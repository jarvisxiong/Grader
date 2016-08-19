package grail.commander;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.animation.AnimationCommandObject;
import grail.animation.Animator;
import grail.animation.BasicAvatarAnimator;
import grail.avatar.Avatar;
import grail.command.MoveCommandToken;
import grail.command.SayCommandToken;
import grail.helpers.Table;
import grail.interfaces.ScannerBean;
import grail.interfaces.CommandToken;
import grail.interfaces.NumberToken;
import grail.interfaces.StoreToken;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import grail.runnables.DefaultCommand;
import grail.runnables.MoveCommand;
import grail.runnables.SayCommand;
import grail.scene.BridgeScene;
import grail.tokens.MinusStoreToken;
import grail.tokens.PlusStoreToken;
import grail.tokens.QuotedStoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"CommandInterpreter", "SignedMove", "ErrorResilient"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Command", "Errors"})
@EditablePropertyNames({"Command"})
public class BasicCommandInterpreter implements CommandInterpreter{
	private BridgeScene scene;
	private ScannerBean scanner;
	private String currentCommand;
	private String errors;
	private Table knightList;
	private int currentToken;
	private String walkCommand = "walk";
	private String clapCommand = "clap";
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	
	public BasicCommandInterpreter(BridgeScene scene, ScannerBean scanner) {
		this.scene = scene;
		this.scanner = scanner;
		this.currentCommand = "";
		this.errors = "";
		this.knightList = scene.getKnightList();
		currentToken = 0;
	}

	
	public String getCommand() {
		return currentCommand;
		
	}

	
	public void setCommand(String commandLine) {
		String oldCommand = this.currentCommand;
		this.currentCommand = commandLine;
		scanner.setScannedString(commandLine);
		currentToken = 0;
		this.errors = "";
		processCommandArray(scanner.getTokens());
		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Command",oldCommand,this.currentCommand ));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Errors", null, this.errors));
		}
		
	}


	@Tags({"ErrorResilient"})
	public String getErrors() {
		return this.errors;
	}
	
	private void processCommandArray(StoreToken[] input) {
		final String defaultError = "Command Found!";
		while (currentToken < input.length) {
			if (input.length > 0 && input[currentToken] instanceof CommandToken) {
				this.errors = defaultError;
				executeCommand(input, input[currentToken]);
			
			} else {
				//Do Nothing;
			}
			currentToken++;
		}
		
		if ("".equalsIgnoreCase(this.errors)) {
			this.errors = "No Command was detected!";
		} else if (defaultError.equals(this.errors)) {
			this.errors = "No Errors Found!";
		}

	}

	private void executeCommand(StoreToken[] input , StoreToken commandToken) {
		Runnable command;
		if (commandToken instanceof SayCommandToken) {
			command = sayCommand(input);
			this.errors = (command instanceof DefaultCommand)? "\"Say\" did not follow proper syntax. Syntax is: \"Say\" <QuotedString>":"No Errors Found in Say!";
		} else if (commandToken instanceof MoveCommandToken) {
			command = moveCommand(input);
			this.errors = (command instanceof DefaultCommand)? "\"Move\" did not follow proper syntax. Syntax is: \"Move\" Avatar <Number> <Number>":"No Errors Found in Move!";
		} else {
			this.errors = "Sorry, unable to implement \"" + commandToken.getInput() + "\" at this time!";
			command = new DefaultCommand();
		}
		command.run();
	}
	
	@Tags({"parseSay"})
	public Runnable sayCommand(StoreToken[] input) {
	    int remainderLength = input.length - currentToken;
		if (remainderLength > 1  && input[currentToken + 1] instanceof QuotedStoreToken) {
			return new SayCommand(this.scene, input[currentToken + 1].getInput());
		} else {
			return new DefaultCommand();
		}
		
	}
	
	@Tags({"parseMove"})
	public Runnable moveCommand(StoreToken[] input) {
		 int remainderLength = input.length - currentToken;
		 int startPosition = currentToken;
		 final int shortFormLength = 3;
		 final int mediumFormLength = 4;
		 final int longFormLength = 5;
		 final int avatarPosition = ++startPosition;
		 final int firstPosition = ++startPosition;
		 final int secondPosition = ++startPosition;
		 final int thirdPosition = ++startPosition;
		 final int fourthPosition = ++startPosition;
		 
		 if (remainderLength > shortFormLength && isAvatar(input[avatarPosition]) && isNumber(input[firstPosition]) && isNumber(input[secondPosition])) {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[currentToken + 1].getInput().toLowerCase()));
			int valueX = ((NumberToken) input[firstPosition]).getValue();
			int valueY = ((NumberToken) input[secondPosition]).getValue();
			return new MoveCommand(currentAvatar,valueX,valueY);
			
		} else if (remainderLength > mediumFormLength && isAvatar(input[avatarPosition]) && isSign(input[firstPosition]) &&  isNumber(input[secondPosition]) && isNumber(input[thirdPosition]))  {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[avatarPosition].getInput().toLowerCase()));
			int whichSignX = (input[firstPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueX = whichSignX * ((NumberToken) input[secondPosition]).getValue();
			int valueY = ((NumberToken) input[thirdPosition]).getValue();
			return new MoveCommand(currentAvatar,valueX,valueY);
			
		} else if (remainderLength > mediumFormLength && isAvatar(input[avatarPosition]) && isNumber(input[firstPosition]) && isSign(input[secondPosition]) && isNumber(input[thirdPosition])) {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[avatarPosition].getInput().toLowerCase()));
			int whichSignY = (input[secondPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueX = ((NumberToken) input[firstPosition]).getValue();
			int valueY = whichSignY * ((NumberToken) input[thirdPosition]).getValue();
			return new MoveCommand(currentAvatar,valueX,valueY);
			
		} else if (remainderLength > longFormLength && isAvatar(input[avatarPosition]) && isSign(input[firstPosition]) && isNumber(input[secondPosition]) && isSign(input[thirdPosition]) && isNumber(input[fourthPosition]) ) {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[avatarPosition].getInput().toLowerCase()));
			int whichSignX = (input[firstPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueX = whichSignX * ((NumberToken) input[secondPosition]).getValue();
			int whichSignY = (input[thirdPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueY = whichSignY * ((NumberToken) input[fourthPosition]).getValue();
			return new MoveCommand(currentAvatar,valueX,valueY);
			
		} else {
			return new DefaultCommand();
		}
	}
		
	private boolean isAvatar(StoreToken input) {
		 	return (this.knightList.contains(input.getInput().toLowerCase()));
		 
	}	
	
		
	private boolean isSign(StoreToken input) {
		return (input instanceof PlusStoreToken || input instanceof MinusStoreToken);
	}
	
	private boolean isNumber(StoreToken input) {
		return input instanceof NumberToken;
	}
	
	@Tags({"asynchronousArthur"})
	public void asynchronousArthur() {
		Animator animate = new BasicAvatarAnimator();
		Runnable animateCommand = new AnimationCommandObject(animate, walkCommand, this.scene.getArthur());
		Thread thread = new Thread(animateCommand);
		thread.start();
	}

	@Tags({"asynchronousGalahad"})
	public void asynchronousGalahad() {
		final int distanceToWalk = 200;
		Animator animate = new BasicAvatarAnimator();
		Runnable animateCommand = new AnimationCommandObject(animate, walkCommand, this.scene.getGalahad(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousLancelot"})
	public void asynchronousLancelot() {
		final int distanceToWalk = 300;
		final int sleepTime = 50;
		Animator animate = new BasicAvatarAnimator();
		Runnable animateCommand = new AnimationCommandObject(animate, walkCommand, this.scene.getLancelot(), distanceToWalk,sleepTime);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousRobin"})
	public void asynchronousRobin() {
		final int distanceToWalk = 260;
		Animator animate = new BasicAvatarAnimator();
		Runnable animateCommand = new AnimationCommandObject(animate, walkCommand, this.scene.getRobin(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousGuard"})
	public void asynchronousGuard() {
		final int secondsToClap = 8;
		Animator animate = new BasicAvatarAnimator();
		Runnable animateCommand = new AnimationCommandObject(animate, clapCommand, this.scene.getGuard(), secondsToClap);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyListenerSupport.addElement(arg0);
		
	}
	
	
	
}
