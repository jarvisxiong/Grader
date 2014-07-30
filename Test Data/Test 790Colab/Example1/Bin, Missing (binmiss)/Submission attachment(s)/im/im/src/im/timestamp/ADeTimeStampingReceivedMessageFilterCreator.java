package im.timestamp;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;

public class ADeTimeStampingReceivedMessageFilterCreator  implements MessageFilterCreator<ReceivedMessage>{
	MessageFilter<ReceivedMessage> receivedMessageQueuer;

	public ADeTimeStampingReceivedMessageFilterCreator() {
		receivedMessageQueuer =  new ADeTimeStampingReceivedMessageFilter();
	}
	@Override
	public MessageFilter<ReceivedMessage> getMessageFilter() {
		return receivedMessageQueuer;
	}

}
