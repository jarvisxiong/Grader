package grail.demo;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.avatar.Avatar;
import grail.avatar.BasicAvatar;
import grail.commander.BasicCommandInterpreter;
import grail.commander.CommandInterpreter;
import grail.controllers.BasicBridgeController;
import grail.controllers.BridgeController;
import grail.helpers.AScannerBean;
import grail.helpers.ComponentBuilder;
import grail.interfaces.ScannerBean;
import grail.paint.ObservablePainter;
import grail.scene.BasicBridgeScene;
import grail.scene.BridgeScene;
import grail.toolkit.BasicCommandController;
import grail.toolkit.ToolkitCommandViewer;
import grail.view.BasicConsole;
import grail.toolkit.CommandController;
import grail.toolkit.AdvancedCommandViewer;
import util.annotations.PropertyNames;
import util.misc.ThreadSupport;

@PropertyNames({"Progress"})
public class Assignment10Demo extends BasicDemo implements NineDemo {
	
	private int progress;
	
	public Assignment10Demo() {
	  progress = 0;
	}
	
	@Override
	public void run() {
		final int frameWidth = 1600;
		final int frameHeight = 1000;
		final int interpreterViewLocation = 600;
		
		Avatar arthur = new BasicAvatar("arthur.jpg");
		arthur.setBodyColor(Color.YELLOW);
		arthur.setTextColor(Color.YELLOW);
		
		Avatar lancelot = new BasicAvatar("lancelot.jpg");
		lancelot.setBodyColor(Color.RED);
		lancelot.setTextColor(Color.RED);
		
		Avatar robin =  new BasicAvatar("robin.jpg");
		robin.setBodyColor(Color.CYAN);
		robin.setTextColor(Color.CYAN);
		
		Avatar galahad = new BasicAvatar("galahad.jpg");
		galahad.setBodyColor(Color.PINK);
		galahad.setTextColor(Color.PINK);
		
		Avatar guard = new BasicAvatar("guard.jpg");
		guard.setBodyColor(Color.DARK_GRAY);
		guard.setTextColor(Color.DARK_GRAY);
		
		BridgeScene scene = new BasicBridgeScene(arthur, lancelot, robin, galahad, guard);
			
		ObservablePainter singleComponentModel = ComponentBuilder.buildObservablePainterFromScene(scene);
		

		ScannerBean bean = new AScannerBean();
		CommandInterpreter commander = new BasicCommandInterpreter(scene, bean);
		
		AdvancedCommandViewer commandView = new ToolkitCommandViewer(commander);
		new BasicConsole(scene);

		CommandController commandControl = new BasicCommandController(commander,commandView);
		commandControl.setScene(scene);
		BridgeController bridgeControl = new BasicBridgeController(scene);
		bridgeControl.setModel(singleComponentModel);
		
		//Make frame and put ObservablePainter in the frame as a component

		
		OEFrame editor = ObjectEditor.edit(scene);
		editor.setSize(frameWidth,frameHeight);
		
		JFrame frame = new JFrame("Window");	
		frame.add(((Component) singleComponentModel));
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		commandView.placeFrameOnTop();
		commandView.getFrame().setLocation(0, interpreterViewLocation);
		
		animateScene(scene, commander);
		
	}

	
	private void animateScene(BridgeScene scene, CommandInterpreter command) {
		final int reasonableWaitTime = 2000;
		int progressOutOfSeven = 0;
		
		command.asynchronousGuard();
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(reasonableWaitTime);
		
		command.asynchronousArthur();
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(reasonableWaitTime);
		
		command.asynchronousGalahad();
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(reasonableWaitTime);
		
		command.asynchronousLancelot();
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(reasonableWaitTime);
		
		command.asynchronousRobin();
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(reasonableWaitTime);
		
		
		final int tenSeconds = 10000;
		ThreadSupport.sleep(tenSeconds);
		
		updateProgressBar(++progressOutOfSeven);
		scene.setOriginalPositions();
		scene.approach(scene.getArthur());
		
		ThreadSupport.sleep(reasonableWaitTime);
		updateProgressBar(++progressOutOfSeven);
		scene.say("I talked, so the Knight can't pass.");
	}
	
	private void updateProgressBar(int number) {
		final int progressIncrement = 14;
		int oldVal = this.progress;
		this.progress = number * progressIncrement;
		if (propertyListener != null) {
			propertyListener.notifyAllListeners(new PropertyChangeEvent(this,"Progress", oldVal, this.progress));
		}
		
	}
	
	public int getProgress() {return this.progress;}
	
	
}
