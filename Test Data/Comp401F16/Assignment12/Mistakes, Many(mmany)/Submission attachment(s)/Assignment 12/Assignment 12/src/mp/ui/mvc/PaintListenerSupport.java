package mp.ui.mvc;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;

public interface PaintListenerSupport {
	
	public int size();
	public PaintListener elementAt (int index);
	public void addElement(PaintListener l);
	public void repaintAll(Graphics g);

}

