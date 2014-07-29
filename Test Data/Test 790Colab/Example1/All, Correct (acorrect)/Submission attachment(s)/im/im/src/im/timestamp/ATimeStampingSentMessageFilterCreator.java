package im.timestamp;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;

public class ATimeStampingSentMessageFilterCreator  implements MessageFilterCreator<SentMessage>{
	MessageFilter<SentMessage> sentMessageQueuer;
	public ATimeStampingSentMessageFilterCreator() {
		sentMessageQueuer =  new ATimeStampingSentMessageFilter();
	}
	@Override
	public MessageFilter<SentMessage> getMessageFilter() {
		return sentMessageQueuer;
	}

}
