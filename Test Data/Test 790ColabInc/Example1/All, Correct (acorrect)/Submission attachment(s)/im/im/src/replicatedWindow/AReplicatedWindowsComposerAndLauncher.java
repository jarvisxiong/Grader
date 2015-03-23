package replicatedWindow;

import bus.uigen.ObjectEditor;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.PeerMessageListener;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
import echo.modular.AnEchoComposerAndLauncher;
import echo.modular.EchoerInteractor;
import echo.modular.History;
public class AReplicatedWindowsComposerAndLauncher implements CommunicatorBasedComposerAndLauncher  {
	public static final String DEFAULT_APPLICATION_NAME = "Shared Window System";

	protected Communicator communicator;	
	protected PeerMessageListener eventReceiver;
	protected ComponentRegistry componentRegistry;
	protected EventQueueHandler eventQueueHandler;
	

	
	
	/* (non-Javadoc)
	 * @see replicatedWindow.ReplicatedWindowsComposerAndLauncher#getApplicationName()
	 */
	@Override
	public String getApplicationName() {
		return DEFAULT_APPLICATION_NAME;
	}
	
	protected void addCollaborationFunctions() {
		addEventReceiver();
	}	
	protected void addApplications() {
		WidgetTesterLauncher.createFrameWithWidgets(getApplicationName() + " Widgets");
	}
	
	protected void createComponentRegistry() {
		componentRegistry = new AComponentRegistry();
	}
	/* (non-Javadoc)
	 * @see replicatedWindow.ReplicatedWindowsComposerAndLauncher#compose(java.lang.String[])
	 */
	@Override
	public void compose(String[] args) {
		createComponentRegistry();
		communicator = createCommunicator(args);
		addCollaborationFunctions();
		AnInputQueue.useAsEventQueue();
		addApplications();
		communicator.join();
		// we do not want to broadcast setup events
		// or early events
		addEventBroadcaster();

	}	
	
	/* (non-Javadoc)
	 * @see replicatedWindow.ReplicatedWindowsComposerAndLauncher#composeAndLaunch(java.lang.String[])
	 */
	@Override
	public void composeAndLaunch(String[] args) {
		compose(args);
	}

	/* (non-Javadoc)
	 * @see replicatedWindow.ReplicatedWindowsComposerAndLauncher#checkArgs(java.lang.String[])
	 */
	@Override
	public void checkArgs(String[] args) {
		if (args.length < 5) {
			System.out.println("Please supply server host name, session name,  user name and application name as main arguments");
			System.exit(-1);
		}
	}
	// parameters to factory
	/* (non-Javadoc)
	 * @see replicatedWindow.ReplicatedWindowsComposerAndLauncher#createCommunicator(java.lang.String[])
	 */
	@Override
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
	protected  void addEventReceiver() {
		eventReceiver = new AnEventQueueReceiver(componentRegistry);
		communicator.addPeerMessageListener(eventReceiver);
	}
	protected void addEventBroadcaster() {
		eventQueueHandler = new AnEventQueueBroadcaster(communicator, componentRegistry);
	}
	/* (non-Javadoc)
	 * @see replicatedWindow.ReplicatedWindowsComposerAndLauncher#getCommunicator()
	 */
	@Override
	public Communicator getCommunicator() {
		return communicator;
	}
	
	public static void main (String[] args) {
		Tracer.showInfo(true);
		SessionTracerSetter.setSessionPrintStatus();
		(new AReplicatedWindowsComposerAndLauncher()).composeAndLaunch(args);
	}
}
