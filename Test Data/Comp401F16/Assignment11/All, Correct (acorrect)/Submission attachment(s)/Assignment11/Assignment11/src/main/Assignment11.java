package main;


//import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import grail.view.AConsoleSceneView;
import grail.view.ASceneView;
import grail.controller.ABridgeSceneController;
import grail.controller.BridgeSceneController;
import grail.controller.CommandInterpreterController;
import grail.controller.ACommandInterpreterController;
import grail.shape.Avatar;
import grail.shape.AvatarInterface;
import grail.view.ConsoleSceneView;
import grail.scanner.BeanStringScanner7;
import grail.scanner.CommandInterpreter;
import grail.scanner.CommandInterpreterInterface;
import grail.shape.BridgeScene;
import grail.shape.BridgeSceneInterface;
import grail.scanner.ScannerInterface;
import bus.uigen.ObjectEditor;
import bus.uigen.OEFrame;
import util.annotations.Tags;
import util.annotations.WebDocuments;
import util.misc.ThreadSupport;

@Tags({"ProgressBarCreator"})
//import lectures.animation.threads_commands.ThreadSupport;
/**
 * 
 * @author Dong Nie
 * dongnie@cs.unc.edu
 *
 *This is for assignment7 from comp 401
 */
public class Assignment11 {
	static JProgressBar bar;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		final int longSleepTime=2000;
		final int shortSleepTime=10;
		final int twenty=20;
		//animate bridge scene
		BridgeSceneInterface bsi=new BridgeScene();
		OEFrame sceneEditor = ObjectEditor.edit(bsi);
		final int width=1000,height=800;
		sceneEditor.setSize(width, height);
		//sceneEditor.refresh();
	
		ThreadSupport.sleep(longSleepTime);
		
		//console view
		ConsoleSceneView csv=new AConsoleSceneView(bsi);
		
		//animate approach scene
		//bsi.approachScene(bsi.getArthur());
		//sceneEditor.refresh();
		//ThreadSupport.sleep(longSleepTime);
		bar = new JProgressBar(0, 100);

		bar.setStringPainted(true);
		ASceneView view = new ASceneView(bsi);
		JFrame frame = new JFrame("View");
		JFrame frame5 = new JFrame("Progress");
		frame5.add(bar);
		frame5.setVisible(true);
		frame5.setSize(250, 50);
		frame5.setLocation(700, 700);
		bar.setValue(10);
		ThreadSupport.sleep(longSleepTime);
		JFrame frame2 = new JFrame("CommandWindow");
		frame2.setSize(300,300);
		JFrame frame3 = new JFrame("MoveMenu");
		JFrame frame4 = new JFrame("Button");
		frame.add(view);
		BridgeSceneController controller = new ABridgeSceneController(bsi, view);

		frame.setSize(width, height);
		frame.setVisible(true);

		ScannerInterface bss=new BeanStringScanner7();
		CommandInterpreterInterface cpi=new CommandInterpreter(bsi,bss);
		OEFrame commandEditor = ObjectEditor.edit(cpi);
		cpi.setCommand("say \"hi\"");
		//commandEditor.refresh();
		//scannerEditor.refresh();
		//sceneEditor.refresh();
		ThreadSupport.sleep(longSleepTime);
		
		
		JMenuItem menuItem = new JMenuItem("moveX");
	    JMenu menu = new JMenu("moveAvatar");
		// menu.setVisible(true);
		// menuItem.setVisible(true);
		JMenuBar menuBar = new JMenuBar();
		menu.add(menuItem);
		 menuBar.add(menu);
		// menuBar.setVisible(true);
		
		JTextField tf = new JTextField();
//tf.setSize(yibai, wushi);
		frame2.add(tf);
		frame2.setVisible(true);
		frame2.setLocation(300, 450);
		frame2.setJMenuBar(menuBar);
		
		JButton button = new JButton("Click button: moveY");
		button.setVisible(true);
		button.setSize(100, 100);
		frame3.setVisible(true);
		frame3.setSize(300, 100);
		frame3.add(button);
		frame3.setLocation(100, 150);
		
		

		
		
//		JButton sayButton=new JButton("say");
//		JFrame sayFrame = new JFrame("sayFrame");
//		sayButton.setVisible(true);
//		sayButton.setSize(100, 100);
//		sayFrame.setVisible(true);
//		sayFrame.setSize(300, 100);
//		sayFrame.add(sayButton);
//		sayFrame.setLocation(100, 250);
//
//		
//		JButton approachButton=new JButton("approach");
//		JFrame approachFrame = new JFrame("approachFrame");
//		approachButton.setVisible(true);
//		approachButton.setSize(100, 100);
//		approachFrame.setVisible(true);
//		approachFrame.setSize(300, 100);
//		approachFrame.add(approachButton);
//		approachFrame.setLocation(100, 350);
//		
//		JButton failButton=new JButton("fail");
//		JFrame failFrame = new JFrame("failFrame");
//		failButton.setVisible(true);
//		failButton.setSize(100, 100);
//		failFrame.setVisible(true);
//		failFrame.setSize(300, 100);
//		failFrame.add(failButton);
//		failFrame.setLocation(100, 450);
//
//		
//		JButton passButton=new JButton("pass");
//		JFrame passFrame = new JFrame("passFrame");
//		passButton.setVisible(true);
//		passButton.setSize(100, 100);
//		passFrame.setVisible(true);
//		passFrame.setSize(300, 100);
//		passFrame.add(passButton);
//		passFrame.setLocation(100, 550);

		
		cpi.setCommand("Move robin 20 20");
		//ACommandInterpreterController cic = new ACommandInterpreterController(cpi, tf, button, menuItem,approachButton, sayButton, passButton, failButton);
		//ACommandInterpreterController cic = new ACommandInterpreterController(cpi, tf, button, menuItem);
		bar.setValue(twenty);


//		for (int i = 0; i < 25; i++)
//		{
//			bsi.getArthur().getArms().getLeftLine().rotate(i + twenty);
//			bsi.getGuard().getArms().getLeftLine().rotate(i + twenty);
//			bsi.getRobin().getArms().getLeftLine().rotate(i + twenty);
//			bsi.getLancelot().getArms().getLeftLine().rotate(i + twenty);
//			bsi.getGalahad().getArms().getLeftLine().rotate(i + twenty);
//			ThreadSupport.sleep(shortSleepTime);
//		}
		
		ThreadSupport.sleep(longSleepTime);
		ThreadSupport.sleep(longSleepTime);

		//the asynchronization
		cpi.asynchronousArthur();
		cpi.asynchronousGalahad();
		cpi.asynchronousLancelot();
		cpi.asynchronousRobin();
		cpi.asynchronousGuard();
		ThreadSupport.sleep(longSleepTime);
		ThreadSupport.sleep(longSleepTime);

//animate the commands	
		bar.setValue(40);
		bsi.approachScene(bsi.getArthur());

		cpi.setCommand("repeat 5 move robin 20 20");
		ThreadSupport.sleep(longSleepTime);
		ThreadSupport.sleep(longSleepTime);
		cpi.setCommand("{move arthur 20 20 say \"hello\"}");
		ThreadSupport.sleep(longSleepTime);

//		bsi.sayScene("Hi man!");
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("NICE TO MEET U");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("you passed");
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("than you");
//		ThreadSupport.sleep(longSleepTime);
//		bsi.passScene();
//		ThreadSupport.sleep(longSleepTime);
//		final int sixty = 60;
//		bar.setValue(sixty);
//		
//		bsi.approachScene(bsi.getGalahad());
//
//		bsi.sayScene("Hi man!!");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("what");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("you fail");
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("why....it's unfair");
//		ThreadSupport.sleep(longSleepTime);
//		bsi.failScene();
//		ThreadSupport.sleep(longSleepTime);
//
//	
//		bar.setValue(80);
//		bsi.approachScene(bsi.getRobin());
//
//		bsi.sayScene("Hi guys");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("what?");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("you fail, haha");
//		ThreadSupport.sleep(longSleepTime);
//
//		bsi.sayScene("why....it's unfair");
//		ThreadSupport.sleep(longSleepTime);
//		bsi.failScene();
//
////		ThreadSupport.sleep(longSleepTime);
////		bsi.passScene();
//		ThreadSupport.sleep(longSleepTime);
//	
//		bar.setValue(90);
//		bsi.approachScene(bsi.getLancelot());
//
//		bsi.sayScene("Hi man");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("Nice to meet you");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.sayScene("you passed");
//
//		ThreadSupport.sleep(longSleepTime);
//		bsi.passScene();
//
////		ThreadSupport.sleep(longSleepTime);
////		bsi.passScene();
//		bar.setValue(100);

		
	}
	
	public static JProgressBar getProgressBar() {
		return bar;
	}
}
