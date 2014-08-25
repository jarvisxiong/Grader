package trace.echo.modular;

import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.console.ConsoleError;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
import util.trace.console.ConsoleTraceSetter;
import util.trace.session.SessionTracerSetter;

public class EchoTracerSetter {
	
	public static void traceEchoer() {
//		Tracer.showInfo(true);
//		TraceableInfo.setPrintSource(true);
//		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		Tracer.showInfo(true);
//		TraceableInfo.setPrintSource(true);
//		Traceable.setPrintTime(false);
//		Traceable.setPrintThread(true);
//		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
//		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);

		EchoTracerSetter.setTraceParameters();
		setEchoerPrintStatus();		
	}
	
	public static void setTraceParameters() {
		TraceableInfo.setPrintSource(true);
		Traceable.setPrintTime(false);
		Traceable.setPrintThread(true);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
	}
	
	public static void setEchoerPrintStatus() {
//		TraceableInfo.setPrintSource(true);
//		Traceable.setPrintTime(false);
//		Traceable.setPrintThread(true);
//		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
//		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
//		SessionTracerSetter.setSessionPrintStatus();
//		ConsoleTraceSetter.traceConsole();	// needed for Echo Tracer
//		ConsoleTraceSetter.setConsolePrintStatus();	// needed for Echo Tracer

//		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setKeywordPrintStatus(ListEditInput.class, true);
		Tracer.setKeywordPrintStatus(ListEditNotified.class, true);
		Tracer.setKeywordPrintStatus(ListEditObserved.class, true);
		Tracer.setKeywordPrintStatus(ListEditMade.class, true);
//		Tracer.setKeywordPrintStatus(ConsoleOutput.class, true);
//		Tracer.setKeywordPrintStatus(ConsoleError.class, true);
//		Tracer.setKeywordPrintStatus(ConsoleInput.class, true);



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
