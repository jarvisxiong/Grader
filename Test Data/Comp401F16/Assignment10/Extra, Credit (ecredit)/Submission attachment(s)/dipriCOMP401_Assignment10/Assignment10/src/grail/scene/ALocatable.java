package grail.scene;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import grail.Locatable;
import grail.PropertyListenerSupport;
import util.models.PropertyListenerRegisterer;
@Tags({"Locatable"})
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({ "x", "y"})
@EditablePropertyNames({ "x", "y" })
public class ALocatable implements Locatable {
 int x = 0;
 int y = 0;
 PropertyListenerSupport propertySupport = new APropertyListenerSupport();
 public int getX(){
	 return x;
 }
 public int getY(){
	 return y;
 }
 public void setX(int anInt) {
	 	int oldX = x;
		x = anInt;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "X", oldX,
                anInt));
	}

	public void setY(int anInt) {
		int oldY = y;
		y = anInt;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Y", oldY,
                anInt));
	}
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertySupport.add(arg0);	
	}
}
