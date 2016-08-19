package grail.paint;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import util.annotations.Tags;

@Tags({"ObservableBridgeScenePainter"})
public class AObservablePainter extends Component implements ObservablePainter {
	
	ArrayList<PaintListener> listenerList = new ArrayList<PaintListener>();
	
	public AObservablePainter() {
		setFocusable(true);
	}
	
	
    public void paint(Graphics g) {
		int index = 0;
    	while (index < listenerList.size()) {
			PaintListener currentListener = listenerList.get(index);
			currentListener.paint((Graphics2D)g);
			index++;
		}
    }
	
	public void repaint() {
		int index = 0;
		while (index < listenerList.size()) {
			PaintListener currentListener = listenerList.get(index);
			currentListener.paint((Graphics2D)this.getGraphics());
			index++;
		}	
		if (this.getParent() != null) {
			this.getParent().repaint();
		}
	
	}
	
	@Tags({"addPaintListener"})
	public void addPaintListener(PaintListener listener) {
		listenerList.add(listener);
	}



}
