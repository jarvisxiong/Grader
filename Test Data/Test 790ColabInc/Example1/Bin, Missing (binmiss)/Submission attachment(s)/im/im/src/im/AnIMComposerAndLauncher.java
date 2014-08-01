package im;

import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.PeerMessageListener;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
import echo.modular.AnEchoComposerAndLauncher;
import echo.modular.EchoerInteractor;
import echo.modular.History;
public class AnIMComposerAndLauncher extends AnEchoComposerAndLauncher implements IMComposerAndLauncher{
	public static final String DEFAULT_APPLICATION_NAME = "IM";

	protected Communicator communicator;	
	protected PeerMessageListener inCoupler;
	
//	public void composeAndLaunch(String[] args) {
//		communicator = createCommunicator(args, getApplicationName());
//		super.composeAndLaunch(args);
//	}
	
	
	public String getApplicationName() {
		return DEFAULT_APPLICATION_NAME;
	}
	protected History<String> createHistory() {
		return new AReplicatedHistory<String>(communicator);
	}
	// factory method
	protected EchoerInteractor createInteractor() {
		return new AnIMInteractor((ReplicatedHistory) history, communicator);
	}	
	protected void addCollaborationFunctions() {
		addInCoupler(communicator, history);
	}	
	public void compose(String[] args) {
		communicator = createCommunicator(args);
		super.compose(args);
		addCollaborationFunctions();
		communicator.join();
	}	

	public void checkArgs(String[] args) {
		if (args.length < 5) {
			System.out.println("Please supply server host name, session name,  user name and application name as main arguments");
			System.exit(-1);
		}
	}
	// parameters to factory
	public  Communicator createCommunicator(String args[]) {
		checkArgs(args);
		if (args.length == 5) {
			if (args[4].equalsIgnoreCase(Communicator.DIRECT))
				CommunicatorSelector.selectDirectCommunicator();
			else if (args[4].equalsIgnoreCase(Communicator.RELAYED))
				CommunicatorSelector.selectRelayerCommunicator();				
		}
		return CommunicatorSelector.getCommunicator(args[0],args[1],args[2], args[3]);
//		CommunicatorCreator communicatorFactory = ACommunicatorSelector.getCommunicatorFactory();
//		return  communicatorFactory.getCommunicator(args[0],args[1],args[2], applicationName);
	}
	protected  void addInCoupler(Communicator communicator, History<String> echoerAndLogger) {
		inCoupler = new AHistoryInCoupler(echoerAndLogger);
		communicator.addPeerMessageListener(inCoupler);
	}
	public Communicator getCommunicator() {
		return communicator;
	}
	public PeerMessageListener getRemoteInputEchoer() {
		return inCoupler;
	}

	public static void main (String[] args) {
		Tracer.showInfo(true);
		SessionTracerSetter.setSessionPrintStatus();
		(new AnIMComposerAndLauncher()).composeAndLaunch(args);
	}
}
