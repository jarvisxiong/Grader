package windowApp;

import util.awt.ADelegateFrame;
import util.awt.EventQueueHandler;
import util.awt.SerializableEvent;

public class ALocalEventMirrorer implements EventQueueHandler{
	ADelegateFrame frame1, frame2;
	public ALocalEventMirrorer(ADelegateFrame theFrame1, ADelegateFrame theFrame2) {
		frame1 = theFrame1;
		frame2 = theFrame2;
	}
	public void newEvent(SerializableEvent event) {
		int frameId = event.getSource();
		ADelegateFrame frame =  ADelegateFrame.getFrame(frameId);
		//ADelegateFrame frame =  (ADelegateFrame)event.getSource();
		if (frame == frame1) {
			frame2.processEvent(event);	
			frame2.repaint();
		} else {
			frame1.processEvent(event);
			frame1.repaint();
		}
	}
}
