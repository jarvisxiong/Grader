package im.causal;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;

public class ACausalReceivedMessageFilterCreator  implements MessageFilterCreator<ReceivedMessage>{
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	CausalityManager causalityManager;
	public ACausalReceivedMessageFilterCreator(CausalityManager theCausalityManager) {
		causalityManager = theCausalityManager;
		receivedMessageQueuer =  new ACausalReceivedMessageFilter(causalityManager);
	}
	@Override
	public MessageFilter<ReceivedMessage> getMessageFilter() {
		return receivedMessageQueuer;
	}

}
