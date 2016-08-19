package main;

import java.awt.Component;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import mp.helpers.ABroadcastingClearanceManager;
import mp.helpers.AConsoleSceneView;
import mp.helpers.BroadcastingClearanceManager;
import mp.painter.ABridgeSceneController;
import mp.painter.ACommandUi;
import util.misc.ThreadSupport;

public class Assignment12 {
	private ScannerBean scannerBean;
	
	public Assignment12(){
		scannerBean = new AScannerBean();
		run();
	}
	
	private void run(){
		final int width = 800;
		final int height = 600;
		final int scannerStringRefreshWait = 20;
		final double rotateAmount = Math.PI/scannerStringRefreshWait;
		final int waitTime = 1000;
		
		BridgeScene scene = ASingletonsCreator.getBridgeScene();
		scene.getArthur().getText().setText("");
		BroadcastingClearanceManager clearanceManager = ASingletonsCreator.getClearanceManager();
		
		Component painter = new ABridgeSceneController(scene);
		CommandInterpreter cmd = ASingletonsCreator.getCommandInterpreter();
		
		OEFrame editor =  ObjectEditor.edit(scene);
		editor.hideMainPanel();
		editor.setSize(width, height);	
		PropertyChangeListener sceneView = new AConsoleSceneView(scene);
		
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
	
		editor = ObjectEditor.edit(cmd);
		editor = ObjectEditor.edit(clearanceManager);
		
		cmd.waitingArthur();
		cmd.waitingGalahad();
		cmd.waitingLancelot();
		cmd.waitingRobin();
	}
	
	public static void main(String[] args){
		new Assignment12();
	}
}
