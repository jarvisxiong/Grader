package im.timestamp;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;
// converts  simplex receiver  multicaster abstraction to duplex causal manager
public class ADeTimeStampingReceivedMessageFilter implements MessageFilter<ReceivedMessage> {
	MessageProcessor<ReceivedMessage> receivedMessageProcessor;
	@Override
	// called by communicator when a new message is submitted by client
	// unwrap time stamped message and submit it to the next input stage inwards
	public void filterMessage(ReceivedMessage aReceivedMessage) {
		if (aReceivedMessage.isUserMessage()) {
			MessageWithTimeStamp aTimeStampedMessage = (MessageWithTimeStamp) aReceivedMessage
					.getUserMessage();
			displayApproximateMessageDelay(aTimeStampedMessage);
			aReceivedMessage.setUserMessage(aTimeStampedMessage.getMessage());
		}
		receivedMessageProcessor.processMessage(aReceivedMessage);		
	}
	
	void displayApproximateMessageDelay(MessageWithTimeStamp aTimeStampedMessage) {
		Object message = aTimeStampedMessage.getMessage();
		long timeStamp = aTimeStampedMessage.geTimeStamp();
		System.out.println("Received message:" + message
				+ " with approximate delay:"
				+ (System.currentTimeMillis() - timeStamp));
		
	}

	@Override
	// downstream node in the receive pipeline
	public void setMessageProcessor(MessageProcessor<ReceivedMessage> theMesssageProcessor) {
		receivedMessageProcessor = theMesssageProcessor;
	}
}
