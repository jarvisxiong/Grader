package grail.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class APropertyListenerSupport implements PropertyListenerSupport{
	private final int maxSize = 50;
	PropertyChangeListener[] contents = new PropertyChangeListener[maxSize];
	int size = 0;
	
	public int size() {
		return size;
	}

	private boolean isFull() {
		return size == maxSize;
	}
	
	public PropertyChangeListener elementAt(int index) {
		return contents[index];
	}

	
	public void addElement(PropertyChangeListener l) {
		if (isFull()) {
			//do nothing
		} else {
			contents[size] = l;
			size++;
		}
		
	}

	
	public void notifyAllListeners(PropertyChangeEvent event) {
		for (int index = 0; index < size(); index++) {
			elementAt(index).propertyChange(event);
	  }
	}

}
