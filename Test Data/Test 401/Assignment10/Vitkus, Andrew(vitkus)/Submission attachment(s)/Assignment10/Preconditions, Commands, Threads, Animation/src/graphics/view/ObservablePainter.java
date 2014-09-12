package graphics.view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.event.EventListenerList;

import util.PaintListener;
import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({ "Observable Painter" })
public class ObservablePainter extends Component {
	private EventListenerList ell = new EventListenerList();
	
	public ObservablePainter() { }
	
	public void addPaintListener(PaintListener listener) {
		ell.add(PaintListener.class, listener);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//System.out.println("Paint!");
		for(PaintListener listener : ell.getListeners(PaintListener.class)) {
			listener.paint((Graphics2D) g);
		}
	}
}
