package mp;

import java.awt.Component;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import mp.bean.AScanner;
import mp.bean.ACommandInterpreter;
import mp.bean.ATable;
import mp.bean.Scanner;
import mp.bean.CommandInterpreter;
import mp.bean.Table;
import mp.bean.animation.ABroadcastingClearanceManager;
import mp.bean.animation.BroadcastingClearanceManager;
import mp.ui.ABridgeScene;
import mp.ui.AHead;
import mp.ui.ARotatingLine;
import mp.ui.AVShape;
import mp.ui.AnAvatar;
import mp.ui.Avatar;
import mp.ui.BridgeScene;
import mp.ui.Head;
import mp.ui.VShape;
import mp.ui.mvc.ABackgroundPainter;
import mp.ui.mvc.ABridgeSceneController;
import mp.ui.mvc.ACommandInterpreterController;
import mp.ui.mvc.ACommandInterpreterView;
import mp.ui.mvc.AnAvatarPainter;
import mp.ui.mvc.AnObservableBridgeScenePainter;
import mp.ui.mvc.BridgeSceneController;
import mp.ui.mvc.CommandInterpreterController;
import mp.ui.mvc.CommandInterpreterView;
import mp.ui.mvc.ConsoleSceneView;
import mp.ui.mvc.ObservableBridgeScenePainter;
import mp.ui.mvc.PaintListener;
import mp.ui.AdvLocatable;
import mp.ui.AnAdvLocatable;
import mp.ui.RotatingLine;
import util.annotations.Tags;
import util.misc.ThreadSupport;


public class Assignment12 {

	public static void main(String[] args) {
		
		final int[] screenSize = {800,600};
		
		BridgeScene scene = new ABridgeScene();
		
		ObservableBridgeScenePainter painter = new AnObservableBridgeScenePainter(scene);
		PaintListener arthur = new AnAvatarPainter(scene.getArthur(), painter);
		PaintListener galahad = new AnAvatarPainter(scene.getGalahad(), painter);
		PaintListener lancelot = new AnAvatarPainter(scene.getLancelot(), painter);
		PaintListener robin = new AnAvatarPainter(scene.getRobin(), painter);
		PaintListener guard = new AnAvatarPainter(scene.getGuard(), painter);
		PaintListener background = new ABackgroundPainter(scene, painter);
		
		BridgeSceneController bsController = new ABridgeSceneController(scene, (Component) painter);
		
		JFrame frame = new JFrame("Bridge Scene");
		frame.add((Component) painter);	
		frame.setSize(screenSize[0],screenSize[1]);
		frame.setVisible(true);
		
		BroadcastingClearanceManager clearanceManager = new ABroadcastingClearanceManager();
		CommandInterpreter interpreter = new ACommandInterpreter(scene, clearanceManager);
		
		OEFrame oeFrame = ObjectEditor.edit(clearanceManager); 
		OEFrame oeFrame2 = ObjectEditor.edit(interpreter); 
		
		interpreter.waitingArthur();
		interpreter.waitingGalahad();
		interpreter.waitingLancelot();
		interpreter.waitingRobin();
		
		clearanceManager.waitForProceed();
		clearanceManager.waitForProceed();
		interpreter.lockstepArthur();
		interpreter.lockstepGalahad();
		interpreter.lockstepLancelot();
		interpreter.lockstepRobin();
		interpreter.lockstepGuard();
		
		BroadcastingClearanceManager extendedGrammarClearanceManager = new ABroadcastingClearanceManager();
		
		OEFrame oeFrame3 = ObjectEditor.edit(extendedGrammarClearanceManager);
		
		extendedGrammarClearanceManager.waitForProceed();
		
		interpreter.lockstepArthur();
		interpreter.lockstepGalahad();
		interpreter.lockstepLancelot();
		interpreter.lockstepRobin();
		
		interpreter.setCommand("define guardArmsIn {rotateLeftArm guard -15 rotateRightArm guard 15}");
		interpreter.setCommand("define guardArmsOut {rotateLeftArm guard 15 rotateRightArm guard -15}");
		interpreter.setCommand("define beat {call guardArmsIn proceedAll sleep 1000 call guardArmsOut sleep 1000 ");
		interpreter.setCommand("define beats repeat 10 call beat");
		interpreter.setCommand("thread beats");
//		
//		CommandInterpreterView view = new ACommandInterpreterView(interpreter);
//		CommandInterpreterController controller = new ACommandInterpreterController(interpreter, bsController, view);
		
//		OEFrame oeFrame = ObjectEditor.edit(scene); 
//		oeFrame.setSize(screenSize[0],screenSize[1]);
		
//		PropertyChangeListener csv = new ConsoleSceneView(scene);
	
//		interpreter.asyncArthur();
//		interpreter.asyncArthur();
//		interpreter.asyncGalahad();
		
//		ThreadSupport.sleep(5000);
//		
//		interpreter.setCommand("move Arthur 20 30");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("approach Galahad");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("say \"Quest?\"");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("say \"To cross!\"");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("passed");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("approach Robin");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("{ move Arthur -20 -30 say \"Name?\" }");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("say \"let me through!\"");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("failed");
//		ThreadSupport.sleep(1000);
//		interpreter.setCommand("{ move Lancelot 30 -20 repeat 5 { move Arthur 20 5 move Galahad -15 -16 } move Lancelot 30 -20}");

//		ThreadSupport.sleep(1000);
//		scene.approach(scene.getArthur());
//		ThreadSupport.sleep(2000);
//		scene.say("Hey, you! Stop right there!");
//		ThreadSupport.sleep(2000);
//		scene.say("See ya!!!");
//		ThreadSupport.sleep(2000);
//		scene.say("Ok, fine, go!");
//		ThreadSupport.sleep(1000);
//		scene.passed();
//		ThreadSupport.sleep(3000);
//		interpreter.asyncArthur();
//		ThreadSupport.sleep(1000);
//		interpreter.asyncGalahad();
//		ThreadSupport.sleep(1000);
//		interpreter.asyncLancelot();
//		ThreadSupport.sleep(1000);
//		interpreter.asyncRobin();
//		ThreadSupport.sleep(1000);
//		interpreter.asyncGuard();

//		ThreadSupport.sleep(1000);
//		bridgeScene.getArthur().move(25, 170);
//		ThreadSupport.sleep(1000);
//		bridgeScene.getArthur().getText().setText("Look at me! Look at me!");
//		ThreadSupport.sleep(1000);
//		bridgeScene.getArthur().getArms().getLeftLine().rotate(80);
//		ThreadSupport.sleep(1000);
//		bridgeScene.getArthur().getArms().getRightLine().rotate(-80);
//		ThreadSupport.sleep(1000);
		
	}	
}
