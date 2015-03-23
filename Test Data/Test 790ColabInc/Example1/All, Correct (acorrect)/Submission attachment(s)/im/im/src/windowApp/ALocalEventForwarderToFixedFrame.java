package windowApp;

import util.awt.ADelegateFrame;
import util.awt.EventQueueHandler;
import util.awt.SerializableEvent;

public class ALocalEventForwarderToFixedFrame implements EventQueueHandler{
	ADelegateFrame frame;
	public ALocalEventForwarderToFixedFrame(ADelegateFrame theFrame) {
		frame = theFrame;
	}
	public void newEvent(SerializableEvent event) {
		//ADelegateFrame frame =  (ADelegateFrame)event.getSource();
		frame.processEvent(event);		
	}
}
