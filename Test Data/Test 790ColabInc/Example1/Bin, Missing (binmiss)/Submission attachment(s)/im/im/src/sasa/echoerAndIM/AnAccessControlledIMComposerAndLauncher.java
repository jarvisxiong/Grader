package sasa.echoerAndIM;

import old.echoerAndIM.AControlDispatcher;
import old.echoerAndIM.AccessController;
import old.echoerAndIM.AnAccessController;
import old.echoerAndIM.AnAccessReceiver;
import old.echoerAndIM.ControlDispatcher;
import util.session.Communicator;
import util.session.ReceivedMessageListener;
import bus.uigen.ObjectEditor;

public class AnAccessControlledIMComposerAndLauncher extends AnAwareIMComposerAndLauncher {	
	static String APPLICATION_NAME = "Access Controlled IM";	
//	public static void main (String[] args) {
//		Communicator communicator = createCommunicator(args, APPLICATION_NAME);		
//		ControlDispatcher controlDispatcher =  new AControlDispatcher(communicator);
//		addAwarenessAndInCoupler(communicator, controlDispatcher);
//		AccessController accessController = new AnAccessController(communicator);		
//		ReceivedMessageListener accessReceiver = new AnAccessReceiver(communicator, accessController);
//		communicator.addReceivedMessageListener(accessReceiver);
//		controlDispatcher.addVetoer(accessController);
//		communicator.join();
//		ObjectEditor.edit(accessController);
//		controlDispatcher.doInput();		
//	}
	protected Echoer compose() {
		Echoer outCouplerAndEchoer =  super.compose();
		ControlDispatcher controlDispatcher =  new AControlDispatcher(communicator);

		return outCouplerAndEchoer;
	}
	public void composeAndLaunch(String[] args) {
		Communicator communicator = createCommunicator(args, APPLICATION_NAME);		
		ControlDispatcher controlDispatcher =  new AControlDispatcher(communicator);
		addAwareness(communicator);
		AccessController accessController = new AnAccessController(communicator);		
		ReceivedMessageListener accessReceiver = new AnAccessReceiver(communicator, accessController);
		communicator.addReceivedMessageListener(accessReceiver);
		controlDispatcher.addVetoer(accessController);
		communicator.join();
		ObjectEditor.edit(accessController);
		controlDispatcher.doInput();		
	}
}
