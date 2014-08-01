package im.causal;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;

public class ACausalSentMessageFilterCreator  implements MessageFilterCreator<SentMessage>{
	MessageFilter<SentMessage> sentMessageQueuer;
	CausalityManager causalityManager;
	public ACausalSentMessageFilterCreator(CausalityManager theCausalityManager) {
		causalityManager = theCausalityManager;
		sentMessageQueuer =  new ACausalSentMessageFilter(causalityManager);
	}
	@Override
	public MessageFilter<SentMessage> getMessageFilter() {
		return sentMessageQueuer;
	}

}
