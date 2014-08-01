package old.echoerAndIM;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.CommunicatorCreator;
import util.session.PeerMessageListener;
import util.session.SessionMessageListener;
public class AnIMComposerAndLauncher {
	public static final String APPLICATION_NAME = "IM";
	static Communicator communicator;
	public static void main (String[] args) {		
		Communicator communicator = createCommunicator(args, APPLICATION_NAME);
		Echoer outCouplerAndEchoer =  new AnOutCoupledEchoer(communicator);
		addAwarenessAndInCoupler(communicator, outCouplerAndEchoer);
		communicator.join();
		outCouplerAndEchoer.doInput();		
	}
	public static void checkArgs(String[] args) {
		if (args.length < 3) {
			System.out.println("Please supply server host name and user name as main argument");	
			System.exit(-1);
		}		
	}
	public static Communicator createCommunicator(String args[], String applicationName) {
		checkArgs(args);
		CommunicatorCreator communicatorFactory = CommunicatorSelector.getCommunicatorFactory();
		return  communicatorFactory.getCommunicator(args[0],args[1],args[2], applicationName);
	}	
	public static void addAwarenessAndInCoupler(Communicator communicator, Echoer echoerAndLogger) {		
		PeerMessageListener remoteInputEchoer = new AnInCoupler(echoerAndLogger);
		SessionMessageListener sessionAwarenesManager = new ASessionAwarenessProvider();	
		communicator.addPeerMessageListener(remoteInputEchoer);
		communicator.addSessionMessageListener(sessionAwarenesManager);
	}	
}
