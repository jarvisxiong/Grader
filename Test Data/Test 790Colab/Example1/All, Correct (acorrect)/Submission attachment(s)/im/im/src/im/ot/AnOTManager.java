package im.ot;

import im.ListEdit;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import trace.echo.modular.ListEditInfo;
import trace.ot.ConcurrentEdits;
import trace.ot.InitialOTTimeStampCreated;
import trace.ot.LocalSiteCountIncremented;
import trace.ot.OTTimeStampInfo;
import trace.ot.OTListEditRemoteCountIncremented;
import trace.ot.OTListEditCopied;
import trace.ot.OTListEditSent;
import trace.ot.RemoteSiteCountIncremented;
import trace.ot.TransformationOperands;
import trace.ot.TransformationResult;
import util.models.ABoundedBuffer;
import util.session.CommunicatorSelector;
import util.session.ASessionManagerSelector;
import util.session.ReceivedMessage;
import util.session.SentMessage;
import util.session.SessionManager;
import util.trace.Tracer;
import util.trace.session.MessageBufferReferenceCountDecremented;
import util.trace.session.MessageBuffered;
import util.trace.session.MessageUnBuffered;

public class AnOTManager implements OTManager {
	String userName;
	
//	BoundedBuffer<ReceivedMessage> inQueue;
	ABoundedBuffer<SentMessage> outQueue;
	boolean isServer ;
	OTTimeStamp myOTTimeStamp; 	
	//Communicator communicator;
//	List<ReferencedObject<SentMessage>> sentMessages = new ArrayList();
	List<SentMessage> sentMessages = new ArrayList();

	OperationTransformer operationTransformer = AnOperationTransfomerSelector.getOperationTransformer();
	String location;
	String sessionName;
	public AnOTManager (String theSessionName, String theUserName, boolean theIsServer) {
		sessionName = theSessionName; 
		isServer = theIsServer;
		userName = theUserName;
		myOTTimeStamp = new AnOTTimeStamp();
		location = computedLocation();
		InitialOTTimeStampCreated.newCase(
				location,
				userName, 
				myOTTimeStamp.getLocalCount(), 
				myOTTimeStamp.getRemoteCount(), 
				theIsServer, 
				this);
	}	
	
	String computedLocation() {
		return isServer?SessionManager.SESSION_MANAGER_NAME:userName; // communicator not created when OT manager is instantiated
	}
	
	public String getLocation() {
		return location;
	}

//	@Override
//	public synchronized void put(ReceivedMessage message) {
//		if (message.isUserMessage()) {
//			//Message.info ("OT Manager receiving message:" + message.getUserMessage());
//			EditWithOTTimeStamp receivedTSEdit = 
//				(EditWithOTTimeStamp) message.getUserMessage();			
//			processReceivedMessage(receivedTSEdit);
//			message.setUserMessage(receivedTSEdit.getTransformableEdit().getEdit());
//			//myOTTimeStamp.incrementRemoteCount();
//			//Message.info("After received:" +myOTTimeStamp + " isServer:" + isServer);
//
//		}
//		if (inQueue != null)
//			inQueue.put(message);			
//				
//	}
	
	// trasnform received edit, changing its edit and time stamp, and also change site time stamp
	// clear buffered messages from other sites (server) whose time stamp is strictly less than
	// the one sent by this one 
	
	public void processTimeStampedEdit(ListEditWithOTTimeStamp receivedTSEdit, String fromUser, boolean isServer) { 
		Tracer.info(this, "Transforming:" + receivedTSEdit);
		
		int firstConcurrentMessageIndex = sentMessages.size();
		for (int i = 0; i < sentMessages.size(); i++) {
//			SentMessage aSentMessage = sentMessages.get(i).getObject();
			SentMessage aSentMessage = sentMessages.get(i);

//			ListEditWithOTTimeStamp sentTSEdit = (ListEditWithOTTimeStamp )sentMessages.get(i).getUserMessage();
			ListEditWithOTTimeStamp sentTSEdit = (ListEditWithOTTimeStamp )aSentMessage.getUserMessage();

			if (sentTSEdit.getOTTimeStamp().isConcurrent(receivedTSEdit.getOTTimeStamp())) {
//				Tracer.info(this, "Found concurrent sent message: " + sentTSEdit);
				traceConcurentEdits(receivedTSEdit, fromUser, !this.isServer, sentTSEdit, aSentMessage.getSendingUser(), this.isServer, this);
				firstConcurrentMessageIndex = i;
				break;
			}
		}
	
		for (int deleteNum = 0; deleteNum < firstConcurrentMessageIndex; deleteNum++ ) {
//			SentMessage sentMessage = sentMessages.get(0);
//			Message
//			sentMessages.remove(0);	
			maybeRemoveFirstSentMessage(); // the called method will trace
		}
		
		TransformableListEdit receivedTransformableEdit = receivedTSEdit.getTransformableListEdit(); // just accessing a field
		Tracer.info(this, "Transforming repeatedly received:" + receivedTSEdit);
		//myOTTimeStamp.incrementRemoteCount();
//		for (ReferencedObject<SentMessage> sentMessage:sentMessages) {
		for (SentMessage sentMessage:sentMessages) {

//			ListEditWithOTTimeStamp sentTSEdit = (ListEditWithOTTimeStamp)sentMessage.getObject().getUserMessage();
			ListEditWithOTTimeStamp sentTSEdit = (ListEditWithOTTimeStamp)sentMessage.getUserMessage();

			Tracer.info(this, "transforming wrt buffered:" + sentTSEdit);
			TransformableListEdit sentTransformableEdit = sentTSEdit.getTransformableListEdit();
			ListEdit transformedReceived = operationTransformer.transform(
						receivedTransformableEdit, sentTransformableEdit);
			traceTransformationOperands(receivedTSEdit, fromUser, !isServer, sentTSEdit, sentMessage.getSendingUser(), isServer, this);

//			traceTransformationOperands(receivedTSEdit, fromUser, !isServer, sentTSEdit, sentMessage.getObject().getSendingUser(), isServer, this);
			ListEdit transformedSent = operationTransformer.transform(sentTransformableEdit, receivedTransformableEdit);			
//			traceTransformationOperands(sentTSEdit, sentMessage.getObject().getSendingUser(), isServer, receivedTSEdit, fromUser, !isServer, this);
			traceTransformationOperands(sentTSEdit, sentMessage.getSendingUser(), isServer, receivedTSEdit, fromUser, !isServer, this);

			// change the original edits after both have been transformed
			sentTransformableEdit.setListEdit(transformedSent);			
//			traceTransformationResult(sentTSEdit,sentMessage.getObject().getSendingUser(), this, isServer);			
			traceTransformationResult(sentTSEdit,sentMessage.getSendingUser(), this, isServer);			

			receivedTransformableEdit.setListEdit(transformedReceived);
			traceTransformationResult(receivedTSEdit,fromUser, this, !isServer);
			sentTSEdit.getOTTimeStamp().incrementRemoteCount();			
			traceRemoteCountIncrement(sentTSEdit, userName, this);
			receivedTSEdit.getOTTimeStamp().incrementRemoteCount();	
			traceRemoteCountIncrement(receivedTSEdit, fromUser, this);
			Tracer.info(this, "buffered transformed to:" + sentTSEdit);
			Tracer.info(this, "Received transformed to:" + receivedTSEdit);
		}
		myOTTimeStamp.incrementRemoteCount();		
		RemoteSiteCountIncremented.newCase(
				CommunicatorSelector.getProcessName(),
				userName, 
				myOTTimeStamp.getLocalCount(), 
				myOTTimeStamp.getRemoteCount(), 
//				this.isServer, 
				this);		
		Tracer.info(this, "Transformed:" + receivedTSEdit);
		Tracer.info(this, "My Time Stamp:" + myOTTimeStamp);
	}
	
	
	public void storeSentMessage(SentMessage message) {
		// if in server, then the sent client message becomes a server message
//		((ListEditWithOTTimeStamp)message.getUserMessage()).getTransformableListEdit().setServer(isServer);
//		int referenceCount = 0;
//		try {
//		int	referenceCount = isServer? 1 // a server has buffer for each user, count is 1
////					ASessionManagerSelector.getSessionManager().
////						getOrCreateSession(sessionName).
////							getClientNames().size()
//					:ACommunicatorSelector.getCommunicator().
//						getUserNames().length; // this is all user names
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		if (referenceCount <= 0) {
//		ReferencedObject referencedObject = new AReferencedObject(message, referenceCount );
//		sentMessages.add(referencedObject);
		
		sentMessages.add(message);
		MessageBuffered.newCase(
				CommunicatorSelector.getProcessName(), 
//				message.getUserMessage(), 
				message,
				userName, 
				sentMessages,
//				referenceCount,
				this);
//		}
	}
	void maybeRemoveFirstSentMessage() {
//		ReferencedObject<SentMessage> sentMessage = sentMessages.get(0);
//		sentMessage.decrementReferenceCount();
//		MessageBufferReferenceCountDecremented.newCase(
//				computedLocation(), 
////				sentMessage.getObject().getUserMessage(), 
//				sentMessage, 
//				userName, 
//				sentMessages,
////				sentMessage.getReferenceCount(),
//				this);
//		if (sentMessage.getReferenceCount() <= 0) {
//		sentMessages.remove(0);	
//		MessageUnBuffered.newCase(
//				computedLocation(), 
////				sentMessage.getObject().getUserMessage(), 
//				sentMessage,
//				userName, 
//				sentMessages,
////				sentMessage.getReferenceCount(),
//				this);
//		}
		SentMessage sentMessage = sentMessages.get(0);
		sentMessages.remove(0);	
		MessageUnBuffered.newCase(
				computedLocation(), 
//				sentMessage.getObject().getUserMessage(), 
				sentMessage,
				userName, 
				sentMessages,
//				sentMessage.getReferenceCount(),
				this);
		
	}
	public ListEditWithOTTimeStamp processSentEdit(ListEdit edit ) { // time stamps and change site time stamp
		Tracer.info (this, "Raw Edit:" + edit);		
		myOTTimeStamp.incrementLocalCount();
		LocalSiteCountIncremented.newCase(
				CommunicatorSelector.getProcessName(),
				userName, 
				myOTTimeStamp.getLocalCount(), 
				myOTTimeStamp.getRemoteCount(), 
//				isServer, 
				this);

		TransformableListEdit transformableEdit = new ATransformableListEdit(edit, isServer);
		ListEditWithOTTimeStamp timeStampedEdit = new AListEditWithOTTimeStamp(transformableEdit, myOTTimeStamp.copy());			
		traceOTEditCopy(timeStampedEdit, this);

		return timeStampedEdit;		
	}
	@Override
	public String getUserName() {
		return userName;
	}
	
	public static void traceRemoteCountIncrement(ListEditWithOTTimeStamp anOTEdit, String aFromUser, OTManager anOTManager) {
		OTTimeStampInfo otTimeStampInfo = anOTEdit.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo = anOTEdit.getTransformableListEdit().getListEdit().toListEditInfo();
		OTListEditRemoteCountIncremented.newCase(
				CommunicatorSelector.getProcessName(),
				editInfo,
				otTimeStampInfo,
				aFromUser,
//				anOTManager.isServer(), // will always be true
				anOTManager);
	}
	
	public static void traceTransformationResult(ListEditWithOTTimeStamp anOTEdit, String aFromUser, OTManager anOTManager, boolean anIsServer) {
		OTTimeStampInfo otTimeStampInfo = anOTEdit.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo = anOTEdit.getTransformableListEdit().getListEdit().toListEditInfo();
		TransformationResult.newCase(
				anOTManager.getLocation(),
				editInfo,
				otTimeStampInfo,
				aFromUser,
				anIsServer,
				anOTManager);
	}


	
	public static void traceConcurentEdits(
			ListEditWithOTTimeStamp anOTEdit1,
			String aUser1,
			boolean anIsServer1,
			ListEditWithOTTimeStamp anOTEdit2,
			String aUser2,
			boolean anIsServer2,			
			OTManager anOTManager) {
		OTTimeStampInfo otTimeStampInfo1 = anOTEdit1.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo1 = anOTEdit1.getTransformableListEdit().getListEdit().toListEditInfo();
		OTTimeStampInfo otTimeStampInfo2 = anOTEdit2.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo2 = anOTEdit2.getTransformableListEdit().getListEdit().toListEditInfo();
		ConcurrentEdits.newCase(
				CommunicatorSelector.getProcessName(), 
				editInfo1, 
				otTimeStampInfo1, 
				aUser1, 
//				anIsServer1, 
				editInfo2, 
				otTimeStampInfo2, 
				aUser2, 
//				anIsServer2, 
				anOTManager);
	}
	public static void traceTransformationOperands(
			ListEditWithOTTimeStamp anOTEdit1,
			String aUser1,
			boolean anIsServer1,
			ListEditWithOTTimeStamp anOTEdit2,
			String aUser2,
			boolean anIsServer2,			
			OTManager anOTManager) {
		OTTimeStampInfo otTimeStampInfo1 = anOTEdit1.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo1 = anOTEdit1.getTransformableListEdit().getListEdit().toListEditInfo();
		OTTimeStampInfo otTimeStampInfo2 = anOTEdit2.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo2 = anOTEdit2.getTransformableListEdit().getListEdit().toListEditInfo();
		TransformationOperands.newCase(
				CommunicatorSelector.getProcessName(), 
				editInfo1, 
				otTimeStampInfo1, 
				aUser1, 
//				anIsServer1, 
				editInfo2, 
				otTimeStampInfo2, 
				aUser2, 
//				anIsServer2, 
				anOTManager);
	}
	
	
	public static void traceOTEditCopy(ListEditWithOTTimeStamp anOTEdit,  OTManager anOTManager) {
		OTTimeStampInfo otTimeStampInfo = anOTEdit.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo = anOTEdit.getTransformableListEdit().getListEdit().toListEditInfo();
		OTListEditCopied.newCase(
				CommunicatorSelector.getProcessName(),
				editInfo,
				otTimeStampInfo,
				anOTManager.getUserName(),
//				anOTManager.isServer(), // will always be true
				anOTManager);
	}
	
	public static void traceOTEditSent(ListEditWithOTTimeStamp anOTEdit,  OTManager anOTManager) {
		OTTimeStampInfo otTimeStampInfo = anOTEdit.getOTTimeStamp().toOTTimeStampInfo();		
		ListEditInfo editInfo = anOTEdit.getTransformableListEdit().getListEdit().toListEditInfo();
		
		OTListEditSent.newCase(
				CommunicatorSelector.getProcessName(),
				editInfo,
				otTimeStampInfo,
				anOTManager.getUserName(),
//				anOTManager.isServer(), // will always be true
				anOTManager);
	}

//	@Override
//	public void setReceivedMessageQueue(BoundedBuffer<ReceivedMessage> theBuffer) {
//		inQueue = theBuffer;
//	}
//
//	@Override
//	public void setSentMessageQueue(BoundedBuffer<SentMessage> theBuffer) {
//		outQueue = theBuffer;
//	}

	
//	public void setClients(Map<MessageReceiver, String> theClients)	{
//		Collection<String> values = theClients.values();
//		for (String clientName:values) {
//			System.out.println("TS for" + clientName);
//			myOTTimeStamp.addUser(clientName);
//		}
//		
//	}
	@Override
	public boolean isServer() {
		return isServer;
	}
	

}
