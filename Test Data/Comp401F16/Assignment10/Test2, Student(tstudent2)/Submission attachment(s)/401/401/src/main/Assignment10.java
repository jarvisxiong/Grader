package main;

import java.awt.Component;
import java.awt.Window;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import bus.uigen.OEFrame;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.misc.ThreadSupport;
import bus.uigen.ObjectEditor;
import mp.Avatar;
import mp.Circle;
import mp.Gorge;
import mp.Rectangle;


@PropertyNames({"Occupied", "KnightTurn"})
public class Assignment10 {
		static Avatar Arthur, Galahad, Lancelot, Robin, Guard;
		static Rectangle Bridge;
		static String Occupied; 
		static String KnightTurn = "FALSE";	
		static String input;
		
		public static void main(String[] args) throws InterruptedException{
					
			//Scanner reads, stores input into a String, & initiates new ScannerBean Object------------------------------------
			//Scanner scanner = new Scanner(System.in);		
			// Initializing Objects ---------------------------------------------------------------------------------------------------------
			
			ScannerBeanInterface scannerBean = new ScannerBean();
			BridgeSceneInterface Window = new BridgeScene();
			Command command = new CommandInterpreter(Window, scannerBean);
			InheritingBridgeScenePainter view = new InheritingBridgeScenePainter(Window);
			
			// MVC------------------------------------------------------------------------------
			
			ConsoleSceneViewInter scene = new ConsoleSceneView(Window);
			
			OEFrame editor = ObjectEditor.edit(Window);
			editor.setSize(1500, 1000);
			//sleep(2000);
			
			JFrame frame = new JFrame("A Scene View");
			frame.add((Component) view);
			frame.setSize(1000, 400);
			frame.setVisible(true);
			
			BridgeSceneController controller = new BridgeSceneController(Window, view);
			
			// Avatars------------------------------------------------------------------------------

			Arthur = Window.getArthur();
			Galahad = Window.getGalahad();
			Lancelot = Window.getLancelot();
			Robin = Window.getRobin();
			Guard = Window.getGuard();
			Bridge = Window.getGorge().getBridge();
			
			//Asynchronous(command);
			
			//Thread.sleep(9000);
			
			// Approaching Avatars------------------------------------------------------------------------------
			
			
			Window.approach(Galahad);
			Window.say("What is your quest?");
			Thread.sleep(1000);
			Window.say("To seek the Holy Grail.");
			Thread.sleep(1000);
			Window.say("Cool Beans");
			Thread.sleep(1000);
			Window.passed();
			Thread.sleep(1000);
			Window.approach(Robin);
			Window.say("What is your quest?");
			Thread.sleep(1000);
			Window.say("Wahey!");
			Thread.sleep(1000);
			Window.failed();
			Thread.sleep(3000);
			
			Asynchronous(command);
			Thread.sleep(9000);

			
						
			
			// Demo ---------------------------------------------------------------------------------------------------
			
			
			/*
			input = "say \"what is your quest?\"";
			command.setCommand(input);	
			Thread.sleep(1000);
			*/
			
			/*
			input = "move Arthur 326 42";
			command.setCommand(input);
			
			input = "move Robin 0 30";
			command.setCommand(input);
			
			input = "move galaHad 90 50";
			command.setCommand(input);
			
			input = "move lancelot -30 40";
			command.setCommand(input);
			
			
			Thread.sleep(1000);
			input = "say \"This was a test for Assignment 9\"";
			command.setCommand(input);		
			*/
			
			
			//================================================================================================================================================================================================================================================================================================
			
			}
		public static void Asynchronous(Command command){
			command.asynArthur();
			command.asynGalahad();
			command.asynLancelot();
			command.asynRobin();
		}
	
		public static void sleep(long interval) {
			try {
				Thread.sleep(interval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
