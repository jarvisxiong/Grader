package im.aware;

import im.AnIMComposerAndLauncher;
import util.session.SessionMessageListener;

public class AJoiningAwareIMComposerAndLauncher extends AnIMComposerAndLauncher implements AwareIMComposerAndLauncher{
	protected SessionMessageListener sessionAwarenesManager;
	public static void main (String[] args) {

		(new AJoiningAwareIMComposerAndLauncher()).composeAndLaunch(args);
	}
	
	protected void addCollaborationFunctions() {
		super.addCollaborationFunctions();
		addAwareness();
	}
	
	public  void addAwareness() {
		sessionAwarenesManager = new ASessionAwarenessProvider();
		communicator.addSessionMessageListener(sessionAwarenesManager);
	}

	public SessionMessageListener getSessionAwarenesManager() {
		return sessionAwarenesManager;
	}
	
}
