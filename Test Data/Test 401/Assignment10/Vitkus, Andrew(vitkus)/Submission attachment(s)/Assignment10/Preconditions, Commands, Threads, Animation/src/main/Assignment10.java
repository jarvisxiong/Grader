package main;

//import test.TableTest;
import commandEngine.interpreter.IInterpreter;
import commandEngine.interpreter.Interpreter;
import commandEngine.tokenizer.Tokenizer;
import graphics.view.BridgeScene;
import graphics.view.BridgeSceneView;
import graphics.view.CommandInterpreterView;
import graphics.view.ConsoleSceneView;
import graphics.view.IBridgeScene;
import graphics.view.ICommandInterpreterView;
import graphics.view.ProgressMonitor;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment10 {
	public static void main(String[] args) {
		IBridgeScene bridgeScene = new BridgeScene();

		@SuppressWarnings("unused")
		ConsoleSceneView csv = new ConsoleSceneView(bridgeScene);

		IInterpreter interpreter = new Interpreter(bridgeScene);
		ProgressMonitor pm = new ProgressMonitor(14);
		
		CommandInterpreterView civ = new CommandInterpreterView(interpreter, bridgeScene);
		javax.swing.SwingUtilities.invokeLater(new BridgeSceneView(bridgeScene, civ));
		pm.stepComplete();
		
		javax.swing.SwingUtilities.invokeLater(civ);
		pm.stepComplete();
		
		//OEFrame editor2 = ObjectEditor.edit(interpreterView);
		OEFrame editor = ObjectEditor.edit(bridgeScene);
		bridgeScene.setOEFrame(editor);

		editor.hideMainPanel();
		editor.setSize(800, 500);
		pm.stepComplete();

		sleep(2000);
		
		interpreter.animateArthur();
		interpreter.animateGalahad();
		interpreter.animateLancelot();
		interpreter.animateRobin();
		interpreter.animateGuard();

		//interpreter.setOEFrame(editor2);
		

		interpreter.setCommand("sleep 5000");
		pm.stepComplete();
		interpreter.setCommand("move Arthur -50 100");
		pm.stepComplete();
		sleep(2000);
		interpreter.setCommand("move Lancelot 50 -20");
		pm.stepComplete();
		sleep(2000);
		interpreter.setCommand("approach Galahad");
		pm.stepComplete();
		sleep(2000);
		interpreter.setCommand("say \"What is your favorite color?\"");
		pm.stepComplete();
		sleep(4000);
		interpreter.setCommand("say \"Magenta!\"");
		pm.stepComplete();
		sleep(2000);
		
		bridgeScene.scaleAvatar(bridgeScene.getArthur(), 0.5);
		pm.stepComplete();
		sleep(2000);
		bridgeScene.angleBody(bridgeScene.getArthur(), -2. * Math.PI / 5., true);
		pm.stepComplete();
		sleep(2000);
		bridgeScene.angleNeck(bridgeScene.getArthur(), -3. * Math.PI / 4., false);
		pm.stepComplete();
		sleep(2000);
		interpreter.setCommand("pass");
		pm.stepComplete();

		/*
		 * TableTest tTest = new TableTest(bridgeScene);
		 * 
		 * OEFrame editor3 = ObjectEditor.edit(tTest);
		 * tTest.setOEFrame(editor3); tTest.runTest();
		 * 
		 * /*bridgeScene.approach(bridgeScene.getArthur()); sleep(1000);
		 * bridgeScene.fail();
		 * 
		 * bridgeScene.approach(bridgeScene.getRobin());
		 * bridgeScene.say("fail?"); sleep(1000); bridgeScene.say("fail.");
		 * sleep(1000); bridgeScene.fail();
		 * 
		 * 
		 * bridgeScene.approach(bridgeScene.getGalahad());
		 * bridgeScene.say("pass?"); sleep(1000); bridgeScene.say("pass");
		 * sleep(1000); bridgeScene.pass();
		 * 
		 * bridgeScene.approach(bridgeScene.getLancelot());
		 * bridgeScene.say("darn"); sleep(1000); bridgeScene.fail();
		 * 
		 * /*angleNeck(editor, bridgeScene.getArthur(), -Math.PI/5., true);
		 * angleBody(editor, bridgeScene.getArthur(), 9.*Math.PI/7., false);
		 * 
		 * //scaleAvatar(editor, bridgeScene.getGuard(), .5);
		 * 
		 * bridgeScene.getGalahad().setBodyColor(Color.BLUE);
		 * 
		 * IRotatingLine galahadLeftArm =
		 * bridgeScene.getGalahad().getArms().getLeftLine(); IRotatingLine
		 * galahadRightArm = bridgeScene.getGalahad().getArms().getRightLine();
		 * 
		 * for(int i = 1; i <= 30; i ++) { galahadLeftArm.rotate(1 + 5 / i);
		 * galahadRightArm.rotate(-1 + -5 / i); editor.refresh(); sleep(10); }
		 * 
		 * sleep(490);
		 * 
		 * say(editor, bridgeScene.getLancelot(), "I seek the holy grail!");
		 * sleep(500);
		 * 
		 * bridgeScene.getGalahad().setBodyColor(Color.YELLOW);
		 * 
		 * editor.refresh(); sleep(250);
		 * 
		 * //say(editor, bridgeScene.getGuard(), "I am half size!");
		 * //sleep(1000);
		 * 
		 * IRotatingLine robinLeftLeg =
		 * bridgeScene.getRobin().getLegs().getLeftLine(); IRotatingLine
		 * robinRightLeg = bridgeScene.getRobin().getLegs().getRightLine();
		 * 
		 * for(int i = 1; i <= 10; i ++) { robinLeftLeg.rotate(-1);
		 * robinRightLeg.rotate(1); bridgeScene.getRobin().move(1, 0);
		 * editor.refresh(); sleep(10); }
		 * 
		 * for(int i = 1; i <= 10; i ++) { robinLeftLeg.rotate(1);
		 * robinRightLeg.rotate(-1); bridgeScene.getRobin().move(1, 0);
		 * editor.refresh(); sleep(10); }
		 * 
		 * for(int i = 1; i <= 10; i ++) { robinLeftLeg.rotate(-1);
		 * robinRightLeg.rotate(1); bridgeScene.getRobin().move(1, 0);
		 * editor.refresh(); sleep(10); }
		 * 
		 * sleep(490);
		 * 
		 * bridgeScene.getLancelot().setTextColor(Color.WHITE);
		 * editor.refresh();
		 */

	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
