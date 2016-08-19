package grail.draw.objects;

import java.beans.PropertyChangeEvent;

import grail.draw.interfaces.MutableShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
public abstract class BasicMutableShape extends BasicShape implements MutableShape{

	public BasicMutableShape() {
		super();
	}
	
	public BasicMutableShape(int x, int y) {
		super(x,y);
	}

	
	public void setHeight(int newHeight) {
		int oldVal = this.height;
		this.height = newHeight;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldVal, this.height));
		}
		
		
	}

	
	public void setWidth(int newWidth) {
		int oldVal = this.height;
		this.width = newWidth;
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldVal, this.width));
		}
	}

}
