package mp.ui;

import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;
import util.models.PropertyListenerRegisterer;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.ui.mvc.APropertyListenerSupport;
import mp.ui.mvc.PropertyListenerSupport;


@Tags("Locatable")
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"X", "Y"}) 
@EditablePropertyNames({"X", "Y"})

public class ALocatable implements Locatable {
	
	private int x, y;
	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	
	public ALocatable() {}
	public ALocatable(int theX, int theY) {
		x = theX;
		y = theY;
	}
	@Visible(false)
	public int getX() {
		return x;
	}
	public void setX(int newX) {
		int oldVal = getX();  
		x = newX;
		propertyListenerSupport.notifyAllListeners(
			new PropertyChangeEvent(this, "X", oldVal, newX));
	}
	@Visible(false)
	public int getY() {
		return y;
	}
	public void setY(int newY) {
		int oldVal = getY();
		y = newY;
		propertyListenerSupport.notifyAllListeners( 
			new PropertyChangeEvent(this, "Y", oldVal, newY));
	}
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.addElement(listener);
	}

}
