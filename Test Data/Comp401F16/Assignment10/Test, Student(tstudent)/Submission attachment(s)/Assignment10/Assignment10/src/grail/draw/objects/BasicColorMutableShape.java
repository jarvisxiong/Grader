package grail.draw.objects;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import grail.draw.interfaces.ColorMutableShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"X", "Y", "Height", "Width", "Color", "Filled"})
@EditablePropertyNames({"X", "Y", "Height", "Width", "Color", "Filled"})
public class BasicColorMutableShape extends BasicMutableShape implements ColorMutableShape{
	protected Color color;
	protected boolean filled;
	
	public BasicColorMutableShape() {
		super();
		this.color = Color.BLACK;
		this.filled = false;
	}

	
	public void setColor(Color newColor) {
		Color oldVal = this.color;
		this.color = newColor;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent (this,"Color", oldVal, this.color ));
		}
		
	}

	
	public Color getColor() {
		return color;
	}
	

	
	public boolean getFilled() {
		return this.filled;
	}

	
	public void setFilled(boolean isFilled) {
		boolean oldVal = this.filled;
		this.filled = isFilled;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent (this,"Filled", oldVal, this.filled ));
		}
	}

}
