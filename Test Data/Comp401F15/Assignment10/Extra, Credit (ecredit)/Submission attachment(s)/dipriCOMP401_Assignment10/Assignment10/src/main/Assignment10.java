package main;

import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import grail.ABridgeSceneController;
import grail.ACommandInterpreter;
import grail.ASceneView;
import grail.BodyInterface;
import grail.BridgeScene;
import grail.BridgeSceneController;
import grail.CommandInterpreter;
import grail.ConsoleSceneView;
import grail.Head;
import grail.ScannerBean;
import grail.ScannerInterface;
import grail.BoundedShape;
import grail.scene.ABridgeScene;
import grail.scene.AConsoleSceneView;
import grail.scene.AHead;
import grail.scene.ALine;
import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import java.awt.Component;

public class Assignment10 {
	public static void main(String[] args) {
		
		final int xSize = 1200;
		final int ySize = 900;
		final int actionDelay = 1200;
		final int xSizeEditor = 600;
		final int ySizeEditor = 800;
		ScannerInterface scannedString = new ScannerBean();
		scannedString.setScannedString("Input here");
		OEFrame editor = ObjectEditor.edit(scannedString);
		ThreadSupport.sleep(actionDelay);
		editor.setSize(xSizeEditor, ySizeEditor);
		JFrame frame = new JFrame("Painter");

		BridgeScene bridge = new ABridgeScene();
		PropertyChangeListener painter = new ASceneView(bridge);
		BridgeSceneController controller = new ABridgeSceneController(bridge, (Component) painter);
		frame.add((Component)painter);
		frame.setVisible(true);
		frame.setSize(xSize, ySize);
		OEFrame testScreen = ObjectEditor.edit(bridge);
		testScreen.setSize(xSize, ySize);
		ThreadSupport.sleep(actionDelay);
		CommandInterpreter aCommand = new ACommandInterpreter(bridge, scannedString);
		OEFrame commander = ObjectEditor.edit(aCommand);
		
		ConsoleSceneView theMonitor = new AConsoleSceneView(bridge);
		
//		ThreadSupport.sleep(actionDelay);
		bridge.approachArea(bridge.getGalahad());
//		ThreadSupport.sleep(actionDelay);
		aCommand.setCommand("say \"This is a Test\"");
//		aCommand.setCommand("move Arthur 20 30");
//		ThreadSupport.sleep(actionDelay);
		aCommand.setCommand("say \"Success!\"");
		aCommand.animateArthur();
		aCommand.animateGalahad();
		aCommand.animateLancelot();
		aCommand.animateRobin();
//		ThreadSupport.sleep(actionDelay);
		bridge.pass();
//		aCommand.setCommand("move Robin 150 100");
//		ThreadSupport.sleep(actionDelay);
		bridge.approachArea(bridge.getRobin());
//		ThreadSupport.sleep(actionDelay);
		aCommand.setCommand("say \"What is the capital of Assyria?\"");
//		ThreadSupport.sleep(actionDelay);
		aCommand.setCommand("say \"I don't know that!\"");
//		ThreadSupport.sleep(actionDelay);
		bridge.fail();
//		ThreadSupport.sleep(actionDelay);
//		bridge.approachArea(bridge.getLancelot());
//		ThreadSupport.sleep(actionDelay);
//		aCommand.setCommand("say \"What is your favorite color?\"");
//		ThreadSupport.sleep(actionDelay);
//		bridge.sayScene("Blue, no yel-");
//		ThreadSupport.sleep(actionDelay);
//		bridge.fail();
//		ThreadSupport.sleep(actionDelay);
//		bridge.approachArea(bridge.getArthur());
//		ThreadSupport.sleep(actionDelay);
//		aCommand.setCommand("say \"What is the wing speed velocity of an unladen swallow?\"");
//		ThreadSupport.sleep(actionDelay);
//		aCommand.setCommand("say \"What do you mean? African or European?\"");
//		ThreadSupport.sleep(actionDelay);
//		aCommand.setCommand("say \"What I don't know that!\"");
//		ThreadSupport.sleep(actionDelay);
//		bridge.fail();
//		ThreadSupport.sleep(actionDelay);
//		ThreadSupport.sleep(actionDelay);
	
	
	
	
		// basic method for movement to area
//		if (bridge.getArthur().getBody().getX() != 415 && bridge.getArthur().getBody().getY() != 505){
//			int xDistance = (int) (415 - bridge.getArthur().getBody().getX())/35;
//			int yDistance = (int) (505 - bridge.getArthur().getBody().getY())/35;
//			for (int i = 0; i <= 35; i++){
//				bridge.getArthur().moveBody(xDistance, yDistance);
//				if (i%2 ==0){
//					bridge.getArthur().getLegs().getLeftLine().rotate(-16);
//					bridge.getArthur().getLegs().getRightLine().rotate(32);
//					}
//					else if (i%2 != 0){
//						bridge.getArthur().getLegs().getRightLine().rotate(16);
//						bridge.getArthur().getLegs().getLeftLine().rotate(-32);
//					}
//				testScreen.refresh();
//				ThreadSupport.sleep(100);
//			}
//		}

		}

	public static int getDigitLength(String aString, int tokenStart) {
		int index = tokenStart;
		while (index < aString.length()) {
			char nextDigit = aString.charAt(index);
			if (Character.isDigit(nextDigit)) {
				index++;
			} else if (nextDigit == ' ') {
				break;
			} else {
				System.out.println("Illegal Character Placement");
				break; // breaks while loop and returns digit up to index before
						// while loop. Then, scanAndSort method will scan
						// illegal character and deal with it
			}
		}
		return index;
	}

	public static int getWordLength(String aString, int tokenStart) {
		int index = tokenStart;
		while (index < aString.length()) {
			char nextDigit = aString.charAt(index);
			if (Character.isLetter(nextDigit)) {
				index++;
			} else if (nextDigit == ' ') {
				break;
			} else {
				System.out.println("Illegal Character Placement");
				break;
			}
		}
		return index;
	}

	public static int getQuotedString(String aString, int tokenStart) {
		int index = tokenStart;
		while (index < aString.length()) {
			char nextDigit = aString.charAt(index);
			if (nextDigit != '"') {
				index++;
			} else if (nextDigit == '"') {
				break;
			}
		}
		return index;
	}
}
