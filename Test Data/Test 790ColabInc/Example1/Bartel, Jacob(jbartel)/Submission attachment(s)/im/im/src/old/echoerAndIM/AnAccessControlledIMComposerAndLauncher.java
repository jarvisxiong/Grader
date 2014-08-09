package old.echoerAndIM;

import util.session.Communicator;
import util.session.ReceivedMessageListener;
import bus.uigen.ObjectEditor;

public class AnAccessControlledIMComposerAndLauncher extends AnIMComposerAndLauncher {	
	static String APPLICATION_NAME = "Access Controlled IM";	
	public static void main (String[] args) {
		Communicator communicator = createCommunicator(args, APPLICATION_NAME);		
		ControlDispatcher controlDispatcher =  new AControlDispatcher(communicator);
		addAwarenessAndInCoupler(communicator, controlDispatcher);
		AccessController accessController = new AnAccessController(communicator);		
		ReceivedMessageListener accessReceiver = new AnAccessReceiver(communicator, accessController);
		communicator.addReceivedMessageListener(accessReceiver);
		controlDispatcher.addVetoer(accessController);
		communicator.join();
		ObjectEditor.edit(accessController);
		controlDispatcher.doInput();		
	}
}
