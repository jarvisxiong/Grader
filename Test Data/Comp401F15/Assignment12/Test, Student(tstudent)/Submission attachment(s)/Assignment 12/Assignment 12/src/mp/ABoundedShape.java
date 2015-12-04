package mp;

import java.beans.PropertyChangeEvent;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"X", "Y","Width","Height"})
@EditablePropertyNames({"X", "Y","Width","Height"})
@Tags({"BoundedShape"})

public class ABoundedShape extends ALocatable implements BoundedShape{
	int height, width;
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public void setWidth(int newWidth) {
		int oldWidth = getWidth();
		width = newWidth;
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "Width", oldWidth, newWidth);
		alertListeners(newEvent);
	}

	public void setHeight(int newHeight) {
		int oldHeight = getHeight();
		height = newHeight;
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "Height", oldHeight, newHeight);
		alertListeners(newEvent);
	}
}
