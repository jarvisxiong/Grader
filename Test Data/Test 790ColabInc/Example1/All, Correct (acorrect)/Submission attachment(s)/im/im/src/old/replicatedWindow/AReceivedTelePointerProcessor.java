package old.replicatedWindow;

import java.util.Collection;

import util.awt.ADelegateFrame;
import util.awt.ASerializableEvent;
import util.session.ReceivedMessageListener;

public class AReceivedTelePointerProcessor implements ReceivedMessageListener {

	ATelePointerManager telePointerManager;
	public AReceivedTelePointerProcessor(ATelePointerManager theTelePointerManager) {
		telePointerManager = theTelePointerManager;
		
	}
	@Override
	public void objectReceived(Object message, String sourceName) {
		if (!(message instanceof ASerializableEvent ))
			return;
		ASerializableEvent event = (ASerializableEvent) message;
		if (!event.isMouseMovedEvent()) return;
		int frameId = event.getSource();
		ADelegateFrame frame =  ADelegateFrame.getFrame(frameId);
		telePointerManager.put(sourceName, new ATelePointer(frame,
				event.getX(), event.getY()));
		
			
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
