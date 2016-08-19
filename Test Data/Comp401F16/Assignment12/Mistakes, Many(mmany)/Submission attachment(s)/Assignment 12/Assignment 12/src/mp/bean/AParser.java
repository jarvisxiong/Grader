package mp.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;

import mp.bean.animation.BroadcastingClearanceManager;
import mp.bean.commands.ACommandList;
import mp.bean.commands.ApproachCommand;
import mp.bean.commands.CallCommand;
import mp.bean.commands.CommandList;
import mp.bean.commands.DefineCommand;
import mp.bean.commands.FailCommand;
import mp.bean.commands.MoveCommand;
import mp.bean.commands.PassCommand;
import mp.bean.commands.ProceedAllCommand;
import mp.bean.commands.RepeatCommand;
import mp.bean.commands.RotateLeftArmCommand;
import mp.bean.commands.RotateRightArmCommand;
import mp.bean.commands.SayCommand;
import mp.bean.commands.SleepCommand;
import mp.bean.commands.ThreadCommand;
import mp.tokens.AMinusToken;
import mp.tokens.ANumToken;
import mp.tokens.APlusToken;
import mp.tokens.AQuoteToken;
import mp.tokens.AValueToken;
import mp.tokens.AWordToken;
import mp.tokens.ApproachToken;
import mp.tokens.CallToken;
import mp.tokens.DefineToken;
import mp.tokens.EndToken;
import mp.tokens.FailToken;
import mp.tokens.MoveToken;
import mp.tokens.PassToken;
import mp.tokens.ProceedAllToken;
import mp.tokens.RepeatToken;
import mp.tokens.RotateLeftArmToken;
import mp.tokens.RotateRightArmToken;
import mp.tokens.SayToken;
import mp.tokens.SleepToken;
import mp.tokens.StartToken;
import mp.tokens.ThreadToken;
import mp.tokens.Token;
import mp.tokens.NumValueInt;
import mp.ui.Avatar;
import mp.ui.BridgeScene;
import mp.ui.mvc.APropertyListenerSupport;
import mp.ui.mvc.PropertyListenerSupport;
import util.annotations.Tags;

@Tags({"Parser"})

public class AParser implements Parser {

	BridgeScene scene;
	Table<String, Avatar> avatars;
	Scanner scanner;
	public int counter;
	int tokensLength;
	Token token;
	Avatar avatar;
	Object inputObj;
	String error;
	public Token[] tokens;
	Runnable commandObject, currCommand;
	PropertyListenerSupport listenerSupport;
	Table<String, Runnable> commandEnvironment;
	BroadcastingClearanceManager cManager;

	public AParser(BridgeScene aScene, Table<String, Runnable> aCommandEnvironment, BroadcastingClearanceManager clearanceManager) {
		scene = aScene;
		scanner = new AScanner();
		avatars = new ATable<String, Avatar>();
		avatars.put("arthur", scene.getArthur());
		avatars.put("galahad", scene.getGalahad());
		avatars.put("lancelot", scene.getLancelot());
		avatars.put("robin", scene.getRobin());
		avatars.put("guard", scene.getGuard());
		listenerSupport = new APropertyListenerSupport();
		commandEnvironment = aCommandEnvironment;
		cManager = clearanceManager;
	}

	public void setCommandText(String commandText) {
		scanner.setScannedString(commandText);
		counter = 0;
		tokens = scanner.getTokens();
		tokensLength = Array.getLength(tokens);
		setError(" ");
		if (Array.getLength(tokens) >= 1) {
			token = tokens[counter];
			commandObject = parseCommand();
		} else {
			setError("Invalid input: no command found.");
		}
	}

	public Runnable getCommandObject() {
		return commandObject;
	}
	@Tags({ "parseCommand" })
	public Runnable parseCommand() {
		if (token instanceof SayToken) { currCommand = parseSay(); } 
		else if (token instanceof MoveToken) { currCommand = parseMove(); }
		else if (token instanceof ApproachToken) { currCommand = parseApproach(); }
		else if (token instanceof PassToken) { currCommand = parsePass(); }
		else if (token instanceof FailToken) { currCommand = parseFail(); }
		else if (token instanceof StartToken) { currCommand = parseCommandList(); }
		else if (token instanceof RepeatToken) { currCommand = parseRepeat(); }
		else if (token instanceof RotateLeftArmToken) { currCommand = parseRotateLeftArm(); }
		else if (token instanceof RotateRightArmToken) { currCommand = parseRotateRightArm(); }
		else if (token instanceof SleepToken) { currCommand = parseSleep(); }
		else if (token instanceof DefineToken) { currCommand = parseDefine(); }
		else if (token instanceof CallToken) { currCommand = parseCall(); }
		else if (token instanceof ThreadToken) { currCommand = parseThread(); }
		else if (token instanceof ProceedAllToken) { currCommand = parseProceedAll(); }
		else { setError("Unexpected command token: " + token);
			return null; }
		return currCommand;
		// Each parse*() should set the appropriate error before they return null so 
		// error doesn't need to be set again if currCommand is null
	}
	@Tags({ "parseSay" })
	public Runnable parseSay() {
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		if (!(token instanceof AQuoteToken)) {
			setError("Invalid value for say command");
			return null; }
		return new SayCommand(scene, token.getInput());
	}
	@Tags({ "parseMove" })
	public Runnable parseMove() {
		Integer[] moveVals = { 0, 0 };
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		avatar = (Avatar) avatars.get(((AValueToken) token).getValue());
		if (avatar == null) {
			setError("Invalid avatar name.");
			return null;
		}
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		//checking for incorrect values is done in parseNumber()
		moveVals[0] = parseNumber();
		if (moveVals[0] == null) { return null; }
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		moveVals[1] = parseNumber();
		if (moveVals[1] == null) { return null; }
		return new MoveCommand(avatar, moveVals[0], moveVals[1]);
	}
	@Tags({ "parseApproach" })
	public Runnable parseApproach() {
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		avatar = (Avatar) avatars.get(((AValueToken) token).getValue());
		if (avatar == null) {
			setError("Invalid avatar name.");
			return null;
		}
		return new ApproachCommand(scene, avatar);
	}
	@Tags({ "parsePass" })
	public Runnable parsePass() {
		return new PassCommand(scene);
	}
	@Tags({ "parseFail" })
	public Runnable parseFail() {
		return new FailCommand(scene);
	}
	@Tags({ "parseCommandList" })
	public CommandList parseCommandList() {
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		CommandList commandList = new ACommandList();
		Runnable commandElement;
		while (!(token instanceof EndToken)) {
			commandElement = parseCommand();
			if (commandElement == null) { break; }
			commandList.add(commandElement);
			counter++;
			if (outOfBounds()) {
				setError("End token missing after last command");
				break; }
			token = tokens[counter];
		} 
		if (commandList.size() == 0) {return null;} //assuming whatever caused an empty list reported the error already
		return commandList;
	}
	@Tags({ "parseRepeat" })
	public Runnable parseRepeat() {
		int repeatNum;
		Runnable repeatCommand;
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		if (!(token instanceof ANumToken)) {
			setError("Invalid value for repeat command.");
			return null; }
		repeatNum = ((NumValueInt) token).getValue();
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		repeatCommand = parseCommand();
		return new RepeatCommand(repeatNum, repeatCommand);
	}
	@Tags({ "numberParser" })
	public int parseNumber() {
		int signCoefficient = 1;
		if (token instanceof ANumToken) {
			return ((NumValueInt) token).getValue();
		} else if (token instanceof APlusToken || token instanceof AMinusToken) {
			if (token instanceof APlusToken) { signCoefficient = 1; }
			if (token instanceof AMinusToken) { signCoefficient = -1; }
			counter++;
			if (checkForMissingValue()) { return (Integer) null; }
			token = tokens[counter];
			if (!(token instanceof ANumToken)) { 
				setError("Invalid input for move command.");
				return (Integer) null; }
			return signCoefficient*((NumValueInt) token).getValue();			
		}
		//if it reaches this point then token was neither ANumToken or signed token so it is invalid
		setError("Invalid value for move command");
		return (Integer) null;
	}
	@Tags({ "parseRotateLeftArm" })
	public Runnable parseRotateLeftArm() {
		int rotateNum;
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		avatar = avatars.get(((AValueToken) token).getValue());
		if (avatar == null) {
			setError("Invalid avatar name.");
			return null;
		}
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		rotateNum = parseNumber();
		return new RotateLeftArmCommand(avatar, rotateNum);
	}
	@Tags({ "parseRotateRightArm" })
	public Runnable parseRotateRightArm() {
		int rotateNum;
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		avatar = avatars.get(((AValueToken) token).getValue());
		if (avatar == null) {
			setError("Invalid avatar name.");
			return null;
		}
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		rotateNum = parseNumber();;
		return new RotateRightArmCommand(avatar, rotateNum);
	}
	@Tags({ "parseSleep" })
	public Runnable parseSleep() {
		int sleepNum;
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		if (!(token instanceof ANumToken)) {
			setError("Invalid value for sleep command.");
			return null; }
		sleepNum = ((NumValueInt) token).getValue();
		return new SleepCommand(sleepNum);
	}
	@Tags({ "parseDefine" })
	public Runnable parseDefine() {
		String defineName;
		Runnable defineCommand;
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		if (!(token instanceof AWordToken)) {
			setError("Invalid value for define command.");
			return null; }
		defineName = ((AWordToken) token).getValue();
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		defineCommand = parseCommand();
		return new DefineCommand(defineName, defineCommand, commandEnvironment);
	}
	@Tags({ "parseCall" })
	public Runnable parseCall() {
		String callName;
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		if (!(token instanceof AWordToken)) {
			setError("Invalid value for call command.");
			return null; }
		callName = ((AWordToken) token).getValue();
		return new CallCommand(callName, commandEnvironment);
	}
	@Tags({ "parseThread" })
	public Runnable parseThread() {
		String threadCommandName;
		counter++;
		if (checkForMissingValue()) { return null; }
		token = tokens[counter];
		if (!(token instanceof AWordToken)) {
			setError("Invalid value for thread command.");
			return null; }
		threadCommandName = ((AWordToken) token).getValue();
		return new ThreadCommand(threadCommandName, commandEnvironment);
	}
	@Tags({ "parseProceedAll" })
	public Runnable parseProceedAll() {
		return new ProceedAllCommand(cManager);
	}
	public String getErrors() {
		return error;
	}

	private void setError(String text) {
		String oldVal = error;
		error = text;
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Error", oldVal, error));
	}
	private boolean outOfBounds() {
		if (counter >= tokensLength) {
			return true;
		}
		return false;
	}
	private boolean checkForMissingValue() {
		if (outOfBounds()) {
			setError("Missing value for command");
			return true; } 
		return false;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listenerSupport.addElement(listener);
	}

}
