package im.delay.p2p;
import im.AnIMComposerAndLauncher;
import im.CathyIM;
import im.IMComposerAndLauncher;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.session.SessionTracerSetter;
public class CathyP2PIM  extends CathyIM {	
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, Communicator.DIRECT};
		IMComposerAndLauncher composerAndLauncher = (new AnIMComposerAndLauncher());
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();
		composerAndLauncher.composeAndLaunch(launcherArgs);
	}
	
}
