package im.ot;
import im.IMComposerAndLauncher;
import im.causal.CathyCausalIM;
import trace.ot.OTIMTracerSetter;
import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
@Tags({DistributedTags.OT, ApplicationTags.IM, DistributedTags.CLIENT, DistributedTags.CLIENT_3})
public class CathyOTIM extends CathyCausalIM {
	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
//		OTIMTracerSetter.setOTPrintStatus();
		OTIMTracerSetter.traceOTIM();

//		SessionTracerSetter.setSessionPrintStatus();
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, true);

//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, IMComposerAndLauncher.RELAYED};
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME, IMComposerAndLauncher.RELAYED};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, APPLICATION_NAME,
				Communicator.RELAYED};
		IMComposerAndLauncher composerAndLauncher = (new AnOTIMComposerAndLauncher());
		composerAndLauncher.composeAndLaunch(launcherArgs);
	}
	
}
