package centralizedWindow;


import java.util.List;

import old.replicatedWindow.ALocalMouseMotionListener;
import old.replicatedWindow.AReceivedTelePointerProcessor;
import old.replicatedWindow.ATelePointerManager;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.CommunicatorCreator;
import util.session.ReceivedMessageListener;
//import bus.uigen.ObjectEditor;
import util.awt.ADelegateFrame;
import util.awt.AnInputQueue;

public class SlaverCharacterDrawer {
	static String APPLICATION_NAME = "Character Drawer";
	public static void launch (String[] args) {
		//MessageSender multicastClient = new AMulticastClient(args);
		CommunicatorCreator messageSenderFactory = CommunicatorSelector.getCommunicatorFactory();
		Communicator multicaster = messageSenderFactory.getCommunicator(args[0],args[1],args[2], APPLICATION_NAME);
		String masterName = args[3];
		//ReceivedMessageListener remoteEventReceiver = new AReceivedEventForwarder(multicastClient);
//		ReceivedMessageListener remoteEventForwarder = new AReceivedEventForwarder();
//		multicaster.addReceivedMessageListener(remoteEventForwarder);
		ATelePointerManager telePointerManager = new ATelePointerManager();
		ReceivedMessageListener receivedTelePointerProcessor = new AReceivedTelePointerProcessor(telePointerManager);
		multicaster.addReceivedMessageListener(receivedTelePointerProcessor);
		LogPainter logPainter = new ALogPainter();
		ReceivedMessageListener receivedRequestProcessor = new AReceivedRequestProcessor(logPainter);
		multicaster.addReceivedMessageListener(receivedRequestProcessor);
		//ADelegateEventQueue.getDelegateEventQueue().addEventQueueHandler(new ALocalEventForwarder());
		AnInputQueue.getEventQueue().addEventQueueHandler(new ASlaveInputHandler(masterName, multicaster));
	
		AnInputQueue.getEventQueue().addEventQueueHandler(new ALocalMouseMotionListener(multicaster.getClientName(), telePointerManager ));
		//AMouseTracker mouseTracker = new AMouseTracker(telePointerManager, multicastClient);
		multicaster.join();
		addPainter(logPainter);
		
		//ObjectEditor.edit(multicaster);
	}
	public static void addPainter(LogPainter logPainter) {
		
		List<ADelegateFrame> allFrames = ADelegateFrame.getAllFrames();
		for (ADelegateFrame frame:allFrames) {
			frame.addPainter(logPainter);
			
		}
	}
	public static void main (String[] args) {
		launch (new String[] {"localhost", "test",  "slave", "master"});	}
}
