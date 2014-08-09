package im.ot;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;

public class AnOTSentMessageFilterCreator  implements MessageFilterCreator<SentMessage>{
	MessageFilter<SentMessage> sentMessageQueuer;
	OTManager otManager;
	public AnOTSentMessageFilterCreator(OTManager theOTManager) {
		otManager = theOTManager;
		sentMessageQueuer =  new AnOTSentMessageFilter(otManager);
	}
	@Override
	public MessageFilter<SentMessage> getMessageFilter() {
		return sentMessageQueuer;
	}

}
