package sasa.echoerAndIM;

import im.aware.ASessionAwarenessProvider;
import util.session.Communicator;
import util.session.SessionMessageListener;

public class AnAwareIMComposerAndLauncher extends AnIMComposerAndLauncher{
	
	public static void main (String[] args) {
//		Communicator communicator = createCommunicator(args, APPLICATION_NAME);
//		Echoer outCouplerAndEchoer =  new AnOutCoupledEchoer(communicator);
//		addAwarenessAndInCoupler(communicator, outCouplerAndEchoer);
//		communicator.join();
//		outCouplerAndEchoer.doInput();
		(new AnAwareIMComposerAndLauncher()).composeAndLaunch(args);
	}
	
	
	
	protected Echoer compose() {
		Echoer echoer =  super.compose();
		addAwareness(communicator);
		return echoer;
	}
	
//	public void composeAndLaunch(String[] args) {
//		communicator = createCommunicator(args, getApplicationName());
//		Echoer outCouplerAndEchoer = compose(); 
//				
////				new AnOutCoupledEchoer(communicator);
////		addAwarenessAndInCoupler(communicator, outCouplerAndEchoer);
////		addCollaborationFunctionality();
//		communicator.join();
//		outCouplerAndEchoer.doInput();	
//	}
//	
//	public  void checkArgs(String[] args) {
//		if (args.length < 3) {
//			System.out.println("Please supply server host name, session name, and user name as main arguments");
//			System.exit(-1);
//		}
//	}
//	public  Communicator createCommunicator(String args[], String applicationName) {
//		checkArgs(args);
//		if (args.length == 4) {
//			if (args[3].equalsIgnoreCase(DIRECT))
//				ACommunicatorSelector.selectDirectCommunicator();
//			else if (args[3].equalsIgnoreCase(RELAYED))
//				ACommunicatorSelector.selectRelayerCommunicator();				
//		}
////		ACommunicatorSelector.selectDirectCommunicator();
//		CommunicatorCreator communicatorFactory = ACommunicatorSelector.getCommunicatorFactory();
//		return  communicatorFactory.getCommunicator(args[0],args[1],args[2], applicationName);
//	}
	public  void addAwareness(Communicator communicator) {
//		PeerMessageListener remoteInputEchoer = new AnInCoupler(echoerAndLogger);
		SessionMessageListener sessionAwarenesManager = new ASessionAwarenessProvider();
//		communicator.addPeerMessageListener(remoteInputEchoer);
		communicator.addSessionMessageListener(sessionAwarenesManager);
	}
}
