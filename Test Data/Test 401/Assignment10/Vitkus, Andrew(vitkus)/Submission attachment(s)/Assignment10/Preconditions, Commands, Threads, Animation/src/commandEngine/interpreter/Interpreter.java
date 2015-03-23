package commandEngine.interpreter;

import java.beans.PropertyChangeListener;

import bus.uigen.OEFrame;
import token.IMinusToken;
import token.INumberToken;
import token.IPlusToken;
import token.IQuotedStringToken;
import token.IToken;
import token.IWordToken;
import token.command.IApproachCommandToken;
import token.command.ICommandToken;
import token.command.IFailCommandToken;
import token.command.IMoveCommandToken;
import token.command.IPassCommandToken;
import token.command.IRotateLeftArmCommandToken;
import token.command.IRotateRightArmCommandToken;
import token.command.ISayCommandToken;
import token.command.ISleepCommandToken;
import util.APropertyChangeListenerManager;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;
import graphics.animation.Animator;
import graphics.animation.ClapAnimatingCommand;
import graphics.animation.WalkAnimatingCommand;
import graphics.avatar.IAvatar;
import graphics.view.IBridgeScene;
import commandEngine.commands.ApproachCommand;
import commandEngine.commands.ClapCommand;
import commandEngine.commands.FailCommand;
import commandEngine.commands.IApproachCommand;
import commandEngine.commands.ICommand;
import commandEngine.commands.IFailCommand;
import commandEngine.commands.IMoveCommand;
import commandEngine.commands.IPassCommand;
import commandEngine.commands.IRotateLeftArmCommand;
import commandEngine.commands.IRotateRightArmCommand;
import commandEngine.commands.ISayCommand;
import commandEngine.commands.MoveCommand;
import commandEngine.commands.PassCommand;
import commandEngine.commands.RotateLeftArmCommand;
import commandEngine.commands.RotateRightArmCommand;
import commandEngine.commands.SayCommand;
import commandEngine.tokenizer.IClearableTokenHistory;
import commandEngine.tokenizer.ITokenizer;
import commandEngine.tokenizer.Tokenizer;

@PropertyNames({ "Command", "Error", "OEFrame" })
@EditablePropertyNames({ "Command", "OEFrame" })
@Tags({ "Command Interpreter", "Error Resilient", "Signed Move", "Observable" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class Interpreter implements IInterpreter {
	private final IBridgeScene scene;
	private final ITokenizer tokenizer;
	private final ITable avatarTable;
	private String error;
	private OEFrame frame;

	private APropertyChangeListenerManager pclm = new APropertyChangeListenerManager(this);

	public Interpreter(IBridgeScene scene) {
		this.scene = scene;
		tokenizer = new Tokenizer();

		avatarTable = new Table();
		buildTable();

		error = "";
	}

	@Override
	public void setOEFrame(OEFrame frame) {
		this.frame = frame;
	}

	@Visible(false)
	@Override
	public OEFrame getOEFrame() {
		return frame;
	}

	@Override
	public void setCommand(String command) {
		pclm.firePropertyChange("command", getCommand(), command);
		error = "";
		tokenizer.setLine(command);
		if (tokenizer.getErrors().length > 0) {
			String oldError = error;
			error = "";
			String[] errors = tokenizer.getErrors();
			for(int i = 0; i < errors.length; i ++) {
				error += errors[i];
				if (i != errors.length - 1) {
					error += ", ";
				}
			}
			pclm.firePropertyChange("error", oldError,error);
			return;
		}
		ICommand commandObj = runCommand();
		if (commandObj != null) {
			new Thread(commandObj).start();
		}
		tokenizer.setLine("");
	}

	@Override
	public String getCommand() {
		return tokenizer.getLine();
	}

	private ICommand runCommand() {
		try {
			pclm.firePropertyChange("error", error, "");
			IClearableTokenHistory tokens = tokenizer.getTokens();
			if (tokens.isEmpty()) {
				return null;
			}

			if (tokenizer.getErrors().length > 0) {
				throw new InvalidCommandException("Invalid token '\""
						+ tokens.tokenAt(tokens.size() - 1).getString()
						+ "'. Missing end quote!");
			}
			
			IToken token = tokens.tokenAt(0);

			if (token instanceof ICommandToken) {
				if (token instanceof IMoveCommandToken) {
					return tryMoveToken(tokens);
				} else if (token instanceof ISayCommandToken) {
					return trySayToken(tokens);
				} else if (token instanceof IApproachCommandToken) {
					return tryApproachToken(tokens);
				} else if (token instanceof ISleepCommandToken) {
					trySleepToken(tokens);
				} else if (token instanceof IPassCommandToken) {
					return tryPassToken(tokens);
				} else if (token instanceof IFailCommandToken) {
					return tryFailToken(tokens);
				} else if (token instanceof IRotateRightArmCommandToken) {
					return tryRotateRightArmToken(tokens);
				} else if (token instanceof IRotateLeftArmCommandToken) {
					return tryRotateLeftArmToken(tokens);
				} else {
					throw new InvalidCommandException("Invalid token '"
							+ tokens.tokenAt(0).getString()
							+ "'. Expected 'approach', 'fail', 'move', 'pass', 'say', or 'sleep'!");
				}
			} else {
				throw new InvalidCommandException("Invalid token '"
						+ tokens.tokenAt(0).getString()
						+ "'. Expected a command token!");
			}
		} catch (InvalidCommandException e) {
			// System.err.println(e.getMessage());
			error = e.getMessage();
			pclm.firePropertyChange("error", "", error);
		}
		return null;
	}

	@Tags({ "Move Parser" })
	private IMoveCommand tryMoveToken(IClearableTokenHistory tokens) {
		int tokenCheck = 1;

		int num1 = 1;
		int num2 = 1;

		try {
			if (tokens.tokenAt(tokenCheck) instanceof IWordToken) {
				tokenCheck++;
				if (tokens.tokenAt(tokenCheck) instanceof IMinusToken) {
					num1 = -1;
					tokenCheck++;
				} else if (tokens.tokenAt(tokenCheck) instanceof IPlusToken) {
					tokenCheck++;
				}
				if (tokens.tokenAt(tokenCheck) instanceof INumberToken) {
					num1 *= ((INumberToken) tokens.tokenAt(tokenCheck))
							.getNumber();
					tokenCheck++;
					if (tokens.tokenAt(tokenCheck) instanceof IMinusToken) {
						num2 = -1;
						tokenCheck++;
					} else if (tokens.tokenAt(tokenCheck) instanceof IPlusToken) {
						tokenCheck++;
					}
					if (tokens.tokenAt(tokenCheck) instanceof INumberToken) {
						num2 *= ((INumberToken) tokens.tokenAt(tokenCheck))
								.getNumber();
						tokenCheck++;
						IAvatar avatar = (IAvatar) avatarTable
								.get(((IWordToken) tokens.tokenAt(1)).getWord());

						if (avatar == null) {
							throw new InvalidCommandException(
									"Invalid value of token '"
											+ tokens.tokenAt(1).getString()
											+ "'. Expected an avatar name!");
						}

						return new MoveCommand(avatar, num1, num2);
						//scene.walk(avatar, num1, num2);
					} else {
						throw new InvalidCommandException("Invalid token '"
								+ tokens.tokenAt(tokenCheck).getString()
								+ "'. Expected a number or sign token!");
					}
				} else {
					throw new InvalidCommandException("Invalid token '"
							+ tokens.tokenAt(--tokenCheck).getString()
							+ "'. Expected a number or sign token!");
				}
			} else {
				throw new InvalidCommandException("Invalid token '"
						+ tokens.tokenAt(1).getString()
						+ "'. Expected a word token!");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidCommandException(
					"Not enough arguments for 'move' command! Requires 'move name [+/-]x [+/-]y'");
		}
	}

	@Tags({ "Say Parser" })
	private ISayCommand trySayToken(IClearableTokenHistory tokens) {
		if (!scene.preSay()) {
			throw new InvalidCommandException("Conditions for say not met");
		}
		try {
			if (tokens.tokenAt(1) instanceof IQuotedStringToken) {
				return new SayCommand(scene, ((IQuotedStringToken) tokens.tokenAt(1)).getString());
				//scene.say(((IQuotedStringToken) tokens.tokenAt(1)).getString());
			} else {
				throw new InvalidCommandException("Invalid token '"
						+ tokens.tokenAt(1).getString()
						+ "'. Expected a quoted string token!");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidCommandException(
					"Not enough arguments for 'say' command! Requires 'say \"text\"'");
		}
	}

	@Tags({ "Approach Parser" })
	private IApproachCommand tryApproachToken(IClearableTokenHistory tokens) {
		if (!scene.preApproach()) {
			throw new InvalidCommandException("Conditions to approach not met");
		}
		try {
			if (tokens.tokenAt(1) instanceof IWordToken) {
				IAvatar avatar = (IAvatar) avatarTable.get(((IWordToken) tokens
						.tokenAt(1)).getWord());

				if (avatar == null) {
					throw new InvalidCommandException(
							"Invalid value of token '"
									+ tokens.tokenAt(1).getString()
									+ "'. Expected an avatar name!");
				} else if (avatar.equals(scene.getGuard())) {
					throw new InvalidCommandException(
							"Invalid avatar 'Guard'. Guard cannot approach.");
				}
				//scene.approach(avatar);
				return new ApproachCommand(scene, avatar);
			} else {
				throw new InvalidCommandException("Invalid token '"
						+ tokens.tokenAt(1).getString()
						+ "'. Expected a word token!");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidCommandException(
					"Not enough arguments for 'approach' command! Requires 'approach name'");
		}
	}

	@Tags({ "Sleep Parser" })
	private void trySleepToken(IClearableTokenHistory tokens) {
		try {
			if (tokens.tokenAt(1) instanceof INumberToken) {
				try {
					Thread.sleep(((INumberToken) tokens.tokenAt(1)).getNumber());
				} catch (InterruptedException e) {
				}
			} else {
				throw new InvalidCommandException("Invalid token '"
						+ tokens.tokenAt(1).getString()
						+ "'. Expected a number token!");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidCommandException(
					"Not enough arguments for 'wait' command! Requires 'wait time'");
		}
	}
	
	@Tags({ "Pass Parser" })
	private IPassCommand tryPassToken(IClearableTokenHistory tokens) {
		if (scene.prePass()) {
			return new PassCommand(scene);
		} else {
			throw new InvalidCommandException("Conditions to pass not met");
		}
	}
	
	@Tags({ "Fail Parser" })
	private IFailCommand tryFailToken(IClearableTokenHistory tokens) {
		if (scene.preFail()) {
			return new FailCommand(scene);
		} else {
			throw new InvalidCommandException("Conditions to fail not met");
		}
	}

	@Tags({ "Rotate Right Arm Parser" })
	private IRotateRightArmCommand tryRotateRightArmToken(IClearableTokenHistory tokens) {
		try {
			if (tokens.tokenAt(1) instanceof IWordToken) {
				IAvatar avatar = (IAvatar) avatarTable.get(((IWordToken) tokens.tokenAt(1)).getWord());
				if (avatar == null) {
					throw new InvalidCommandException(
							"Invalid value of token '"
									+ tokens.tokenAt(1).getString()
									+ "'. Expected an avatar name!");
				}
				
				int tokenCheck = 2;
				int steps = 1;
				
				if (tokens.tokenAt(tokenCheck) instanceof IMinusToken) {
					steps = -1;
					tokenCheck ++;
				} else if(tokens.tokenAt(tokenCheck) instanceof IMinusToken) {
					tokenCheck ++;
				}
				if (tokens.tokenAt(tokenCheck) instanceof INumberToken) {
					steps *= ((INumberToken) tokens.tokenAt(tokenCheck)).getNumber();
					return new RotateRightArmCommand(avatar, steps);
				} else {
					throw new InvalidCommandException("Invalid token '"
							+ tokens.tokenAt(tokenCheck).getString()
							+ "'. Expected a number or sign token!");
				}

			} else {
				throw new InvalidCommandException("Invalid token '"
						+ tokens.tokenAt(1).getString()
						+ "'. Expected a word token!");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidCommandException(
					"Not enough arguments for 'rotateRightArm' command! Requires 'rotateRightArm name [+/-] number'");
		}
	}
	

	@Tags({ "Rotate Left Arm Parser" })
	private IRotateLeftArmCommand tryRotateLeftArmToken(IClearableTokenHistory tokens) {try {
		if (tokens.tokenAt(1) instanceof IWordToken) {
			IAvatar avatar = (IAvatar) avatarTable.get(((IWordToken) tokens.tokenAt(1)).getWord());
			if (avatar == null) {
				throw new InvalidCommandException(
						"Invalid value of token '"
								+ tokens.tokenAt(1).getString()
								+ "'. Expected an avatar name!");
			}
			
			int tokenCheck = 2;
			int steps = 1;
			
			if (tokens.tokenAt(tokenCheck) instanceof IMinusToken) {
				steps = -1;
				tokenCheck ++;
			} else if(tokens.tokenAt(tokenCheck) instanceof IMinusToken) {
				tokenCheck ++;
			}
			if (tokens.tokenAt(tokenCheck) instanceof INumberToken) {
				steps *= ((INumberToken) tokens.tokenAt(tokenCheck)).getNumber();
				return new RotateLeftArmCommand(avatar, steps);
			} else {
				throw new InvalidCommandException("Invalid token '"
						+ tokens.tokenAt(tokenCheck).getString()
						+ "'. Expected a number or sign token!");
			}

		} else {
			throw new InvalidCommandException("Invalid token '"
					+ tokens.tokenAt(1).getString()
					+ "'. Expected a word token!");
		}
	} catch (IndexOutOfBoundsException e) {
		throw new InvalidCommandException(
				"Not enough arguments for 'rotateLeftArm' command! Requires 'rotateLeftArm name [+/-] number'");
	}
	}

	private void buildTable() {
		if (avatarTable.isEmpty()) {
			avatarTable.put("arthur", scene.getArthur());
			avatarTable.put("lancelot", scene.getLancelot());
			avatarTable.put("robin", scene.getRobin());
			avatarTable.put("galahad", scene.getGalahad());
			avatarTable.put("guard", scene.getGuard());
		}
	}

	@Override
	public String getError() {
		return error;
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pclm.addPropertyChangeListener(listener);
	}

	@Tags({ "asynchronous arthur" })
	@Override
	public void animateArthur() {
		new Animator().animate(new WalkAnimatingCommand(scene.getArthur(), 100, 0));
		//new Thread(new MoveCommand(scene, scene.getArthur(), 100, 0)).start();
	}

	@Tags({ "asynchronous galahad" })
	@Override
	public void animateGalahad() {
		new Animator().animate(new WalkAnimatingCommand(scene.getGalahad(), 100, 20));
		//new Thread(new MoveCommand(scene, scene.getGalahad(), 100, 20)).start();
	}

	@Tags({ "asynchronous lancelot" })
	@Override
	public void animateLancelot() {
		new Animator().animate(new WalkAnimatingCommand(scene.getLancelot(), 150, 0));
		//new Thread(new MoveCommand(scene, scene.getLancelot(), 150, 0)).start();
	}

	@Tags({ "asynchronous robin" })
	@Override
	public void animateRobin() {
		new Animator().animate(new WalkAnimatingCommand(scene.getRobin(), 150, -20));
		//new Thread(new MoveCommand(scene, scene.getRobin(), 150, -20)).start();
	}

	@Tags({ "asynchronous guard" })
	@Override
	public void animateGuard() {
		new Animator().animate(new ClapAnimatingCommand(scene.getGuard(), 5));
		//new Thread(new ClapCommand(scene, scene.getGuard(), 1)).start();
	}
}
