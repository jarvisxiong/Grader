package grail.draw.objects;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.draw.interfaces.Locator;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@PropertyNames({"X", "Y"})
@EditablePropertyNames({"X", "Y"})
@Tags({"Locatable"})
public class BasicLocator implements Locator{
	protected int x,y;
	protected PropertyListenerSupport propertyListenerSupport = 
	         new APropertyListenerSupport();

	
	public BasicLocator() {
		this.x = 0;
		this.y = 0;
	}
	
	public BasicLocator(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	public int getX() {
		return this.x;
	}

	
	public void setX(int newX) {
		int oldVal = this.x;
		this.x = newX;
		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "X", oldVal,this.x));
		}
		
	}

	
	public int getY() {
		return y;
	}

	
	public void setY(int newY) {
		int oldVal = this.y;
		this.y = newY;
		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Y", oldVal,this.y));
		}
		
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyListenerSupport.addElement(arg0);
		
	}	

}
