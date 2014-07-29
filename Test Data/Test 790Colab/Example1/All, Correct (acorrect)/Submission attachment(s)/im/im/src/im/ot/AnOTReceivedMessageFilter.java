package im.ot;

import trace.echo.modular.ListEditInfo;
import trace.ot.OTTimeStampInfo;
import trace.ot.OTListEditReceived;
import trace.ot.OTListEditFlipped;
import util.session.CommunicatorSelector;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;

public class AnOTReceivedMessageFilter implements MessageFilter<ReceivedMessage> {
	OTManager otManager;
	MessageProcessor<ReceivedMessage> receivedMessageQueue;
	public AnOTReceivedMessageFilter(OTManager theOTManager) {
		otManager = theOTManager;
	}
	@Override
	public void filterMessage(ReceivedMessage message) {
		if (!message.isUserMessage() ) {
			receivedMessageQueue.processMessage(message);
			return;
		}		
		ListEditWithOTTimeStamp receivedTSEdit = 
			(ListEditWithOTTimeStamp) message.getUserMessage();	
		String fromUser = message.getClientName();
		
		traceOTEditReceive(receivedTSEdit, fromUser, false, this);
		receivedTSEdit.getOTTimeStamp().flip(); // our OT function compares local with local now
		traceOTEditFlip(receivedTSEdit, fromUser, false, this);

		
//		// start notify tracer
//		OTTimeStampInfo otTimeStampInfo = receivedTSEdit.getOTTimeStamp().toOTTimeStampInfo();		
//		ListEditInfo editInfo = receivedTSEdit.getTransformableListEdit().getListEdit().toListEditInfo();
//		UserOTTimeStampedListEditReceived.newCase(
//				ACommunicatorSelector.getProcessName(),
//				editInfo,
//				otTimeStampInfo,
//				fromUser,
//				receivedTSEdit.getTransformableListEdit().isServer(), // will always be true
//				this);
//		// end notify tracer
		// client receives edit from server
		otManager.processTimeStampedEdit(receivedTSEdit, fromUser, true);		
		message.setUserMessage(receivedTSEdit.getTransformableListEdit().getListEdit());		
		receivedMessageQueue.processMessage(message);		
	}
	@Override
	public void setMessageProcessor(MessageProcessor<ReceivedMessage> theMesssageProcessor) {
		receivedMessageQueue = theMesssageProcessor;		
	}
	
	public static void traceOTEditReceive(ListEditWithOTTimeStamp anOTEdit, String aUser, boolean isServer, Object aFinder) {
		OTTimeStampInfo otTimeStampInfo = anOTEdit.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo = anOTEdit.getTransformableListEdit().getListEdit().toListEditInfo();
		OTListEditReceived.newCase(
				CommunicatorSelector.getProcessName(),
				editInfo,
				otTimeStampInfo,
				aUser,
//				isServer,
				aFinder);
	}
	
	public static void traceOTEditFlip(ListEditWithOTTimeStamp anOTEdit, String aUser, boolean isServer, Object aFinder) {
		OTTimeStampInfo otTimeStampInfo = anOTEdit.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo = anOTEdit.getTransformableListEdit().getListEdit().toListEditInfo();
		OTListEditFlipped.newCase(
				CommunicatorSelector.getProcessName(),
				editInfo,
				otTimeStampInfo,
				aUser,
//				isServer,
				aFinder);
	}

}
