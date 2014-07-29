package old.replicatedWindow;


import util.awt.ADelegateFrame;
import util.awt.AnInputQueue;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.CommunicatorCreator;
import util.session.ReceivedMessageListener;
import windowApp.CharacterDrawerLauncher;

public class ReplicatedCharacterDrawer {
	static String APPLICATION_NAME = "Character Drawer";
	public static void main (String[] args) {
		//MessageSender multicastClient = new AMulticastClient(args);
		CommunicatorCreator messageSenderFactory = CommunicatorSelector.getCommunicatorFactory();
		Communicator multicaster = messageSenderFactory.getCommunicator(args[0],args[1],args[2], APPLICATION_NAME);
		//ReceivedMessageListener remoteEventReceiver = new AReceivedEventForwarder(multicastClient);
		ReceivedMessageListener remoteEventForwarder = new AReceivedEventForwarder();
		multicaster.addReceivedMessageListener(remoteEventForwarder);
		ATelePointerManager telePointerManager = new ATelePointerManager();
		ReceivedMessageListener receivedTelePointerProcessor = new AReceivedTelePointerProcessor(telePointerManager);
		multicaster.addReceivedMessageListener(receivedTelePointerProcessor);
		//ADelegateEventQueue.getDelegateEventQueue().addEventQueueHandler(new ALocalEventForwarder());
		AnInputQueue.getEventQueue().addEventQueueHandler(new ASerializableEventBroadcaster(multicaster));
	
		AnInputQueue.getEventQueue().addEventQueueHandler(new ALocalMouseMotionListener(multicaster.getClientName(), telePointerManager ));
		//AMouseTracker mouseTracker = new AMouseTracker(telePointerManager, multicastClient);
		multicaster.join();
		printUserNames(multicaster);
		CharacterDrawerLauncher.createCharacterDrawer("Frame 1");
		CharacterDrawerLauncher.createCharacterDrawer("Frame 2");
		for (ADelegateFrame frame: ADelegateFrame.getAllFrames()) {
			frame.addPainter(telePointerManager);
		}
//		createCharacterDrawer("Frame 1", telePointerManager);
//		createCharacterDrawer("Frame 2", telePointerManager);
		
		//ObjectEditor.edit(multicaster);
	}
//	public static void createCharacterDrawer (String theTitle, ATelePointerManager telePointerManager) {
//		ADelegateFrame delegateFrame = new ADelegateFrame();
//		delegateFrame.setTitle(theTitle);
//		//delegateFrame.addMouseMotionListener(mouseTracker);
//		delegateFrame.addPainter(telePointerManager);
//		ACharacterDrawer characterDrawer =  new ACharacterDrawer(delegateFrame);
//		delegateFrame.setSize(300, 400);
//		delegateFrame.setVisible(true);			
//	}	
	static void printUserNames(Communicator multicaster) {
		try {
			String[] users = multicaster.getUserNames();
			String userString = "";
			for (int i = 0; i < users.length; i++) {
				userString += users[i];
			}
			System.out.println("Existing users in session:" + userString);
		} catch (Exception e) {
				
		}
	}
}
