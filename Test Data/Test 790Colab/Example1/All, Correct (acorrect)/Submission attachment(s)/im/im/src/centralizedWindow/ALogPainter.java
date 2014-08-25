package centralizedWindow;

import java.util.ArrayList;
import java.util.List;

import util.awt.ADelegateFrame;
import util.awt.ListenableGraphics;
import util.awt.SerializableGraphicsRequest;

public class ALogPainter implements LogPainter {
	List<SerializableGraphicsRequest> requests = new ArrayList();
	@Override
	public synchronized void paint(ADelegateFrame theFrame, ListenableGraphics g) {		
		for (SerializableGraphicsRequest request:requests) {
			paint(theFrame, g, request);
		}
		
		
	}
	public synchronized void add (SerializableGraphicsRequest theRequest) {
		//System.out.println("request added:" + theRequest);
		requests.add(theRequest);
	}
	public synchronized void clear() {
		//System.out.println("Log Cleared");
		requests.clear();
	}
	
    void  paint(ADelegateFrame theFrame, ListenableGraphics g, SerializableGraphicsRequest theRequest) {
		//System.out.println("Painting " + theRequest);
    	if (theRequest.getSource() != theFrame.getID())
			return;
    	g.processRequest(theRequest);
//		Object[] args = theRequest.getArgs();
//		if (theRequest.getName().equals(theRequest.DRAW_LINE)) {
//			//System.out.println("Drawing line ");
//			g.drawLine( (Integer) args[0],(Integer) args[1], (Integer) args[2], (Integer) args[3]);
//		} else if (theRequest.getName().equals(theRequest.DRAW_RECT)) {
//			g.drawRect( (Integer) args[0],(Integer) args[1], (Integer) args[2], (Integer) args[3]);
//		} else if (theRequest.getName().equals(theRequest.DRAW_OVAL)) {
//			g.drawOval( (Integer) args[0],(Integer) args[1], (Integer) args[2], (Integer) args[3]);
//		} else if (theRequest.getName().equals(theRequest.DRAW_STRING)) {
//			//System.out.println("Drawing String ");
//			g.drawString( (String) args[0], (Integer) args[1], (Integer) args[2]);
//		}
	}
	

}
