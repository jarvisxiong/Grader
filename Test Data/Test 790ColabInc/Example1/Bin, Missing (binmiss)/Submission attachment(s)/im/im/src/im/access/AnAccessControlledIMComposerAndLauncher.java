package im.access;

import echo.modular.History;
import im.AReplicatedHistory;
import im.ReplicatedHistory;
import im.aware.ASessionAwarenessProvider;
import im.aware.AnAwareIMComposerAndLauncher;
import util.session.Communicator;
import util.session.ReceivedMessageListener;
import util.session.SessionMessageListener;
import bus.uigen.ObjectEditor;

public class AnAccessControlledIMComposerAndLauncher extends AnAwareIMComposerAndLauncher {	
	static String APPLICATION_NAME = "Access Controlled IM";	
	protected void addCollaborationFunctions() {
		super.addCollaborationFunctions();
		addAccessControl();	
	}
	protected History<String> createHistory() {
		return new AControlledReplicatedHistory<String>(communicator);
	}	
	public  void addAccessControl() {	
		AccessController accessController = new AnAccessController(communicator);		
		ReceivedMessageListener accessReceiver = new AnAccessReceiver(communicator, accessController);
		communicator.addReceivedMessageListener(accessReceiver);
		controlledReplicatedHistory().addVetoer(accessController);
		ObjectEditor.edit(accessController);

	}	
	protected ControlledReplicatedHistory<String> controlledReplicatedHistory() {
		return (ControlledReplicatedHistory<String>) history;
	}
	
	
}
