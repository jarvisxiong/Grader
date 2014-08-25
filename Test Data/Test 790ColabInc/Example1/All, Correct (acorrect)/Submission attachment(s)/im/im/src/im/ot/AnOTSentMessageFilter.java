package im.ot;

import im.ListEdit;
import trace.echo.modular.ListEditInfo;
import trace.ot.OTTimeStampInfo;
import trace.ot.OTTimeStampedListEditInfo;
import trace.ot.OTListEditReceived;
import trace.ot.OTListEditSent;
import util.misc.Common;
import util.session.CommunicatorSelector;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;
import util.session.SentMessageType;
import util.trace.session.MessageCopied;

public class AnOTSentMessageFilter implements MessageFilter<SentMessage> {
	OTManager otManager;
	MessageProcessor<SentMessage> sentMessageProcessor;
	public AnOTSentMessageFilter(OTManager theCausalityManager) {
		otManager = theCausalityManager;
	}

	@Override
	public synchronized void filterMessage(SentMessage message) {
		if (!message.isUserMessage() || message.getSentMessageType() != SentMessageType.Others) {
			sentMessageProcessor.processMessage(message);
			return;
		}
		ListEdit edit = (ListEdit) message.getUserMessage();
		ListEditWithOTTimeStamp timeStampedEdit = timeStamp(edit);
		message.setUserMessage(timeStampedEdit);
		sentMessageProcessor.processMessage(message);
		AnOTManager.traceOTEditSent(timeStampedEdit, otManager);
//
//		SentMessage messageClone = (SentMessage) Common.deepCopy(message); // time stamp copied earlier, now message embedding it
//		MessageCopied.newCase(
//				CommunicatorSelector.getProcessName(), 
//				messageClone.getUserMessage(), 
//				otManager.getUserName(), 
//				this);
//		otManager.storeSentMessage(messageClone); // cloning was done for storing message
		storeClone(message);
	}
	
	ListEditWithOTTimeStamp timeStamp(ListEdit edit) {
		return otManager.processSentEdit(edit);//create time stamped edit
	}
	void storeClone(Object message) {
		SentMessage messageClone = (SentMessage) Common.deepCopy(message); // time stamp copied earlier, now message embedding it
		MessageCopied.newCase(
				CommunicatorSelector.getProcessName(), 
				messageClone.getUserMessage(), 
				otManager.getUserName(), 
				this);
		otManager.storeSentMessage(messageClone); // cloning was done for storing message
	
	}
	
	

	@Override
	public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
		sentMessageProcessor = theMesssageProcessor;		
	}

}
