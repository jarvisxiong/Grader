package im.mixed;

import im.AnIMComposerAndLauncher;
import im.aware.ASessionAwarenessProvider;
import im.aware.AwareIMComposerAndLauncher;
import util.misc.ThreadSupport;
import util.session.Communicator;
import util.session.CommunicatorSelector;
import util.session.SessionMessageListener;

public class AP2PAndRelayedIMComposerAndLauncher extends AnIMComposerAndLauncher {
	public static void main (String[] args) {

		(new AP2PAndRelayedIMComposerAndLauncher()).composeAndLaunch(args);
		
	}
	
	
	public void compose(String[] args) {
		super.compose(args);
		Communicator relayerCommunicator =
				
		CommunicatorSelector.getRelayerCommunicator(args[0],
				args[1], args[2], args[3]);
		ThreadSupport.sleep(500); // need to get join notification, otherwise early event
		relayerCommunicator.toOthers("relayed communication");
	}
	
	
}
