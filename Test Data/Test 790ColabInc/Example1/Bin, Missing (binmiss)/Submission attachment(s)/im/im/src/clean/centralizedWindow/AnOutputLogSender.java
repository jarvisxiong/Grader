package clean.centralizedWindow;

import java.util.Collection;

import util.session.Communicator;
import util.session.ReceivedMessageListener;

public class AnOutputLogSender implements ReceivedMessageListener {
	Communicator multicaster;
	OutputLoggerAndListener outputLogger;
	public AnOutputLogSender(Communicator theMulticaster, OutputLoggerAndListener theOutputLogger) {
		multicaster = theMulticaster;
		outputLogger = theOutputLogger;
	}
	public void objectReceived(Object message, String userName) {}
	public void clientJoined(String userName, String theApplicationName,
			String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		if (!userName.equals(multicaster.getClientName()))
		    multicaster.toClient(userName, outputLogger.getOutputLog());
	}
	public void clientLeft(String userName, String theApplicationName) {}
}
