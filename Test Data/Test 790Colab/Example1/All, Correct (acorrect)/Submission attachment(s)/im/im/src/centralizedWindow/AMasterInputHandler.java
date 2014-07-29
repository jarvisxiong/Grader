package centralizedWindow;
import util.awt.ADelegateFrame;
import util.awt.ASerializableFrameRequest;
import util.awt.AnOutputQueue;
import util.awt.EventQueueHandler;
import util.awt.SerializableEvent;
import util.awt.SerializableFrameRequest;
public class AMasterInputHandler implements EventQueueHandler {	
	
	public void newEvent(SerializableEvent event) {
		if ( event.isResizeEvent() ) {	
			int frameId = event.getSource();
			ADelegateFrame frame = ADelegateFrame.getFrame(frameId);
			Object args[] = {frame.getWidth(), frame.getHeight()};
			SerializableFrameRequest request =  new ASerializableFrameRequest(frameId, SerializableFrameRequest.SET_SIZE, args);			
			AnOutputQueue.notifyListeners(request);
		}		
	}
}
