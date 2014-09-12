package util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class APropertyChangeListenerManager implements IPropertyChangeListenerManager {
	private final Object source;
	private ArrayList<PropertyChangeListener> listeners = new ArrayList<>();

	public APropertyChangeListenerManager(Object source) {
		this.source = source;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		PropertyChangeEvent event = new PropertyChangeEvent(source, propertyName, oldValue, newValue);
		firePropertyChange(event);
	}

	@Override
	public void firePropertyChange(PropertyChangeEvent event) {
		for (PropertyChangeListener l : listeners) {
			l.propertyChange(event);
		}
	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return listeners.toArray(new PropertyChangeListener[listeners.size()]);
	}

}
