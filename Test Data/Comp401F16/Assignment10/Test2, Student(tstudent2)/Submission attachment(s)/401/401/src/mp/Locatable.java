package mp;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import main.APropertyListenerSupport;
import main.PropertyListenerSupport;

@Tags({"Locatable"})
@PropertyNames({"x","y"})
@EditablePropertyNames({"x","y"}) 
public class Locatable {
	public int x,y;
	PropertyListenerSupport propertySupport = new APropertyListenerSupport();
	
	// Getters-------------------------------------------------------------------------------------------------------------------------------------
			public int getX(){return x;}
			public int getY(){return y;}
			
	// Setters-------------------------------------------------------------------------------------------------------------------------------------
			public void setX(int newX){
				int oldVal = x;
				x = newX;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "X", oldVal, newX));
				}
			
			public void setY(int newY){
				int oldVal = y;
				y = newY;
				propertySupport.notifyAllListeners(new PropertyChangeEvent(this,"Y", oldVal, newY));
				}
			
			public void propertyChangeX(int newX, PropertyChangeEvent evt){
				x = newX;
			}
			public void propertyChangeY(int newY, PropertyChangeEvent evt){
				y = newY;
			}
}
