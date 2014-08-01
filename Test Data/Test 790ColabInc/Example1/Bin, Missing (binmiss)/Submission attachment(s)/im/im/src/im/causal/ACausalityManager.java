package im.causal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import util.session.MessageProcessor;
import util.session.ReceivedMessage;
import util.session.SentMessage;
import util.trace.Tracer;

public class ACausalityManager implements CausalityManager {
	MessageProcessor<ReceivedMessage> receivedMessageProcessor; // the succeeding queue to which incoming messages are sent after removing time stamp
	MessageProcessor<SentMessage> sentMessageProcessor; // the succeeding queue to which time stamped message is sent
	String myUserName ;
	VectorTimeStamp myVectorTimeStamp = new AVectorTimeStamp();	
	List<ReceivedMessage> pendingReceivedMessages = new ArrayList(); // a received message is pending until its preceding message arrives
	
	public ACausalityManager (String theMyUserName) {
		myUserName = theMyUserName;
	}
	int compareTo(ReceivedMessage message1, ReceivedMessage message2) {
		MessageWithVectorTimeStamp wrappedMessage1 = (MessageWithVectorTimeStamp) message1.getUserMessage();
		MessageWithVectorTimeStamp wrappedMessage2 = (MessageWithVectorTimeStamp) message2.getUserMessage();
		return wrappedMessage1.getVectorTimeStamp().compareTo(wrappedMessage2.getVectorTimeStamp());
		
	}
	boolean isSuccessor(ReceivedMessage message) {
		MessageWithVectorTimeStamp wrappedMessage = (MessageWithVectorTimeStamp) message.getUserMessage();
		
		return myVectorTimeStamp.isSuccessor(wrappedMessage.getVectorTimeStamp());
		
	}
	void insertIntSortedPendingMessages (ReceivedMessage receivedMessage) {
		int insertionIndex = 0;
		for (int i=0; i < pendingReceivedMessages.size(); i++) {
			if (compareTo(receivedMessage, pendingReceivedMessages.get(i)) <= 0)
				insertionIndex++;
			else
				break;
		}
		pendingReceivedMessages.add(insertionIndex, receivedMessage);			
		
	}
	// return false if next message is also not ready for processing
	boolean processNextPendingMessage() {
		if (pendingReceivedMessages.size() == 0)
			return false;
		ReceivedMessage nextPendingMessage = pendingReceivedMessages.get(0);
		if (isSuccessor(nextPendingMessage)) {
			processReadyReceivedMessage(nextPendingMessage);
			pendingReceivedMessages.remove(0);
			return true;
		}
		return false;
		
	}
	void  processPendingMessages() {
		// while next message is processsable, process (and remove) next message
		if (processNextPendingMessage()) //  side effect as in iterator
			processPendingMessages();		
	}
	
	void processReadyReceivedMessage(ReceivedMessage receivedMessage) {
		MessageWithVectorTimeStamp wrappedMessage = 
			(MessageWithVectorTimeStamp) receivedMessage.getUserMessage();
		Object actualMessage =  wrappedMessage.getMessage();
		receivedMessage.setUserMessage(actualMessage);
		myVectorTimeStamp.addMessage(receivedMessage.getClientName());
		receivedMessageProcessor.processMessage(receivedMessage);		
	}

	@Override
	public void put(ReceivedMessage message) {
		if (message.isUserMessage()) {
			MessageWithVectorTimeStamp wrappedMessage = 
				(MessageWithVectorTimeStamp) message.getUserMessage();			
			if (wrappedMessage.getVectorTimeStamp().isConcurrent(myVectorTimeStamp))
				processReadyReceivedMessage(message); // concurrent messages  delivered immediately, as they should be stopped by other means
			else {				
				insertIntSortedPendingMessages(message);	
				processPendingMessages();
			}
		} else {
			receivedMessageProcessor.processMessage(message);
		}				
	}

	@Override
	public void put(SentMessage message) {
		
		if (message.isUserMessage()) {
//			myVectorTimeStamp.addMessage(myUserName);
//			MessageWithVectorTimeStamp wrappedMessage = 
//				new AMessageWithVectorTimeStamp(message.getUserMessage(), (VectorTimeStamp) myVectorTimeStamp.clone());
			message.setUserMessage(timeStamp(message));
		}
		sentMessageProcessor.processMessage(message);		
	}
	@Override
	public  MessageWithVectorTimeStamp timeStamp(SentMessage message) {
		myVectorTimeStamp.addMessage(myUserName);
		MessageWithVectorTimeStamp wrappedMessage = 
			new AMessageWithVectorTimeStamp(message.getUserMessage(), (VectorTimeStamp) myVectorTimeStamp.clone());
		return wrappedMessage;
	}

	@Override
	public void setReceivedMessageProcessor(MessageProcessor<ReceivedMessage> theMessageProcessor) {
		receivedMessageProcessor = theMessageProcessor;
	}

	@Override
	public void setSentMessageProcessor(MessageProcessor<SentMessage> theMessageProcessor) {
		sentMessageProcessor = theMessageProcessor;
	}

	

	@Override
	public void clientJoined(String userName, String theApplicationName,
			String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
//		Tracer.info("Caisuality Manager: User Joined" + userName);		
		if (userName.equals(myUserName)) {
			myVectorTimeStamp.addUser(userName);
			for (String clientName:allUsers) {				
				myVectorTimeStamp.addUser(clientName);
//				Tracer.info("Vector TS:" + clientName + " :" + myVectorTimeStamp);
			}
		} else {
			myVectorTimeStamp.addUser(userName);
			//System.out.println("TS for" + userName);
//			Tracer.info("Vector TS:" + userName + " :" + myVectorTimeStamp);
		}
//		if (userName.equals(communicator.getUserName())) {
//			setClients(communicator.getClients());
//			
//		} 
//		myVectorTimeStamp.addUser(userName)	;
//		System.out.println(communicator.getUserName() + " joined by " + userName + " TS size:" + myVectorTimeStamp.size());
		
	}
	@Override
	public void clientLeft(String userName, String theApplicationName) {
		// TODO Auto-generated method stub
		
	}

}
