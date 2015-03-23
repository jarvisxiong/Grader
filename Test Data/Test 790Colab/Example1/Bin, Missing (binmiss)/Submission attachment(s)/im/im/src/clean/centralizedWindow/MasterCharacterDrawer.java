package clean.centralizedWindow;

import java.util.List;

import old.replicatedWindow.ALocalMouseMotionListener;
import old.replicatedWindow.AReceivedEventForwarder;
import old.replicatedWindow.AReceivedTelePointerProcessor;
import old.replicatedWindow.ATelePointerManager;
import util.awt.ADelegateFrame;
import util.awt.AnInputQueue;
import util.awt.AnOutputQueue;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.CommunicatorCreator;
import util.session.ReceivedMessageListener;

public class MasterCharacterDrawer  {
	static String APPLICATION_NAME = "Character Drawer";
	public static void main (String[] args) {
		CommunicatorCreator multicasterFactory = CommunicatorSelector.getCommunicatorFactory();
		Communicator multicaster = multicasterFactory.getCommunicator(args[0],args[1],args[2], APPLICATION_NAME);
		ReceivedMessageListener remoteEventForwarder = new AReceivedEventForwarder();
		multicaster.addReceivedMessageListener(remoteEventForwarder);
		ATelePointerManager telePointerManager = new ATelePointerManager();
		ReceivedMessageListener receivedTelePointerProcessor = new AReceivedTelePointerProcessor(telePointerManager);
		multicaster.addReceivedMessageListener(receivedTelePointerProcessor);
		OutputLoggerAndListener outputLoggerAndListener = new AnOutputLoggerAndListener(multicaster);
		ReceivedMessageListener outputLogSender = new AnOutputLogSender(multicaster, outputLoggerAndListener);
		multicaster.addReceivedMessageListener(outputLogSender);
		AnInputQueue.addHandler(new ALocalMouseMotionListener(multicaster.getClientName(), telePointerManager ));
		AnInputQueue.addHandler(new AMasterInputHandler());
		AnOutputQueue.addOutputListener(outputLoggerAndListener);
		multicaster.join();
		createCharacterDrawer("Frame 1");
	}
	public static void interceptOutput(Communicator multicaster, OutputLoggerAndListener outputListener) {
		List<ADelegateFrame> allFrames = ADelegateFrame.getAllFrames();
		for (ADelegateFrame frame:allFrames) {
			frame.addOutputListener(outputListener);
		}
	}
	public static ADelegateFrame createCharacterDrawer(String theTitle) {
		ADelegateFrame frame = new ADelegateFrame(theTitle);
		new ACharacterDrawer(frame);
		frame.setSize(300, 200);
		frame.setVisible(true);
		return frame;
	}
}
