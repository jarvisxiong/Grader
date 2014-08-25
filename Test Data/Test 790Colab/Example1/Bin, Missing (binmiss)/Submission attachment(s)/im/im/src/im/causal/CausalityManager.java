package im.causal;

import util.session.MessageProcessor;
import util.session.ReceivedMessage;
import util.session.SentMessage;
import util.session.SessionMessageListener;

public interface CausalityManager extends SessionMessageListener{
	//public void setCommunicator(Communicator theCommunicator);
	public void put(ReceivedMessage message);
	public void put(SentMessage message);
	public void setReceivedMessageProcessor(MessageProcessor<ReceivedMessage> theMessageProcessor) ;
	public void setSentMessageProcessor(MessageProcessor<SentMessage> theMessageProcessor) ;
	MessageWithVectorTimeStamp timeStamp(SentMessage message);
}
