package im.causal;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;
// converts  simplex receiver  multicaster abstraction to duplex causal manager
public class ACausalReceivedMessageFilter implements MessageFilter<ReceivedMessage> {
	CausalityManager causalityManager;
	public ACausalReceivedMessageFilter(CausalityManager theCausalityManager) {
		causalityManager = theCausalityManager;
	}

	@Override
	public void filterMessage(ReceivedMessage message) {
		causalityManager.put(message);
	}

	@Override
	public void setMessageProcessor(MessageProcessor<ReceivedMessage> theMesssageProcessor) {
		causalityManager.setReceivedMessageProcessor(theMesssageProcessor);		
	}

}
