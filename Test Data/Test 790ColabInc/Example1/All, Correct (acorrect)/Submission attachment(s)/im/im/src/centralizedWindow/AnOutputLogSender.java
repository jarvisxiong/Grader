package centralizedWindow;

import java.util.Collection;

import util.session.Communicator;
import util.session.ReceivedMessageListener;

public class AnOutputLogSender implements ReceivedMessageListener {
	Communicator multicaster;
	OutputLoggerAndListener outputLogger;
	public AnOutputLogSender(Communicator theMulticaster, 
			OutputLoggerAndListener theOutputLogger) {
		multicaster = theMulticaster;
		outputLogger = theOutputLogger;
	}
	@Override
	public void objectReceived(Object message, String userName) {
				
	}

	@Override
	public void clientJoined(String userName, String theApplicationName,
			String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
//		if (userName.equals(multicaster.getUserName())) {
//			MasterCharacterDrawer.createCharacterDrawer("Frame 1");
//		    MasterCharacterDrawer.createCharacterDrawer("Frame 2");
//		    MasterCharacterDrawer.interceptOutput(multicaster, outputLogger);
//		} else
		if (!userName.equals(multicaster.getClientName()))
		    multicaster.toClient(userName, outputLogger.getOutputLog());		
	}

	@Override
	public void clientLeft(String userName, String theApplicationName) {
		
	}

}
