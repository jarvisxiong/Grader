package old.echoerAndIM;
import java.util.Collection;

import util.session.Communicator;
public class AnInputAndAccessControlMessageReceiver extends AnInputReceiver {
	AccessController accessController;
	public AnInputAndAccessControlMessageReceiver(Communicator theMessageSender, 
			AccessController theAccessController) {
		super(theMessageSender);
		accessController = theAccessController;
	}
	public void clientJoined(String userName, String theApplicationName, String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		super.clientJoined(userName, theApplicationName, theSessionName, newSession, newApplication, allUsers);
		if (newApplication) {
			accessController.setOwner(userName);
			accessController.addAdministratorLocal(userName);
			accessController.addInputterLocal(userName);
		}
		
	}
	public void objectReceived(Object message, String sourceName) {
		if (message instanceof AnInputAuthorization ) {
			processInputAuthorization((Message<String>) message);
		} else if (message instanceof AnAdministratorAuthorization) {
			processAdminAuthorization((Message<String>) message);
		} else
			super.objectReceived(message, sourceName);
	}
	
	void processInputAuthorization(Message<String> message) {
		accessController.addInputterLocal(message.getData());
	}
	void processAdminAuthorization(Message<String> message) {
		accessController.addAdministratorLocal(message.getData());
	}	
}
