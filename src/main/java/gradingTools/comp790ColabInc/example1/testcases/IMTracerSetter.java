package gradingTools.comp790ColabInc.example1.testcases;

import util.trace.Tracer;
import util.trace.session.ReceivedMessageDistributedToListeners;
import util.trace.session.SendDataRequest;

public class IMTracerSetter extends EchoTracerSetter{
	
	public static void traceIM() {
		Tracer.showInfo(true);
//		TraceableInfo.setPrintSource(true);
//		Traceable.setPrintTime(false);
//		Traceable.setPrintThread(true);
//		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		

//		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		EchoTracerSetter.setTraceParameters();
		setIMPrintStatus();		
	}
	
	public static void setIMPrintStatus() {
		

//		TraceableInfo.setPrintSource(true);
//		Traceable.setPrintTime(false);
//		Traceable.setPrintThread(true);
//		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		EchoTracerSetter.setEchoerPrintStatus();
		Tracer.setKeywordPrintStatus(SendDataRequest.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);

//		SessionTracerSetter.setSessionPrintStatus();
		Tracer.setKeywordPrintStatus(ListEditSent.class, true);
		Tracer.setKeywordPrintStatus(ListEditReceived.class, true);
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
