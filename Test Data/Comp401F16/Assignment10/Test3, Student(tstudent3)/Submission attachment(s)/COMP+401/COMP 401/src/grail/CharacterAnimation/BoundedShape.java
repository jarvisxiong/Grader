package grail.CharacterAnimation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;


@PropertyNames({"width","height","location","x","y"})
@EditablePropertyNames({"width","height","location","x","y"})
public class BoundedShape extends LocatableShape implements BoundedShapeInt{
    protected int width, height;
    PointInterface location;
    public final int MAX_SIZE = 50;
    PropertyChangeListener[] contents = new PropertyChangeListener[MAX_SIZE];
    int size = 0;
	
    public PointInterface getLocation(){return location;}
    public void setLocation(PointInterface newLocation){
    	location = newLocation;
    	setXY(location.getX(),location.getY());
    }
    
    public int getWidth() {return width;}
    public void setWidth(int newVal) {
    	int oldVal = width;
    	width = newVal;
    	notifyAllListeners(new PropertyChangeEvent(this, "WIDTH", oldVal, newVal));
    }
    
    public int getHeight() {return height;}
    public void setHeight(int newHeight) {
    	int oldVal = height;
    	height = newHeight;
    	notifyAllListeners(new PropertyChangeEvent(this, "HEIGHT", oldVal, newHeight));
    }
    

	public void rotate(int rotation) {
		int oldVal = width;
		width = width + rotation;
		notifyAllListeners(new PropertyChangeEvent(this, "width", oldVal, width));
		
	}
	
	
}
