package centralizedWindow;


import java.util.List;

import old.replicatedWindow.ALocalMouseMotionListener;
import old.replicatedWindow.AReceivedEventForwarder;
import old.replicatedWindow.AReceivedTelePointerProcessor;
import old.replicatedWindow.ATelePointerManager;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.CommunicatorCreator;
import util.session.ReceivedMessageListener;
//import bus.uigen.ObjectEditor;
import util.awt.ADelegateFrame;
import util.awt.AnInputQueue;
import util.awt.AnOutputQueue;
import widgets.TextFieldLauncher;

public class MasterCharacterDrawer  {
	static String APPLICATION_NAME = "Character Drawer";
	public static void launch (String[] args) {
		CommunicatorCreator multicasterFactory = CommunicatorSelector.getCommunicatorFactory();
		Communicator multicaster = multicasterFactory.getCommunicator(args[0],args[1],args[2], APPLICATION_NAME);
		ReceivedMessageListener remoteEventForwarder = new AReceivedEventForwarder();
		multicaster.addReceivedMessageListener(remoteEventForwarder);
		ATelePointerManager telePointerManager = new ATelePointerManager();
		ReceivedMessageListener receivedTelePointerProcessor = new AReceivedTelePointerProcessor(telePointerManager);
		multicaster.addReceivedMessageListener(receivedTelePointerProcessor);
		//ADelegateEventQueue.getDelegateEventQueue().addEventQueueHandler(new ASerializableEventBroadcaster(multicaster));
		OutputLoggerAndListener outputLoggerAndListener = new AnOutputLoggerAndListener(multicaster);
		ReceivedMessageListener outputLogSender = new AnOutputLogSender(multicaster, outputLoggerAndListener);
		multicaster.addReceivedMessageListener(outputLogSender);
//		ReceivedMessageListener applicationLauncher = new AnApplicationLauncher(multicaster,outputLoggerAndListener);
//		multicaster.addReceivedMessageListener(applicationLauncher);
		//AnInputQueue.getEventQueue().addEventQueueHandler(new ALocalMouseMotionListener(multicaster.getUserName(), telePointerManager ));
		AnInputQueue.addHandler(new ALocalMouseMotionListener(multicaster.getClientName(), telePointerManager ));
		AnInputQueue.addHandler(new AMasterInputHandler());
		AnOutputQueue.addOutputListener(outputLoggerAndListener);
		multicaster.join();
		createCharacterDrawer("Frame 1");
		TextFieldLauncher.createTextField("text field");
	    //createCharacterDrawer("Frame 2");
//		createCharacterDrawer("Frame 1");
//		createCharacterDrawer("Frame 2");
//		interceptOutput(multicaster, outputLoggerAndListener);
//		createCharacterDrawer("Frame 1", telePointerManager);
//		createCharacterDrawer("Frame 2", telePointerManager);
//		
		//ObjectEditor.edit(multicaster);
	}
	public static void interceptOutput(Communicator multicaster, OutputLoggerAndListener outputListener) {
		
		List<ADelegateFrame> allFrames = ADelegateFrame.getAllFrames();
		for (ADelegateFrame frame:allFrames) {
			frame.addOutputListener(outputListener);
			
		}
	}
//	public static void createCharacterDrawer (String theTitle, ATelePointerManager telePointerManager) {
//		ADelegateFrame delegateFrame = new ADelegateFrame();
//		delegateFrame.setTitle(theTitle);
//		delegateFrame.addPainter(telePointerManager);
//		ACharacterDrawer characterDrawer =  new ACharacterDrawer(delegateFrame);
//		delegateFrame.setSize(300, 400);
//		delegateFrame.setVisible(true);			
//	}	
	public static ADelegateFrame createCharacterDrawer(String theTitle) {
		ADelegateFrame frame = new ADelegateFrame(theTitle);
		//frame.setTitle(theTitle);
		new ACharacterDrawer(frame);
		frame.setSize(300, 200);
		frame.setVisible(true);		
		return frame;
	}
	public static void main (String[] args) {
		launch (new String[] {"localhost", "test",  "master"});
	}
}
