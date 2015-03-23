package im.causal;
import im.IMComposerAndLauncher;
import im.delay.p2p.CathyP2PIM;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.session.MessageGivenToFilter;
import util.trace.session.MessageReceived;
import util.trace.session.ProcessInfo;
import util.trace.session.ReceivedMessageDistributedToListeners;
import util.trace.session.SessionTracerSetter;
public class CathyCausalIM extends CathyP2PIM {
	
	public static void main (String[] args) {
		Tracer.showInfo(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		TraceableInfo.setPrintSource(false);
		TraceableInfo.setPrintThread(false);
		ProcessInfo.setShowProcessName(false);
//		SessionTracerSetter.setSessionPrintStatus();

		Traceable.setPrintTime(false);
//		Traceable.setPrintThread(true);
		Tracer.setMessagePrefixKind(MessagePrefixKind.SHORT_CLASS_NAME);
//		Tracer.setKeywordPrintStatus(MessageReceived.class, true);
		Tracer.setKeywordPrintStatus(MessageGivenToFilter.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);
//		IMTracerSetter.traceIM();
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME, Communicator.DIRECT};

		(new ACausalIMComposerAndLauncher()).composeAndLaunch(launcherArgs);

	}
	
}
