package im.causal;
import im.IMComposerAndLauncher;
import im.delay.p2p.AliceP2PDelayingIM;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.TraceableInfo;
import util.trace.session.SessionTracerSetter;
public class AliceCausalIM extends AliceP2PDelayingIM {
	
	public static void main (String[] args) {
		IMTracerSetter.traceIM();
		
		TraceableInfo.setPrintThread(true);


//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, IMComposerAndLauncher.DIRECT};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME, Communicator.DIRECT};

		IMComposerAndLauncher composerAndLauncher = (new ACausalIMComposerAndLauncher());
		delayToCathyAndLaunch(composerAndLauncher);
	}
	
}
