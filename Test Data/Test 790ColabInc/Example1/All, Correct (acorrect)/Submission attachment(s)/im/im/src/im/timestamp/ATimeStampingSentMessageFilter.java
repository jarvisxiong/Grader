package im.timestamp;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;
//converts  simplex sending  multicaster abstraction to duplex causal manager
// is this really a queuer? It is a filter! Rename util at some point
public class ATimeStampingSentMessageFilter implements MessageFilter<SentMessage> {
	MessageProcessor<SentMessage> sentMessageProcessor;
	@Override
	// downstream node in the send pipline
	public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
		sentMessageProcessor = theMesssageProcessor;
	}
	@Override
	// called by communicator when a new message is submitted by client
	// time stamp message and send it to the next stage outwards
	public void filterMessage(SentMessage aSentMessage) {
		Object userMessage = aSentMessage.getUserMessage();
		aSentMessage.setUserMessage(
				new AMessageWithTimeStamp(
						userMessage, System.currentTimeMillis()));
		sentMessageProcessor.processMessage(aSentMessage);			
	}


}
