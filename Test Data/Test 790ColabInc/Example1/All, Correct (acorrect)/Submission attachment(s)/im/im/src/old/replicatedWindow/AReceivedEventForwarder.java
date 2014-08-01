package old.replicatedWindow;

import java.util.Collection;

import util.awt.ADelegateFrame;
import util.awt.ASerializableEvent;
import util.session.ReceivedMessageListener;

public class AReceivedEventForwarder implements ReceivedMessageListener {
//	MulticastClient multicastClient;
//	public AReceivedEventForwarder(MulticastClient theClient) {
//		multicastClient = theClient;
//		multicastClient.addReceivedMessageListener(this);
//	}
	@Override
	public void objectReceived(Object message, String sourceName) {
		ASerializableEvent event = (ASerializableEvent) message;
		//ADelegateFrame delegateFrame = (ADelegateFrame) event.getSource();
		int frameId = event.getSource();
		ADelegateFrame delegateFrame =  ADelegateFrame.getFrame(frameId);
		delegateFrame.processEvent(event);	
	}

	@Override
	public void clientJoined(String userName, String theApplicationName, String theSessionName, boolean newSession, boolean newApplication, Collection<String> allUsers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientLeft(String userName, String theApplicationName) {
		// TODO Auto-generated method stub
		
	}

}
