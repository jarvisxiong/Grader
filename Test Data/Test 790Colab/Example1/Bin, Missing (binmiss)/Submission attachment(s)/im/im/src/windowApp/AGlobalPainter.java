package windowApp;

import util.awt.ADelegateFrame;
import util.awt.AnOutputQueue;
import util.awt.ListenableGraphics;
import util.awt.ListenablePainter;
import util.awt.OutputListener;
import util.awt.SerializableFrameRequest;
import util.awt.SerializableGraphicsRequest;

public class AGlobalPainter  implements ListenablePainter, OutputListener {
	SerializableGraphicsRequest lastGraphicsRequest;
	String lastFrameTitle;	
	ADelegateFrame globalFrame;
	public AGlobalPainter(ADelegateFrame theGlobalFrame) {
		globalFrame = theGlobalFrame;
		globalFrame.addPainter(this);
		AnOutputQueue.addOutputListener(this);
	}	
	public void paint(ADelegateFrame theFrame, ListenableGraphics g) {
		if (lastGraphicsRequest == null) return;
		Object[] args = lastGraphicsRequest.getArgs();			
		g.drawString(lastFrameTitle+':'+(String) args[0], (Integer) args[1], (Integer) args[2]);
				
	}	
	public void newGraphicsRequest(SerializableGraphicsRequest graphicsRequest) {
		if (graphicsRequest.getSource() == globalFrame.getID()	||
	       !graphicsRequest.getName().equals(SerializableGraphicsRequest.DRAW_STRING))
			 return;
		lastGraphicsRequest = graphicsRequest;
		lastFrameTitle = ADelegateFrame.getFrame(graphicsRequest.getSource()).getTitle();
		globalFrame.repaint();
	}
    public void newFrameRequest(SerializableFrameRequest frameRequest) {
		
	}	
}
