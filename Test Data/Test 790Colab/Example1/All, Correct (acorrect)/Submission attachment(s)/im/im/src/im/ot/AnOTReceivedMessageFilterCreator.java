package im.ot;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;

public class AnOTReceivedMessageFilterCreator  implements MessageFilterCreator<ReceivedMessage>{
	MessageFilter<ReceivedMessage> receivedMessageQueuer;
	OTManager otManager;
	public AnOTReceivedMessageFilterCreator(OTManager theOTManager) {
		otManager = theOTManager;
		receivedMessageQueuer =  new AnOTReceivedMessageFilter(otManager);
	}
	@Override
	public MessageFilter<ReceivedMessage> getMessageFilter() {
		return receivedMessageQueuer;
	}

}
