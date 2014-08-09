package im.delay.p2p;
import im.AliceIM;
import im.AnIMComposerAndLauncher;
import im.IMComposerAndLauncher;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.session.SessionTracerSetter;
public class AliceP2PDelayingIM extends AliceIM {	
	public static final int DELAY_TO_CATHY = 20000;
	public static void main (String[] args) {
		IMComposerAndLauncher composerAndLauncher = (new AnIMComposerAndLauncher());
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();
		delayToCathyAndLaunch(composerAndLauncher);
	}
	public static void delayToCathyAndLaunch(IMComposerAndLauncher composerAndLauncher) {
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, IMComposerAndLauncher.DIRECT};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME, Communicator.DIRECT};

		composerAndLauncher.compose(launcherArgs);
		composerAndLauncher.getCommunicator().setMinimumDelayToPeer(CathyP2PIM.USER_NAME,  DELAY_TO_CATHY);
		composerAndLauncher.launch();
		
	}
	
}
