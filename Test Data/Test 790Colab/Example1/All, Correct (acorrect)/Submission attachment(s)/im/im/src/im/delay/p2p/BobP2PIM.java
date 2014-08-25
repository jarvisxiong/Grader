package im.delay.p2p;
import im.AnIMComposerAndLauncher;
import im.BobIM;
import im.IMComposerAndLauncher;
import trace.im.IMTracerSetter;
import util.trace.session.SessionTracerSetter;
public class BobP2PIM extends BobIM {	
	public static void main (String[] args) {
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, IMComposerAndLauncher.DIRECT};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME};

		IMComposerAndLauncher composerAndLauncher = (new AnIMComposerAndLauncher());
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();
		composerAndLauncher.composeAndLaunch(launcherArgs);
	}
	
}
