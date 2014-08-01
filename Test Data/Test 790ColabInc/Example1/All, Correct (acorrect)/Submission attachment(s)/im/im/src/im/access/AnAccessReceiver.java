package im.access;

import java.util.Collection;

import util.session.Communicator;
import util.session.ReceivedMessageListener;

public class AnAccessReceiver implements ReceivedMessageListener {
	Communicator communicator;
	AccessController accessController;
	public AnAccessReceiver(Communicator theCommunicator,
			AccessController theAccessController) {
		communicator = theCommunicator;
		accessController = theAccessController;
	}
	public void clientJoined(
			String userName, String theApplicationName, 
			String theSessionName, boolean newSession, 
			boolean newApplication, Collection<String> allUsers) {
		if (newApplication) {
			accessController.setOwner(userName);
			accessController.addAdministratorLocal(userName);
			accessController.addInputterLocal(userName);
		}
	}
	public void objectReceived(Object message, String sourceName) {
		if (message instanceof AnInputAuthorization)
			processInputAuthorization((Message<String>) message);
		else if (message instanceof AnAdministratorAuthorization)
			processAdminAuthorization((Message<String>) message);
	}
	void processInputAuthorization(Message<String> message) {
		accessController.addInputterLocal(message.getData());
	}
	void processAdminAuthorization(Message<String> message) {
		accessController.addAdministratorLocal(message.getData());
	}
	public void clientLeft(String userName, String theApplicationName) {}
}
