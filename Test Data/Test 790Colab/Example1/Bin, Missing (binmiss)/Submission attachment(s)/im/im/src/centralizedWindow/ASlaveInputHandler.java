package centralizedWindow;
import util.awt.EventQueueHandler;
import util.awt.SerializableEvent;
import util.session.Communicator;
public class ASlaveInputHandler implements EventQueueHandler {
	Communicator multicastClient;
	String masterName;
	public ASlaveInputHandler(String theMasterName, Communicator theMulticaster) {		
		multicastClient = theMulticaster;
		masterName = theMasterName;
	}
	public void newEvent(SerializableEvent event) {
		if (event.isKeyEvent()  || event.isMousePressedEvent() || event.isResizeEvent() ) {			
			multicastClient.toClient(masterName, event);
			return;
		}		
	}
}
