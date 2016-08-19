package grail.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface PropertyListenerSupport {
	public int size();
	public PropertyChangeListener elementAt(int index);
	public void addElement(PropertyChangeListener l);
	public void notifyAllListeners(PropertyChangeEvent event);
}
