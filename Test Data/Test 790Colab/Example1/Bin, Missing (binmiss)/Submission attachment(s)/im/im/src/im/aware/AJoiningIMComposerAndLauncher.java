package im.aware;

import im.AnIMComposerAndLauncher;
import util.session.Communicator;
import util.session.CommunicatorSelector;
import util.session.SessionMessageListener;

public class AJoiningIMComposerAndLauncher  {
	protected SessionMessageListener sessionJoiner;
	protected Communicator communicator;	
	String sessionServerHost;
	String userName;


	
	
	protected void addCollaborationFunctions() {
//		super.addCollaborationFunctions();
		addAwareness();
	}
	
	public  void addAwareness() {
		sessionJoiner = new ADynamicSessionJoiner(sessionServerHost, userName);
		communicator.addSessionMessageListener(sessionJoiner);
	}

	public SessionMessageListener getSessionAwarenesManager() {
		return sessionJoiner;
	}
	public void checkArgs(String[] args) {
		if (args.length < 5) {
			System.out.println("Please supply server host name, session name,  user name and application name as main arguments");
			System.exit(-1);
		}
	}
	public  Communicator createCommunicator(String args[]) {
		checkArgs(args);
		sessionServerHost = args[0];
		userName = args[2];
		return CommunicatorSelector.getCommunicator(args[0],args[1],args[2], args[3], args[4]);
//		CommunicatorCreator communicatorFactory = ACommunicatorSelector.getCommunicatorFactory();
//		return  communicatorFactory.getCommunicator(args[0],args[1],args[2], applicationName);
	}
	public void compose(String[] args) {
		communicator = createCommunicator(args);
		addCollaborationFunctions();
		communicator.join();
	}
	
}
