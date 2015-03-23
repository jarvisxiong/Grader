package replicatedWindow.jitter;

import replicatedWindow.AReplicatedWindowsComposerAndLauncher;
import replicatedWindow.AliceReplicatedWindows;
import replicatedWindow.CommunicatorBasedComposerAndLauncher;
import im.IMComposerAndLauncher;
import im.delay.p2p.CathyP2PIM;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.Tracer;

public class AliceJitteryReplicatedWindows extends AliceReplicatedWindows{
	public static final int DELAY_TO_CATHY = 1000;
	public static final int DELAY_VARIATION = 2000;


	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,  APPLICATION_NAME,  Communicator.DIRECT};
//		Tracer.showInfo(true);
//		IMTracerSetter.traceIM();
		CommunicatorBasedComposerAndLauncher aCommunicatorBasedComposerAndLauncher = 
				new AReplicatedWindowsComposerAndLauncher();
		aCommunicatorBasedComposerAndLauncher.composeAndLaunch(launcherArgs);
		delayToCathy(aCommunicatorBasedComposerAndLauncher.getCommunicator());
	}
	public static void delayToCathy(Communicator aCommunicator) {
		aCommunicator.setMinimumDelayToPeer(CathyP2PIM.USER_NAME,  DELAY_TO_CATHY);
		aCommunicator.setDelayVariation(DELAY_VARIATION);		
	}

}
