package grail.demo;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import javax.swing.JFrame;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.widgets.TextFieldSelector;
import grail.commander.CommandInterpreter;
import grail.controllers.BasicBridgeController;
import grail.factories.PersonalizedSwingTextFieldFactory;
import grail.factories.SingletonsCreator;
import grail.helpers.ClearanceManager;
import grail.helpers.ComponentBuilder;
import grail.paint.ObservablePainter;
import grail.scene.BridgeScene;
import grail.toolkit.BasicCommandController;
import grail.toolkit.ToolkitCommandViewer;
import grail.toolkit.AdvancedCommandViewer;
import util.annotations.PropertyNames;
import util.misc.ThreadSupport;

@PropertyNames({"Progress"})
public class Assignment12Demo extends BasicDemo implements TwelveDemo {
	int progress;
	private final String undo = "undo";
	private final String redo = "redo";
	
	public Assignment12Demo() {
		
	}
	
	public void run() {
		final int frameWidth = 1600;
		final int frameHeight = 1000;
		final int interpreterViewLocation = 600;
		progress = 0;
		
		//allows the factory to be set. 
		ObjectEditor.initialize();
		TextFieldSelector.setTextFieldFactory(new PersonalizedSwingTextFieldFactory());
		
		//build using SingletonCreator inside ComponenetBuilder
		ObservablePainter singleComponentModel = ComponentBuilder.buildObservablePainterFromScene();
		
		(new BasicBridgeController()).setModel(singleComponentModel);
		
		JFrame frame = new JFrame("Window");	
		frame.add(((Component) singleComponentModel));
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		AdvancedCommandViewer commandView = new ToolkitCommandViewer();
		new BasicCommandController(commandView);
		commandView.placeFrameOnTop();
		commandView.getFrame().setLocation(0, interpreterViewLocation);
		
		final int interpreterX = 1200;
		final int interpreterY = 700;
		final int editor2Width = 490;
		final int editor2Height = 125;
		OEFrame editor2 = ObjectEditor.edit(SingletonsCreator.commandInterpreterFactoryMethod());
		editor2.setLocation(interpreterX, interpreterY);
		editor2.setSize(editor2Width, editor2Height);
		
		
		final int clearanceX = 1200;
		final int clearanceY = 100;
		final int clearanceWidth = 325;
		final int clearanceHeight = 110;
		OEFrame editor3 = ObjectEditor.edit(SingletonsCreator.broadcastingClearnaceManagerMethod());
		editor3.setLocation(clearanceX, clearanceY);
		editor3.setSize(clearanceWidth, clearanceHeight);
		
		
		animateScene(SingletonsCreator.bridgeSceneFactoryMethod(), SingletonsCreator.commandInterpreterFactoryMethod(), SingletonsCreator.broadcastingClearnaceManagerMethod());
		
	}

	
	private void animateScene(BridgeScene scene, CommandInterpreter command, ClearanceManager manager) {
		final int reasonableWaitTime = 2000;
		final int waitingAnimationTime = 8000;
		final int lockstepAnimationTime = 12000;
		int progressOutOfFive = 0;
		
		//demonstrate wait
		command.waitingArthur();
		command.waitingGalahad();
		command.waitingLancelot();
		command.waitingRobin();
		scene.getGuard().setBasicText("Demonstrate waitAnimation");
		scene.getGalahad().setBasicText("Press ProceedAll to animate.");
		manager.waitForProceed();
		ThreadSupport.sleep(waitingAnimationTime);
		updateProgressBar(++progressOutOfFive);
		
		//demonstrate lockstep
		scene.setOriginalPositions();
		scene.getGuard().setBasicText("Time to demonstrate Lockstep!");
		scene.getArthur().setBasicText("We Move with claping");
		scene.getGalahad().setBasicText("Lets March!");
		ThreadSupport.sleep(reasonableWaitTime);
		command.lockstepArthur();
		command.lockstepGalahad();
		command.lockstepLancelot();
		command.lockstepRobin();
		command.lockstepGuard();
		ThreadSupport.sleep(lockstepAnimationTime);
		updateProgressBar(++progressOutOfFive);
		
		//demonstrate commands
		scene.setOriginalPositions();
		scene.getGuard().setBasicText("Now we will show each command going into the command interpreter.");
		scene.getLancelot().setBasicText("Press ProceedAll to see each command go in.");
		manager.waitForProceed();
		command.setCommand("approach arthur");
		manager.waitForProceed();
		command.setCommand("say \"Hello from the guard!\"");
		manager.waitForProceed();
		command.setCommand("say \"I'm passing!\"");
		manager.waitForProceed();
		command.setCommand("pass");
		manager.waitForProceed();
		command.setCommand("approach galahad");
		manager.waitForProceed();
		command.setCommand("say \"I'm Failing you!\"");
		manager.waitForProceed();
		command.setCommand("say \"What?!\"");
		manager.waitForProceed();
		command.setCommand("fail");
		manager.waitForProceed();
		scene.getGalahad().rebuild();
		scene.setOriginalPositions();
		scene.getGalahad().setBasicText("I'm Back!");
		scene.getArthur().setBasicText("I'm back too!");
		scene.getGuard().setBasicText("Time to demo Beats!");
		manager.waitForProceed();
		command.setCommand("define guardArmsIn { rotateLeftArm guard 7 rotateRightArm guard - 7 }");
		manager.waitForProceed();
		command.setCommand("define guardArmsOut { rotateLeftArm guard - 7 rotateRightArm guard 7 }");
		manager.waitForProceed();
		command.setCommand("define beat { call guardArmsIn sleep 1000 proceedAll call guardArmsOut sleep 1000 proceedAll }");
		manager.waitForProceed();
		command.setCommand("define beats repeat 5 call beat");
		command.lockstepArthur();
		command.lockstepGalahad();
		command.lockstepRobin();
		command.lockstepLancelot();
		scene.getGuard().setBasicText("The Next ProceedAll Will run the different beats");
		manager.waitForProceed();
		command.setCommand("thread beats");
		scene.getGuard().setBasicText("Doing Beats");
		ThreadSupport.sleep(lockstepAnimationTime);
		updateProgressBar(++progressOutOfFive);
		
		//Undo and Redo
		scene.setOriginalPositions();
		scene.getGuard().setBasicText("Next we will show basic commands with undo and redo");
		scene.getLancelot().setBasicText("Press ProceedAll to go through commands");
		manager.waitForProceed();
		scene.getGuard().setBasicText("Keep pushing ProceedAll!");
		scene.getLancelot().setBasicText("I'll show undo and redo");
		command.setCommand("move lancelot 100 100");
		manager.waitForProceed();
		command.setCommand(undo);
		manager.waitForProceed();
		command.setCommand(redo);
		manager.waitForProceed();
		command.setCommand(undo);
		manager.waitForProceed();
		scene.getArthur().setBasicText("We all move!");
		scene.getGalahad().setBasicText("Yay!");
		scene.getLancelot().setBasicText("We all move a lot!");
		scene.getRobin().setBasicText("Awesome!");
		command.setCommand("{ rotateRightArm arthur 5 rotateLeftArm arthur 5 move galahad 100 100 move lancelot 100 100 move robin 100 100 }");
		manager.waitForProceed();
		command.setCommand(undo);
		manager.waitForProceed();
		command.setCommand(redo);
		manager.waitForProceed();
		command.setCommand(undo);
		manager.waitForProceed();
		scene.getGuard().setBasicText("All done! Time to show Errors. Press ProceedAll");
		updateProgressBar(++progressOutOfFive);
		
		//show how errors are handled
		manager.waitForProceed();
		scene.getGuard().setBasicText("See how errors are handled. Keep pushing ProceedAll");
		command.setCommand("Invalid command and %$%#%@ token");
		manager.waitForProceed();
		command.setCommand("move arthur 100 turkey  bad syntax in move");
		manager.waitForProceed();
		command.setCommand("rotateRightArm BillyBob 100 100 bad syntax avatar does not exist");
		manager.waitForProceed();
		command.setCommand("move galahad 100 100 excess token and illegal tokens ign@red.");
		manager.waitForProceed();
		command.setCommand(undo);
		scene.getGalahad().setBasicText("Moving back");
		manager.waitForProceed();
		scene.getGuard().setBasicText("All Done with Demo! Enter commands to see it working.");
		command.setCommand("print");
		updateProgressBar(++progressOutOfFive);
		
	}
	
	private void updateProgressBar(int number) {
		final int progressIncrement = 20;
		int oldVal = this.progress;
		this.progress = number * progressIncrement;
		if (propertyListener != null) {
			propertyListener.notifyAllListeners(new PropertyChangeEvent(this,"Progress", oldVal, this.progress));
		}
		
	}
	
	
}
