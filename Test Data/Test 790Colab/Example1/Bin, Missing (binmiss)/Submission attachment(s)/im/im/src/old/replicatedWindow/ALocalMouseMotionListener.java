package old.replicatedWindow;

import util.awt.ADelegateFrame;
import util.awt.EventQueueHandler;
import util.awt.SerializableEvent;

public class ALocalMouseMotionListener implements EventQueueHandler{
	String telePointerString;
	ATelePointerManager telePointerManager;
	public ALocalMouseMotionListener(String theTelePointerString, 
			ATelePointerManager theTelePointerManager) {
		telePointerString = theTelePointerString;
		telePointerManager = theTelePointerManager;		
	}
	@Override
	public void newEvent(SerializableEvent e) {
		if (e.isMouseMovedEvent()) {
			int frameId = e.getSource();
			ADelegateFrame frame =  ADelegateFrame.getFrame(frameId);
			telePointerManager.put(telePointerString, 
					new ATelePointer(frame, e.getX(), e.getY()));	
		}
		
	}

}
