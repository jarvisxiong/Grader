package mp;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeListener;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Visible;
@PropertyNames({"X", "Y"})
@EditablePropertyNames({"X", "Y"})
@Tags({"Locatable"})

public class ALocatable implements Locatable{
	int x, y;
	List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>(); //List that stores all Observers
	
@Visible(false)
	public void setX(int newX) {
		int oldVal = getX();
		x = newX;
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "X", oldVal, newX);
		alertListeners(newEvent);
	}

@Visible(false)
	public void setY(int newY) {
		int oldVal = getY();
		y = newY;
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "Y", oldVal, newY);
		alertListeners(newEvent);
	}

@Visible(false)
	public int getX() {return x;}
@Visible(false)
	public int getY() {return y;}

	public void addPropertyChangeListener (PropertyChangeListener aListener) {listeners.add(aListener);}
	
	protected void alertListeners(PropertyChangeEvent newEvent) {
		for (int x = 0; x < listeners.size(); x++) {
			listeners.get(x).propertyChange(newEvent);
			}
		}
}
