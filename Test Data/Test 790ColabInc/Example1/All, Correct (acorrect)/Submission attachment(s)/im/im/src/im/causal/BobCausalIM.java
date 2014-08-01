package im.causal;
import im.IMComposerAndLauncher;
import im.delay.p2p.BobP2PIM;
import trace.im.IMTracerSetter;
import util.session.Communicator;
public class BobCausalIM extends BobP2PIM {	
	public static void main (String[] args) {
		IMTracerSetter.traceIM();
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME, Communicator.DIRECT};
		(new ACausalIMComposerAndLauncher()).composeAndLaunch(launcherArgs);

	}
	
}
