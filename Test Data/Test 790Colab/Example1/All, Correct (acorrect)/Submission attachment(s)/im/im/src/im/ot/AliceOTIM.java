package im.ot;
import im.IMComposerAndLauncher;
import im.causal.AliceCausalIM;
import trace.ot.OTIMTracerSetter;
import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;
import util.trace.session.MessagePutInQueue;
import util.trace.session.MessageRetrievedFromQueue;
import util.trace.session.MessageSent;
import util.trace.session.ReceivedMessageDelayed;
import util.trace.session.ReceivedMessageDistributedToListeners;
import util.trace.session.SendDataRequest;
import util.trace.session.SentMessageDelayed;
import util.trace.session.SessionTracerSetter;
@Tags({DistributedTags.OT, ApplicationTags.IM, DistributedTags.CLIENT, DistributedTags.CLIENT_1})
public class AliceOTIM extends AliceCausalIM {
	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		OTIMTracerSetter.traceOTIM();
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, true);
//		OTIMTracerSetter.setOTPrintStatus();
//		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
//		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
//		Tracer.setKeywordPrintStatus(ReceivedMessageDelayed.class, true);
//		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);
//		Tracer.setKeywordPrintStatus(MessagePutInQueue.class, true);
//		Tracer.setKeywordPrintStatus(MessageRetrievedFromQueue.class, true);
//		Tracer.setKeywordPrintStatus(MessageSent.class, true);

//		SessionTracerSetter.setSessionPrintStatus();
		

		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, APPLICATION_NAME, Communicator.RELAYED};
		IMComposerAndLauncher composerAndLauncher = (new AnOTIMComposerAndLauncher());
		composerAndLauncher.composeAndLaunch(launcherArgs);
	}
	
}
