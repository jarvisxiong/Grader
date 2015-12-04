package main;

import javax.swing.JFrame;
import java.awt.Component;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import mp.AnAvatar;
import mp.Avatar;
import mp.ABridgeScene;
import mp.BridgeScene;
import mp.AGorgeScene;
import mp.GorgeScene;
import mp.AnOvalShape;
import mp.OvalShape;
import grail.ACommandInterpreter;
import grail.ApproachCommand;
import grail.CallCommand;
import grail.CommandInterpreter;
import grail.DefineCommand;
import grail.End;
import grail.Minus;
import grail.MoveCommand;
import grail.Number;
import grail.Plus;
import grail.ProceedAllCommand;
import grail.Quote;
import grail.RedoCommand;
import grail.RepeatCommand;
import grail.RotateLeftArmCommand;
import grail.RotateRightArmCommand;
import grail.SayCommand;
import grail.SleepCommand;
import grail.Start;
import grail.ThreadCommand;
import grail.TokenInterface;
import grail.UndoCommand;
import grail.WaitCommand;
import grail.Word;
import util.misc.ThreadSupport;

public class Assignment12 {
	public final static int MAX_TOKENS = 20;
	public final static int SLEEP_TIME = 1500;
	public final static int SPEECH_TIME = 1500;
	public final static int MAX_HEROES = 5;
	public final static int BRIDGE_WIDTH = 300;
	public final static int BRIDGE_HEIGHT = 200;
	public final static int BRIDGE_INIT_Y = 200;
	public final static int GORGE_INIT_X = 1100;
	public final static int GORGE_HEIGHT = 600;
	public final static int GORGE_WIDTH = 300;
	public final static int GUARD_AREA_X = 900;
	public final static int KNIGHT_AREA_X = 700;
	public final static int GUARD_AREA_Y = 300;
	public final static int KNIGHT_AREA_Y = 300;
	public final static int AREA_WIDTH = 200;
	public final static int AREA_HEIGHT = 100;
	public final static int KNIGHT_INIT_X = 100;
	public final static int KNIGHT_INIT_Y = 100;
	public final static int KNIGHT_LIMB_LENGTH = 50;
	public final static int SWORD_LENGTH = 100;
	public final static int OFFSET = 200;
	public final static int OVAL_X_CORRECTION = 100;
	public final static int OVAL_Y_CORRECTION = 20;
	public final static int WINDOW_WIDTH = 1700;
	public final static int WINDOW_HEIGHT = 700;
	private static boolean quoteHit = false;
	
	
	public static void main (String[] args) {
		/* Instances */
		BroadcastingClearanceManager manager = new ABroadcastingClearanceManager();
		ScannerBeanInterface scan = new ScannerBean();
		Avatar arthur = new AnAvatar("arthur.jpg", "", KNIGHT_INIT_X, KNIGHT_INIT_Y, KNIGHT_LIMB_LENGTH, KNIGHT_LIMB_LENGTH,(int) (SWORD_LENGTH/2), SWORD_LENGTH);
		Avatar galahad = new AnAvatar("galahad.jpg", "", KNIGHT_INIT_X + OFFSET, KNIGHT_INIT_Y, KNIGHT_LIMB_LENGTH, KNIGHT_LIMB_LENGTH,(int) (SWORD_LENGTH/2), SWORD_LENGTH);
		Avatar guard = new AnAvatar("guard.jpg", "", GUARD_AREA_X + OVAL_X_CORRECTION, GUARD_AREA_Y - OVAL_Y_CORRECTION, KNIGHT_LIMB_LENGTH, KNIGHT_LIMB_LENGTH,(int) (SWORD_LENGTH/2), SWORD_LENGTH);
		Avatar lancelot = new AnAvatar("lancelot.jpg", "", KNIGHT_INIT_X, KNIGHT_INIT_Y + OFFSET, KNIGHT_LIMB_LENGTH, KNIGHT_LIMB_LENGTH,(int) (SWORD_LENGTH/2), SWORD_LENGTH);
		Avatar robin = new AnAvatar("robin.jpg", "", KNIGHT_INIT_X + OFFSET, KNIGHT_INIT_Y + OFFSET, KNIGHT_LIMB_LENGTH, KNIGHT_LIMB_LENGTH,(int) (SWORD_LENGTH/2), SWORD_LENGTH);
		GorgeScene gorge = new AGorgeScene(GORGE_INIT_X, BRIDGE_INIT_Y, GORGE_WIDTH, GORGE_HEIGHT, BRIDGE_WIDTH, BRIDGE_HEIGHT);
		OvalShape knightArea = new AnOvalShape(KNIGHT_AREA_X, KNIGHT_AREA_Y, AREA_WIDTH, AREA_HEIGHT);
		OvalShape guardArea = new AnOvalShape(GUARD_AREA_X, GUARD_AREA_Y, AREA_WIDTH, AREA_HEIGHT);
		BridgeScene bridge = new ABridgeScene(arthur, galahad, guard, lancelot, robin, gorge, guardArea, knightArea);
		CommandInterpreter aCommandInterpreter = new ACommandInterpreter(bridge, scan, manager);
		ConsoleSceneView aConsoleSceneView = new AConsoleSceneView(bridge);
		ABridgeScenePainter aBridgeScenePainter = new ABridgeScenePainter(bridge);
		BridgeSceneController aBridgeSceneController = new ABridgeSceneController(aBridgeScenePainter, bridge);	
		
		/*Windows*/
		OEFrame editor3 = ObjectEditor.edit(manager);
		OEFrame editor2 = ObjectEditor.edit(aCommandInterpreter); 
		JFrame frame = new JFrame("A Bridge Scene");
		frame.add((Component)aBridgeScenePainter);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setVisible(true);
	
		/*Demos*/
		aCommandInterpreter.waitingRobin();
		aCommandInterpreter.waitingGalahad();
		aCommandInterpreter.waitingArthur();
		aCommandInterpreter.waitingLancelot();
	}
		/*Scanning and parsing methods*/
	public static String[] parseString (String userInput) {
		String[] parsedStrings = new String[MAX_TOKENS];
		int tokenCounter = 0;
		int startSubstring = -1;
		for(int counter=0; counter< userInput.length(); counter++){
			if(userInput.charAt(counter) == '"' && !quoteHit) {
				String temp = quoteFinder(userInput, counter+1);
				parsedStrings[tokenCounter] = temp;
				tokenCounter++;
				counter += temp.length();
				startSubstring=counter;
			}
			else if((userInput.charAt(counter) == ' ') && (counter != userInput.length()-1)){
				if (startSubstring == 0) {parsedStrings[tokenCounter] = userInput.substring(startSubstring,counter);}
				else {parsedStrings[tokenCounter]=userInput.substring(startSubstring+1, counter);}
				startSubstring=counter;
				tokenCounter++;
				quoteHit = false;
			}
			else if(counter==userInput.length()-2){
				parsedStrings[tokenCounter]=userInput.substring(startSubstring+1, counter+1);
				quoteHit = false;
			}
		}
		return parsedStrings;
	}
	
	public static String quoteFinder(String input, int initPoint) {
		for (int x = initPoint+1; x <(input.length()); x++) {
			if (input.charAt(x) == '"') {
				quoteHit = true;
				return input.substring(initPoint-1,x+1);
			}
		}
		return "error";
	}

	public static TokenInterface[] scanner (String parseString) {
		
		TokenInterface[] maxTokens = new TokenInterface[MAX_TOKENS];
		String[] parsedString = parseString(parseString);
		
		for (int count = 0; count < MAX_TOKENS; count++) {
			if (parsedString[count] == null) {
				return maxTokens;
				}
			else if (Character.isDigit(parsedString[count].charAt(0))) {
				TokenInterface number = new Number(parsedString[count]);
				maxTokens[count] = number;
			}
			else if (Character.isLetter(parsedString[count].charAt(0))) {
				if ("sleep".equalsIgnoreCase(parsedString[count])) {
					TokenInterface sleep = new SleepCommand(parsedString[count]);
					maxTokens[count] = sleep;
				}
				else if ("call".equalsIgnoreCase(parsedString[count])) {
					TokenInterface call = new CallCommand(parsedString[count]);
					maxTokens[count] = call;
				}
				else if ("define".equalsIgnoreCase(parsedString[count])) {
					TokenInterface define = new DefineCommand(parsedString[count]);
					maxTokens[count] = define;
				}
				else if ("move".equalsIgnoreCase(parsedString[count])) {
					TokenInterface move = new MoveCommand(parsedString[count]);
					maxTokens[count] = move;
				}
				else if ("proceedAll".equalsIgnoreCase(parsedString[count])) {
					TokenInterface proceedAll = new ProceedAllCommand(parsedString[count]);
					maxTokens[count] = proceedAll;
				}
				else if ("redo".equalsIgnoreCase(parsedString[count])) {
					TokenInterface redo = new RedoCommand(parsedString[count]);
					maxTokens[count] = redo;
				}
				else if ("repeat".equalsIgnoreCase(parsedString[count])) {
					TokenInterface repeat = new RepeatCommand(parsedString[count]);
					maxTokens[count] = repeat;
				}
				else if ("rotateLeftArm".equalsIgnoreCase(parsedString[count])) {
					TokenInterface rotateLeftArm = new RotateLeftArmCommand(parsedString[count]);
					maxTokens[count] = rotateLeftArm;
				}
				else if ("rotateRightArm".equalsIgnoreCase(parsedString[count])) {
					TokenInterface rotateRightArm = new RotateRightArmCommand(parsedString[count]);
					maxTokens[count] = rotateRightArm;
				}
				else if ("say".equalsIgnoreCase(parsedString[count])) {
					TokenInterface say = new SayCommand(parsedString[count]);
					maxTokens[count] = say;
				}
				else if ("thread".equalsIgnoreCase(parsedString[count])) {
					TokenInterface thread = new ThreadCommand(parsedString[count]);
					maxTokens[count] = thread;
				}
				else if ("undo".equalsIgnoreCase(parsedString[count])) {
					TokenInterface undo = new UndoCommand(parsedString[count]);
					maxTokens[count] = undo;
				}
				else if ("wait".equalsIgnoreCase(parsedString[count])) {
					TokenInterface wait = new WaitCommand(parsedString[count]);
					maxTokens[count] = wait;
				}
				else if ("approach".equalsIgnoreCase(parsedString[count])) {
					TokenInterface approach = new ApproachCommand(parsedString[count]);
					maxTokens[count] = approach;
				}
				else {
				TokenInterface word = new Word(parsedString[count]);
				maxTokens[count] = word;
				}
			}
			else if (parsedString[count].charAt(0) == '"') {
				TokenInterface quote = new Quote(parsedString[count]);
				maxTokens[count] = quote;
			}
			else if (parsedString[count].charAt(0) == '{') {
				TokenInterface start = new Start(parsedString[count]);
				maxTokens[count] = start;
			}
			else if (parsedString[count].charAt(0) == '}') {
				TokenInterface end = new End(parsedString[count]);
				maxTokens[count] = end;
			}
			else if (parsedString[count].charAt(0) == '+') {
				TokenInterface plus = new Plus(parsedString[count]);
				maxTokens[count] = plus;
			}
			else if (parsedString[count].charAt(0) == '-') {
				TokenInterface minus = new Minus(parsedString[count]);
				maxTokens[count] = minus;
			}
		}
		return maxTokens;
	}

}
