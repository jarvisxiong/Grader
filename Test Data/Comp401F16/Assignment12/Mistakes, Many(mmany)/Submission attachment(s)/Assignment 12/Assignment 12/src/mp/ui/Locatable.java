package mp.ui;

import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags("Locatable")
@PropertyNames({"X", "Y"}) 
@EditablePropertyNames({"X", "Y"})

public interface Locatable extends PropertyListenerRegisterer {
	public int getX();
	public void setX(int newX);
	public int getY();
	public void setY(int newY);
	public void addPropertyChangeListener(PropertyChangeListener listener);
}
