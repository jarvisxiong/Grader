package grail.helpers;

import grail.avatar.Avatar;
import grail.command.ApproachCommandToken;
import grail.command.CallCommandToken;
import grail.command.DefineCommandToken;
import grail.command.FailCommandToken;
import grail.command.MoveCommandToken;
import grail.command.PassCommandToken;
import grail.command.PrintStoredCommandToken;
import grail.command.ProceedAllCommandToken;
import grail.command.RedoCommandToken;
import grail.command.RepeatCommandToken;
import grail.command.RotateLeftArmCommandToken;
import grail.command.RotateRightArmCommandToken;
import grail.command.SayCommandToken;
import grail.command.SleepCommandToken;
import grail.command.ThreadCommandToken;
import grail.command.UndoCommandToken;
import grail.exceptions.ParsingException;
import grail.exceptions.ScanningException;
import grail.factories.SingletonsCreator;
import grail.interfaces.NumberToken;
import grail.interfaces.ScannerBean;
import grail.interfaces.StoreToken;
import grail.interfaces.UndoableRunnable;
import grail.runnables.ApproachCommand;
import grail.runnables.CallCommand;
import grail.runnables.CommandList;
import grail.runnables.DefaultCommand;
import grail.runnables.DefineCommand;
import grail.runnables.FailCommand;
import grail.runnables.MoveCommand;
import grail.runnables.PassCommand;
import grail.runnables.ProceedAllCommand;
import grail.runnables.RedoCommand;
import grail.runnables.RepeatCommand;
import grail.runnables.RotateLeftArmCommand;
import grail.runnables.RotateRightArmCommand;
import grail.runnables.RunnableList;
import grail.runnables.SayCommand;
import grail.runnables.SleepCommand;
import grail.runnables.ThreadCommand;
import grail.runnables.UndoCommand;
import grail.scene.BridgeScene;
import grail.tokens.EndStoreToken;
import grail.tokens.MinusStoreToken;
import grail.tokens.NumberStoreToken;
import grail.tokens.PlusStoreToken;
import grail.tokens.QuotedStoreToken;
import grail.tokens.StartStoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Parser"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"CommandText", "CommandObject","Errors"})
@EditablePropertyNames({"CommandText"})
public class BasicCommandParser implements CommandParser {
	private BridgeScene scene;
	private Table<String,Avatar> knightList;
	private ScannerBean scanner;
	private StoreToken[] tokenArray;
	private String errors;
	private int currentPosition;
	private Runnable returnCommand;
	private Table<String,Runnable> commandList;
	private ClearanceManager manager;
	private Runnable previousCommand;
	
	public BasicCommandParser(BridgeScene scene, ClearanceManager manager) {
		this.scene = scene;
		this.manager = manager;
		this.knightList = scene.getKnightList();
		this.commandList = new BasicTable<String,Runnable>();
		this.scanner = SingletonsCreator.scannerFactoryMethod();
		try {
			this.scanner.setScannedString("Default");
		} catch (ScanningException se) {
			//do nothing
		}
		this.tokenArray = this.scanner.getTokens();
		this.errors = "No Errors Found!";
		this.currentPosition = 0;
		this.returnCommand = new DefaultCommand();
		this.previousCommand = new DefaultCommand();
	}
	
	public BasicCommandParser(BridgeScene scene) {
		this (scene, SingletonsCreator.broadcastingClearnaceManagerMethod());
	}
	
	public BasicCommandParser() {
		this (SingletonsCreator.bridgeSceneFactoryMethod());
	}

	public void setCommandText(String text)  {
		this.errors = "";
		
		try {
			this.scanner.setScannedString(text);
		} catch (ScanningException se) {
			this.errors = this.errors + se.getLocalizedMessage();
		}
		
		this.tokenArray = this.scanner.getTokens();
		this.currentPosition = 0;
		
		this.errors = this.errors + "Parsing returned the following errors:";
		
		try {
			this.returnCommand = parseCommand();
			this.errors = this.errors + "\nCommand was parsed successfully! Note: Excess or error tokens ignored.";
		} catch (ParsingException pe) {
			this.errors = this.errors + pe.getLocalizedMessage();
			this.returnCommand = new DefaultCommand();
		} 
	}


	public Runnable getCommandObject() {
		return this.returnCommand;
	}

	
	public String getErrors() {
		return this.errors;
		
	}

	@Tags({"parseCommand"})
	public Runnable parseCommand() throws ParsingException {
		Runnable returnCommand;
		StoreToken currentToken = (this.currentPosition < this.tokenArray.length)? this.tokenArray[this.currentPosition]:null;
		currentPosition++;
			if (currentToken instanceof SayCommandToken) {
				returnCommand = parseSay();
			} else if (currentToken instanceof MoveCommandToken) {
					returnCommand = parseMove();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof ApproachCommandToken) {
					returnCommand = parseApproach();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof PassCommandToken) {
					returnCommand = parsePass();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof FailCommandToken) {
					returnCommand = parseFail();
					this.previousCommand =  returnCommand;
			} else if (currentToken instanceof StartStoreToken) {
					returnCommand = parseCommandList();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof RepeatCommandToken) {
					returnCommand = parseRepeat();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof RotateLeftArmCommandToken) {
					returnCommand = parseLeftArm();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof RotateRightArmCommandToken) {
					returnCommand = parseRightArm();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof SleepCommandToken) {
					returnCommand = parseSleep();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof DefineCommandToken) {
					returnCommand = parseDefine();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof CallCommandToken) {
					returnCommand = parseCall();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof ThreadCommandToken) {
					returnCommand = parseThread();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof PrintStoredCommandToken) {
					returnCommand = parsePrint();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof ProceedAllCommandToken) {
					returnCommand = parseProceedAll();
					this.previousCommand = returnCommand;
			} else if (currentToken instanceof UndoCommandToken) {
					returnCommand = parseUndo();
			} else if (currentToken instanceof RedoCommandToken) {
					returnCommand = parseRedo();
			} else {	
				throw new ParsingException("\nA Command Token was not found when parseCommand() was run. No command was executed!");
			}
			return returnCommand;
	}

	@Tags({"parseSay"})
	public Runnable parseSay() throws ParsingException{
	    int remainderLength = tokenArray.length - currentPosition;
		
	    if (remainderLength >= 1  && tokenArray[currentPosition] instanceof QuotedStoreToken) {
			int returnPosition = currentPosition;
	    	currentPosition++;
			return new SayCommand(this.scene, tokenArray[returnPosition].getInput());
		} else {
			throw new ParsingException("\nError: Say command has syntax: say \"Quote\"");
		}
	}

	
	@Tags({"parseMove"})
	public Runnable parseMove() throws ParsingException{
		 Avatar toMove = (this.currentPosition < this.tokenArray.length && isAvatar(this.tokenArray[this.currentPosition]))? (Avatar) this.knightList.get(this.tokenArray[this.currentPosition].getInput().toLowerCase()):null;
		 if (toMove != null) {
			 currentPosition++;
			 int xAmount = 0;
			 int yAmount = 0;
			 xAmount = numberParser();
			 yAmount = numberParser();	 
			 return new MoveCommand(toMove, xAmount, yAmount);
		} else {
			 throw new ParsingException("\nAvatar token was not found. Please use syntax: move <Avatar> <Number> <Number>");
		}
		 
	}

	@Tags({"parseApproach"})
	public Runnable parseApproach() throws ParsingException{
		int remainderLength = tokenArray.length - currentPosition;
		
	    if (remainderLength >= 1  && isAvatar(tokenArray[currentPosition])) {
			int returnPosition = currentPosition;
	    	currentPosition++;
			return new ApproachCommand(this.scene, (Avatar) (this.knightList.get(tokenArray[returnPosition].getInput().toLowerCase())));
		} else {
			 throw new ParsingException("\nApproach command has syntax: approach knight");

		}
	}

	
	@Tags({"parsePass"})
	public Runnable parsePass() {
		return new PassCommand(this.scene);
	}

	@Tags({"parseFail"})
	public Runnable parseFail() {
		return new FailCommand(this.scene);
	}

	@Tags({"parseCommandList"})
	public Runnable parseCommandList() throws ParsingException {
		RunnableList returnList = new CommandList();
		int artificialEnd = getMatchingEndQuote();
		while (this.currentPosition < artificialEnd) {
			returnList.add(parseCommand());
		}
		
		this.currentPosition = artificialEnd + 1;
		return (CommandList) returnList;
	}

	@Tags({"parseRepeat"})
	public Runnable parseRepeat() throws ParsingException {
		int numberOfRepeats = numberParser();
		Runnable commandToExecute = parseCommand();
		return new RepeatCommand(numberOfRepeats, commandToExecute);
		
	}
	
	@Tags({"numberParser"})
	public int numberParser() throws ParsingException{
		StoreToken firstToken = (this.currentPosition < this.tokenArray.length)? this.tokenArray[this.currentPosition]:null;
		if (isNumber(firstToken)) {
			this.currentPosition++;
			return ((NumberStoreToken) firstToken).getValue();
		} else if (isSign(firstToken) && this.currentPosition + 1 < this.tokenArray.length && isNumber(this.tokenArray[this.currentPosition + 1]) ) {
			int signNumber = (firstToken instanceof PlusStoreToken)? 1:-1;
			currentPosition++;
			StoreToken secondToken = this.tokenArray[this.currentPosition];
			currentPosition++;
			return signNumber * ((NumberStoreToken) secondToken).getValue();
		 } else {
			throw new ParsingException("\nA number was not found in the proper format. Please Check your input.");
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
	
	private int getMatchingEndQuote() throws ParsingException{
		int counter = 1;
		int endPosition = this.currentPosition;
		try {
			while (counter > 0) {
				StoreToken currentToken = this.tokenArray[endPosition];
				if ( currentToken instanceof StartStoreToken) {
					counter++;
				} else if ( currentToken instanceof EndStoreToken) {
					counter--;
				}
				endPosition++;
			} 
		} catch (StackOverflowError e) {
			throw new ParsingException("\nOverflow: An End Quote was not found in command. Check for }");
		} catch (Exception e) {
			throw new ParsingException("\nException: An End Quote was not found in command. Check for }");
		}
		return endPosition - 1;
	}

	@Tags({"parseLeftArm"})
	public Runnable parseLeftArm() throws ParsingException{
		 Avatar toMove = (this.currentPosition < this.tokenArray.length && isAvatar(this.tokenArray[this.currentPosition]))? (Avatar) this.knightList.get(this.tokenArray[this.currentPosition].getInput().toLowerCase()):null;
		 if (toMove != null) {
			 currentPosition++;
			 int rotateAmount = numberParser();
			 return new RotateLeftArmCommand(toMove, rotateAmount);
		} else {
			throw new ParsingException("\nAvatar token was not found. Please use syntax: rotateLeftArm <Avatar> <Number>");

		}
		
	}

	@Tags({"parseRightArm"})
	public Runnable parseRightArm() throws ParsingException{
		Avatar toMove = (this.currentPosition < this.tokenArray.length && isAvatar(this.tokenArray[this.currentPosition]))? (Avatar) this.knightList.get(this.tokenArray[this.currentPosition].getInput().toLowerCase()):null;
		 if (toMove != null) {
			 currentPosition++;
			 int rotateAmount = numberParser();
			 return new RotateRightArmCommand(toMove, rotateAmount);
		} else {
			throw new ParsingException("\nAvatar token was not found. Please use syntax: rotateRighttArm <Avatar> <Number>");

		}
	}

	@Tags({"parseSleep"})
	public Runnable parseSleep() throws ParsingException {
		long amountToSleep = (long) numberParser();
		return new SleepCommand(amountToSleep);
	}

	@Tags({"parseDefine"})
	public Runnable parseDefine() throws ParsingException {
		String commandName = (this.currentPosition < this.tokenArray.length)? this.tokenArray[this.currentPosition].getInput():null;
		if (commandName != null) {
			currentPosition++;
		} else {
			throw new ParsingException("\nDefine is missing a name for command. Use syntax: define <Word> <Command>");
		}
		
		
		Runnable command = parseCommand();
		
		if (command != null) {
			return new DefineCommand(commandName, command, this.commandList);
		} else {
			throw new ParsingException("\nDefine did not find a command. Use Syntax: define <Word> <Command>");
		}
	}

	@Tags({"parseCall"})
	public Runnable parseCall() throws ParsingException {
		String commandName = (this.currentPosition < this.tokenArray.length && this.commandList.contains(this.tokenArray[this.currentPosition].getInput()))? this.tokenArray[this.currentPosition].getInput():null;
		if (commandName != null) {
			currentPosition++;
			return new CallCommand(commandName, this.commandList);
		} else {
			throw new ParsingException("\nCall did not find a command with that name or syntax was not: call <Name>. Use Define to add commands. Use Print to view stored commands");
		}
	}

	@Tags({"parseThread"})
	public Runnable parseThread() throws ParsingException{
		String commandName = (this.currentPosition < this.tokenArray.length && this.commandList.contains(this.tokenArray[this.currentPosition].getInput()))? this.tokenArray[this.currentPosition].getInput():null;
		if (commandName != null) {
			currentPosition++;
			return new ThreadCommand(commandName, this.commandList);
		} else {
			throw new ParsingException("\nThread did not find a command with that name or syntax was not: Thread <Name>. Use Define to add commands. Use Print to view stored commands");
		}
	}
	
	@Tags({"parseProceedAll"})
	public Runnable parseProceedAll() {
		return new ProceedAllCommand(this.manager);
	}
	
	@Tags({"parseUndo"})
	public Runnable parseUndo() throws ParsingException {
		try {
			return new UndoCommand(((UndoableRunnable) this.previousCommand));
		} catch (ClassCastException ce) {
			throw new ParsingException("\nThe previous command can not be undone.");
		}
	}
	
	@Tags({"parseRedo"})
	public Runnable parseRedo() throws ParsingException {
		try {
			return new RedoCommand(((UndoableRunnable) this.previousCommand));
		} catch (ClassCastException ce) {
			throw new ParsingException("\nThe previous command can not be redone.");
		} 
	}
	
	@Tags({"parsePrint"})
	public Runnable parsePrint() {
		this.errors = "The following commands are available to be run:\n";
		this.errors = this.errors + this.commandList.keyList();
		return new DefaultCommand();
	}
	
}
