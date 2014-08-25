package centralizedWindow;

import java.util.Collection;

import util.session.Communicator;
import util.session.ReceivedMessageListener;

public class AnApplicationLauncher implements ReceivedMessageListener{
	Communicator multicaster;
	OutputLoggerAndListener outputListenerAndLogger;
	public AnApplicationLauncher(Communicator theMulticaster, OutputLoggerAndListener theOutputListenerAndLogger) {
		multicaster = theMulticaster;
		outputListenerAndLogger = theOutputListenerAndLogger;
	}

	@Override
	public void objectReceived(Object message, String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientJoined(String userName, String theApplicationName,
			String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		if (userName.equals(multicaster.getClientName())) {
			MasterCharacterDrawer.createCharacterDrawer("Frame 1");
		    MasterCharacterDrawer.createCharacterDrawer("Frame 2");
		    MasterCharacterDrawer.interceptOutput(multicaster, outputListenerAndLogger);
		}
		
	}

	@Override
	public void clientLeft(String userName, String theApplicationName) {
		// TODO Auto-generated method stub
		
	}

}
