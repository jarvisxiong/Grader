package sasa.echoerAndIM;

import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.CommunicatorCreator;
import util.session.PeerMessageListener;

public class AnIMComposerAndLauncher {
	public static final String APPLICATION_NAME = "IM";
	public static final String DIRECT = "P2P";
	public static final String RELAYED = "Relayed";
	Communicator communicator;
	public static void main (String[] args) {
		(new AnIMComposerAndLauncher()).composeAndLaunch(args);
	}	
	protected String getApplicationName() {
		return APPLICATION_NAME;
	}	
	protected Echoer compose() {
		Echoer outCouplerAndEchoer =  new AnOutCoupledEchoer(communicator);
		addInCoupler(communicator, outCouplerAndEchoer);
		return outCouplerAndEchoer;
	}	
	public void composeAndLaunch(String[] args) {
		communicator = createCommunicator(args, getApplicationName());
		Echoer outCouplerAndEchoer = compose(); 
		communicator.join();
		outCouplerAndEchoer.doInput();	
	}
	
	public  void checkArgs(String[] args) {
		if (args.length < 3) {
			System.out.println("Please supply server host name, session name, and user name as main arguments");
			System.exit(-1);
		}
	}
	public  Communicator createCommunicator(String args[], String applicationName) {
		checkArgs(args);
		if (args.length == 4) {
			if (args[3].equalsIgnoreCase(DIRECT))
				CommunicatorSelector.selectDirectCommunicator();
			else if (args[3].equalsIgnoreCase(RELAYED))
				CommunicatorSelector.selectRelayerCommunicator();				
		}
		CommunicatorCreator communicatorFactory = CommunicatorSelector.getCommunicatorFactory();
		return  communicatorFactory.getCommunicator(args[0],args[1],args[2], applicationName);
	}
	public  void addInCoupler(Communicator communicator, Echoer echoerAndLogger) {
		PeerMessageListener remoteInputEchoer = new AnInCoupler(echoerAndLogger);
		communicator.addPeerMessageListener(remoteInputEchoer);
	}
}
