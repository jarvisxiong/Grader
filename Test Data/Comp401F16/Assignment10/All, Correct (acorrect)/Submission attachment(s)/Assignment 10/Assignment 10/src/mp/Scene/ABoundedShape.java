package mp.Scene;

import java.beans.PropertyChangeEvent;

import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"BoundedShape"})
@PropertyNames({"Width","Height","X","Y"})
public class ABoundedShape extends ALocatable implements BoundedShape{
	protected int width,height;
	public int getWidth() {
		return width;
		}
	public void setWidth(int newVal){
		int oldVal=width;
		width=newVal;
		aListener.notifyAllListeners(new PropertyChangeEvent(this, "Width",oldVal, newVal));
	}
	public int getHeight() {return height;}
	public void setHeight(int newVal){
		int oldVal=height;
		height=newVal;
		aListener.notifyAllListeners(new PropertyChangeEvent(this, "Height",oldVal, newVal));
	}
}
