package main;


import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.ScannerBean;
import grail.ScannerBeanImpl;
//import mp.ACommandInterpreterView;
//import grail.ScannerBean;
//import grail.ScannerBeanImpl;
//import mp.ACommandInterpreter;
import mp.AConsoleView;
import mp.Scene.ABridgeSceneController;
import mp.Scene.AScenePainter;
import mp.Scene.BridgeScene;
import mp.Scene.BridgeSceneController;
import mp.Scene.TheBridgeScene;
import mp.command.ACommandInterpreter;
import mp.command.ACommandInterpreterController;
import mp.command.CommandInterpreter;
import mp.command.CommandInterpreterController;
import mp.command.CommandInterpreterView;
import util.annotations.Tags;
import util.misc.ThreadSupport;
@Tags({"ProgressBarCreator"})
public class Assignment10 {

	
	static JButton say=new JButton("Say Something");
	static JButton fail=new JButton("fail");
	static JButton pass=new JButton("pass");
	static JButton approach=new JButton("approach");

	
	
	public static void main(String[] args){
			final int windowW= 1100;
			final int windowH= 700;
			final int sleepTime2=3000;
			final int sleepTime1=200;
			final int wubai=500;
			final int yibai=100;
			final int sanbai=300;
			final int shi=10;
			final int wu=5;
			final int ershi=20;
			
			BridgeScene bridge= new TheBridgeScene();
			ScannerBean scan= new ScannerBeanImpl();
			CommandInterpreter command=new ACommandInterpreter(bridge,scan);
			PropertyChangeListener console=new AConsoleView(bridge);
			Component paint=new AScenePainter(bridge);
			BridgeSceneController aController=new ABridgeSceneController(bridge,paint);
			
			JFrame f1=new JFrame("A Bridge Scene View");
			JFrame commandI= new JFrame("A Command Interpreter");
			JFrame buttons=new JFrame("Buttons");
			JPanel approachP=new JPanel();
			JPanel sayP=new JPanel();
			JPanel passP=new JPanel();
			JPanel failP=new JPanel();
			JLabel comamndLabel= new JLabel("Command:");
			JTextField commandText=new JTextField();
			JPanel commandPanel=new JPanel();
			CommandInterpreterController aCIController=new ACommandInterpreterController(
					bridge,command,commandText,say,pass,fail,approach);
//			CommandInterpreterView error= new ACommandInterpreterView(command,errorText);
			
			
			
			
			
			f1.add((Component)paint);
			f1.setSize(windowW, windowH);
			f1.setVisible(true);
			
			
			commandPanel.setLayout(new GridLayout(1, 2));
			commandPanel.add(comamndLabel);
			commandPanel.add(commandText);
			commandI.add(commandPanel);
			commandI.setSize(wubai,yibai);
			commandI.setVisible(true);
			
			failP.setLayout(new GridLayout(1, 2));
			buttons.add(failP);
			failP.add(approach);
			failP.add(say);
			failP.add(pass);
			failP.add(fail);
			buttons.setSize(wubai,yibai);
			buttons.setVisible(true);
			
			
			bridge.approach(bridge.getGalahad());
			ThreadSupport.sleep(sleepTime2);
			bridge.sayScene("hahahahah");
			ThreadSupport.sleep(sleepTime2);
			bridge.sayScene("console should print precondition now");
			ThreadSupport.sleep(sleepTime2);
			bridge.passedScene();
			bridge.approach(bridge.getArthur());
			ThreadSupport.sleep(sleepTime2);
			bridge.failedScene();
			ThreadSupport.sleep(sleepTime2/2);
			command.asyncGuard();
			command.asyncArthur();
			command.asyncGalahad();
			command.asyncLancelot();
			command.asyncRobin();
			

	}
	
}
