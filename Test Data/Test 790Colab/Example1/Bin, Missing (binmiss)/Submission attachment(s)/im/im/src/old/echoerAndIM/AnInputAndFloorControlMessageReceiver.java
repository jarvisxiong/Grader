package old.echoerAndIM;

import java.util.Collection;

import util.session.Communicator;

public class AnInputAndFloorControlMessageReceiver extends AnInputAndAccessControlMessageReceiver {
	FloorController floorController;
	public AnInputAndFloorControlMessageReceiver(Communicator theMessageSender, 
			AccessController theAccessController,
			FloorController theFloorController) {
		super(theMessageSender, theAccessController);
		floorController = theFloorController;
	}
	public void objectReceived(Object message, String sourceName) {
		if (message instanceof AGetFloorMessage ) {
			processGetFloor((Message<String>) message);
		} else if (message instanceof AReleaseFloorMessage) {
			processReleaseFloor((Message<String>) message);
		} else
			super.objectReceived(message, sourceName);
	}
	public void clientJoined(String userName, String theApplicationName, String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		//System.out.println("User Joined in Floor receiver");
		super.clientJoined(userName, theApplicationName, theSessionName, newSession, newApplication, allUsers);
		if (newApplication) {
			floorController.getFloor();
		}		
	}
	
	void processGetFloor(Message<String> message) {
		floorController.newFloorHolder(message.getData());
	}
	void processReleaseFloor(Message<String> message) {
		floorController.newFloorHolder(null);
	}
}
