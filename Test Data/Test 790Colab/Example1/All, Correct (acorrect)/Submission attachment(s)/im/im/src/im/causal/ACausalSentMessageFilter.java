package im.causal;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;
//converts  simplex sending  multicaster abstraction to duplex causal manager

public class ACausalSentMessageFilter implements MessageFilter<SentMessage> {
	CausalityManager causalityManager;
	MessageProcessor<SentMessage> sentMessageProcessor;
	public ACausalSentMessageFilter(CausalityManager theCausalityManager) {
		causalityManager = theCausalityManager;
	}

	@Override
	public void filterMessage(SentMessage message) {
//		causalityManager.put(message);	
		if (message.isUserMessage()) {
//			myVectorTimeStamp.addMessage(myUserName);
//			MessageWithVectorTimeStamp wrappedMessage = 
//				new AMessageWithVectorTimeStamp(message.getUserMessage(), (VectorTimeStamp) myVectorTimeStamp.clone());
			message.setUserMessage(causalityManager.timeStamp(message));
		}
		sentMessageProcessor.processMessage(message);	
	}

	@Override
	public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
		sentMessageProcessor = theMesssageProcessor;
//		causalityManager.setSentMessageProcessor(theMesssageProcessor);		
	}

}
