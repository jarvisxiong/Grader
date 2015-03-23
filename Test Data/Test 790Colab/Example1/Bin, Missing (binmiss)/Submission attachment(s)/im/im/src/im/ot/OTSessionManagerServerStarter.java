package im.ot;
import trace.im.IMTracerSetter;
import trace.ot.OTIMTracerSetter;
import util.annotations.Tags;
import util.session.ServerSentMessageFilterSelector;
import util.session.ASessionManager;
import util.session.ASessionManagerSelector;
import util.session.ServerMessageFilterCreator;
import util.session.SessionManager;
import util.tags.DistributedTags;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Tracer;
import util.trace.session.ServerClientJoined;
import util.trace.session.SessionTracerSetter;
@Tags({DistributedTags.OT, DistributedTags.SERVER, DistributedTags.SESSION_MANAGER})
public class OTSessionManagerServerStarter {
	static ASessionManager server;
	public static void main (String[] args) {
		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, true);
		OTIMTracerSetter.setOTPrintStatus();
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
//		Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);
//		Tracer.showInfo(true);
//		IMTracerSetter.traceIM();
		Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);
//		SessionTracerSetter.setSessionPrintStatus();
		ServerMessageFilterCreator serverMessageQueueCreator = new AnOTServerMessageFilterCreator();
		ServerSentMessageFilterSelector.setMessageFilterFactory(serverMessageQueueCreator);
//		server = new ASessionManager();	
		server =  ASessionManagerSelector.getSessionManager();

		server.register();
	}
}
