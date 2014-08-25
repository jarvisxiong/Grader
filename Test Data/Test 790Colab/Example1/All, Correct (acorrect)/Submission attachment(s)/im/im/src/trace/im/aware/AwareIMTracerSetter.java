package trace.im.aware;

import trace.im.IMTracerSetter;
import util.trace.Tracer;
import util.trace.session.ClientJoinNotificationReceived;
import util.trace.session.ClientLeaveNotificationReceived;
import util.trace.session.SessionTracerSetter;

public class AwareIMTracerSetter {
	
	public static void traceAwareIM() {
		Tracer.showInfo(true);
		setAwareIMPrintStatus();		
	}
	
	public static void setAwareIMPrintStatus() {
//		SessionTracerSetter.setSessionPrintStatus();
		IMTracerSetter.setIMPrintStatus();
		Tracer.setKeywordPrintStatus(ClientJoinNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ClientLeaveNotificationReceived.class, true);

//		Tracer.setKeywordPrintStatus(ListEditSent.class, true);
//		Tracer.setKeywordPrintStatus(ListEditReceived.class, true);
//		Tracer.setKeywordPrintStatus(MessageInSendingQueue.class, true);
//		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
//		Tracer.setKeywordPrintStatus(MessageSent.class, true);
//		Tracer.setKeywordPrintStatus(SendMessageRequest.class, true);
//		Tracer.setKeywordPrintStatus(MessageReceived.class, true);
//		Tracer.setKeywordPrintStatus(MessageRetrievedFromReceivingQueue.class, true);
//		Tracer.setKeywordPrintStatus(ReceivedMessageDelayed.class, true);
//		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);





	}

}
