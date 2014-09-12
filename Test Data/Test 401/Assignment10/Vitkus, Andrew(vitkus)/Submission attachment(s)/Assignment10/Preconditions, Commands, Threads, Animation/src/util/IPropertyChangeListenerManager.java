package util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface IPropertyChangeListenerManager {
	public void addPropertyChangeListener(PropertyChangeListener listener);

	public void removePropertyChangeListener(PropertyChangeListener listener);

	public void firePropertyChange(String propertyName, Object oldValue, Object newValue);

	public void firePropertyChange(PropertyChangeEvent event);

	public PropertyChangeListener[] getPropertyChangeListeners();
}
