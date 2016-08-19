package grail.draw.objects;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import grail.draw.interfaces.ColoredShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;


@PropertyNames({"X", "Y", "Width", "Height", "Color", "Filled"})
@EditablePropertyNames({"X", "Y", "Color", "Filled"})
public class BasicColoredShape extends BasicShape implements ColoredShape{
	protected Color color;
	protected boolean filled;
	
	public BasicColoredShape() {
		super();
		this.color = Color.BLACK;
		this.filled = false;
	}

	
	public Color getColor() {
		return this.color;
	}

	
	public void setColor(Color newColor) {
		Color oldVal = this.color;
		this.color = newColor;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent (this,"Color", oldVal, this.color ));
		}
		
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
