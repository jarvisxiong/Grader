package main;

import java.awt.Component;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import mp.helpers.AConsoleSceneView;
import mp.painter.ABridgeSceneController;
import mp.painter.ACommandUi;
import util.misc.ThreadSupport;

public class Assignment10 {
	private ScannerBean scannerBean;
	
	public Assignment10(){
		scannerBean = new AScannerBean();
		run();
	}
	
	private void run(){
		final int width = 800;
		final int height = 600;
		final int scannerStringRefreshWait = 20;
		final double rotateAmount = Math.PI/scannerStringRefreshWait;
		final int waitTime = 2000;
		
		BridgeScene scene = new ABridgeScene();
		scene.getArthur().getText().setText("");
		
		Component painter = new ABridgeSceneController(scene);
		CommandInterpreter cmd = new ACommandInterpreter(scannerBean, scene);
		
		OEFrame editor =  ObjectEditor.edit(scene);
		PropertyChangeListener sceneView = new AConsoleSceneView(scene);
		editor.hideMainPanel();
		editor.setSize(width, height);	
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.add(painter);
		frame.setVisible(true);	
		
		JFrame frame2 = new JFrame();
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setLocationRelativeTo(null);
		frame2.add(new ACommandUi(cmd, scene));
		frame2.pack();
		frame2.setVisible(true);
	
		ThreadSupport.sleep(waitTime);
		scene.approach(scene.getArthur());
		ThreadSupport.sleep(waitTime);
		scene.say("I am a guard");
		ThreadSupport.sleep(waitTime);
		scene.say("I don't care");
		ThreadSupport.sleep(waitTime);
		scene.passed();
		ThreadSupport.sleep(waitTime);
		scene.approach(scene.getGalahad());
		ThreadSupport.sleep(waitTime);
		scene.failed();
		ThreadSupport.sleep(waitTime);
		scene.reset();
		
		cmd.animateArthur();
		cmd.animateGalahad();
		cmd.animateLancelot();
		cmd.animateRobin();
		cmd.animateGuard();
		
	}
	
	public static void main(String[] args){
		new Assignment10();
	}
}
