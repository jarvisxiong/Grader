package mp.ui.mvc;

import java.awt.Component;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class APaintListenerSupport implements PaintListenerSupport {
 
	public final int MAX_SIZE = 50;
	PaintListener[] contents = new PaintListener[MAX_SIZE];
	int size = 0;
	
	public int size() {
		return size;
	}
	public PaintListener elementAt (int index) { 
		return contents[index];
	}
	boolean isFull() {
		return size == MAX_SIZE;
	}
	
	public void addElement(PaintListener l) { 
		if (isFull()) {
			System.out.println("Adding item to a full history"); 
		}
		else {
			contents[size] = l;
			size++; 
		}
	}
	public void repaintAll(Graphics g) { 
		for (int index = 0; index < size(); index++) {
		((Component) elementAt(index)).paint(g); 
		}
	}

}
