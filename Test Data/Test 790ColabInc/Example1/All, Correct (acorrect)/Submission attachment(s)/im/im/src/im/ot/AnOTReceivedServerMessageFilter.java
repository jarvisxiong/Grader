package im.ot;

import im.ListEdit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import util.misc.Common;
import util.session.ASentMessage;
import util.session.MessageProcessor;
import util.session.SentMessage;
import util.session.SentMessageType;
import util.session.ServerMessageFilter;
import util.trace.Tracer;
// A multicaster sender that puts the message in multiple queues, one for each client
// Each queue is a separate OT Manager

public class AnOTReceivedServerMessageFilter implements ServerMessageFilter {
	Map<String, OTManager> clientToOTManager = new HashMap();
	MessageProcessor<SentMessage> sentMessageQueue;

	@Override
	public synchronized void userJoined(String aSessionName, String anApplicationName, String userName) {
		OTManager otManager = new AnOTManager(aSessionName, userName, true);
		clientToOTManager.put(userName, otManager);
		// otManager.setSentMessageQueue(sentMessageQueue);

	}

	@Override
	public void userLeft(String aSessionName, String anApplicationName, String userName) {
		// TODO Auto-generated method stub

	}

	/*
	 * message sent by user u is a message received by all users, U, other than
	 * u. so it must be treated as a received message by server representing U
	 * -u transform this message with respect to all messages sent to u by the
	 * server representing itself as U - u. these messages are stored in u's ot
	 * Manager. send transformed message to U - u. store this message, with
	 * appropriate time stamp, as sent message in otManagers of U - u each ot
	 * Manager will time stamp differently this means message must be unicast
	 */

	@Override
	public synchronized void filterMessage(SentMessage message) {
		if (!message.isUserMessage()
				|| message.getSentMessageType() != SentMessageType.Others) {
			sentMessageQueue.processMessage(message);
			return;
		}
		Tracer.info(this, "START PUT in OTSERVER QUEUE");
		ListEditWithOTTimeStamp receivedEditWithOTTimeStamp = (ListEditWithOTTimeStamp) message
				.getUserMessage();
		OTManager sentOTManager = clientToOTManager.get(message
				.getSendingUser());
		Tracer.info(this, "OT Manager:" + message.getSendingUser());
		receivedEditWithOTTimeStamp.getOTTimeStamp().flip(); // our OT function compares local with local now
		AnOTReceivedMessageFilter.traceOTEditFlip(receivedEditWithOTTimeStamp, message.getSendingUser(), false , this);
		// transform message with respect to all messages sent to u by the
		// server representing itself as U - u
		sentOTManager.processTimeStampedEdit(receivedEditWithOTTimeStamp,
				message.getSendingUser(), false);
		// this will be the edit sent to U - u
		ListEdit sentEdit = receivedEditWithOTTimeStamp
				.getTransformableListEdit().getListEdit();
		// iterate thru U - u and ot Managers of U - u.
		Set<String> allUsers = clientToOTManager.keySet();
		Tracer.info(this, "All Users n OTSERVER QUEUE:" + allUsers);
		for (String user : allUsers) {
			if (user.equals(message.getSendingUser()))
				continue;
			OTManager receivingOTManager = clientToOTManager.get(user);
			// transform the headers in the message which will include
			// transformed edit
			SentMessage unicastMessage = ASentMessage.toSpecificUser(message,
					user); // move to communicator? 
			// this is the server's sent edit to each U - u
			// Edit clonedEdit = (Edit) Misc.deepCopy(sentEdit);
			// sent edit does not have to be cloned in the sentMessage output
			// buffer
			Tracer.info(this, "OT Manager:" + user);
			ListEditWithOTTimeStamp sentListEditWithOTTimeStamp = receivingOTManager
					.processSentEdit(sentEdit); // the raw edit has to be time stamped by  receiving ot manager, it should increment local count
			// create a different copy for sent buffer with original time stamp, why different it should have this time stamp, it is as if the server is send
//			SentMessage clonedUnicastMessage = (SentMessage) Common
//					.deepCopy(unicastMessage);
			OTTimeStamp otTimeStamp = sentListEditWithOTTimeStamp
					.getOTTimeStamp();
			// sentListEditWithOTTimeStamp.getOTTimeStamp().flip();			
			// receiver is flipping, so sender does not have to

			unicastMessage.setUserMessage(sentListEditWithOTTimeStamp); //
			Tracer.info(this, "Unicasting message to: " + user);
			sentMessageQueue.processMessage(unicastMessage);
			// store a different copy in the buffer
			// SentMessage clonedUnicastMessage = (SentMessage)
			// Common.deepCopy(unicastMessage);
			// Message.info("OT Manager:" + user);
			
			// create a different copy for sent buffer with time stamp created by receiving OT manager
			SentMessage clonedUnicastMessage = (SentMessage) Common
					.deepCopy(unicastMessage);
			receivingOTManager.storeSentMessage(clonedUnicastMessage); // why not put this in ot manager or make all of this part of ot manager?
			// otManager.put(receivedMessage);
		}
		Tracer.info(this, "END PUT in OTSERVER QUEUE");
	}

	@Override
	public void setMessageProcessor(
			MessageProcessor<SentMessage> theMesssageProcessor) {
		sentMessageQueue = theMesssageProcessor;

	}

}
