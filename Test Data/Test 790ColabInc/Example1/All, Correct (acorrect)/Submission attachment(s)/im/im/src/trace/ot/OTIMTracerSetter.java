package trace.ot;

import trace.echo.modular.EchoTracerSetter;
import trace.im.IMTracerSetter;
import util.trace.Tracer;

public class OTIMTracerSetter extends IMTracerSetter {
	
	public static void traceOTIM() {
		Tracer.showInfo(true);
		EchoTracerSetter.setTraceParameters();
		setOTPrintStatus();		
	}
	
	public static void setOTPrintStatus() {
//		SessionTracerSetter.setSessionPrintStatus();
		IMTracerSetter.setIMPrintStatus();
		Tracer.setKeywordPrintStatus(ConcurrentEdits.class, true);
		Tracer.setKeywordPrintStatus(InitialOTTimeStampCreated.class, true);
		Tracer.setKeywordPrintStatus(LocalSiteCountIncremented.class, true);
		Tracer.setKeywordPrintStatus(OTListEditBuffered.class, true);
		Tracer.setKeywordPrintStatus(OTListEditCopied.class, true);
		Tracer.setKeywordPrintStatus(OTListEditFlipped.class, true);
		Tracer.setKeywordPrintStatus(OTListEditReceived.class, true);
		Tracer.setKeywordPrintStatus(OTListEditRemoteCountIncremented.class, true);
		Tracer.setKeywordPrintStatus(OTListEditSent.class, true);
		Tracer.setKeywordPrintStatus(TransformationOperands.class, true);
		Tracer.setKeywordPrintStatus(TransformationResult.class, true);


		






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
