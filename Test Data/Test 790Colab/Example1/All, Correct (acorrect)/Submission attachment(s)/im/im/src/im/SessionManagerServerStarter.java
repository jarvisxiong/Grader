package im;

import trace.im.IMTracerSetter;
import util.annotations.Tags;
import util.session.ASessionManager;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Tracer;
import util.trace.session.ClientJoinFinished;
import util.trace.session.ServerClientJoined;
import util.trace.session.SessionTracerSetter;
@Tags({DistributedTags.SERVER, DistributedTags.SESSION_MANAGER, ApplicationTags.IM})
public class SessionManagerServerStarter {
	static ASessionManager server;
	public static void main (String[] args) {
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
//		Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);
		Tracer.showInfo(true);
		IMTracerSetter.traceIM();
		Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);

//		SessionTracerSetter.setSessionPrintStatus();
		
		server = new ASessionManager();
		server.register();
	}
}
