package old.replicatedWindow;
import util.awt.EventQueueHandler;
import util.awt.SerializableEvent;
import util.session.Communicator;
public class ASerializableEventBroadcaster implements EventQueueHandler {
	Communicator multicastClient;
	public ASerializableEventBroadcaster(Communicator theMulticastClient) {		
		multicastClient = theMulticastClient;		
	}
	public void newEvent(SerializableEvent event) {
		if (event.isKeyEvent()  || event.isMouseEvent() || event.isResizeEvent() ) {			
			multicastClient.toOthers(event);
			return;
		}		
	}
}
