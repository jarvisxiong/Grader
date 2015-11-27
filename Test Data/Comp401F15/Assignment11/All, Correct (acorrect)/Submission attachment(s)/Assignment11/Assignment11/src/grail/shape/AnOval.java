package grail.shape;


import java.awt.Color;
import java.beans.PropertyChangeEvent;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags("Bounded Shape")
@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class AnOval extends ABoundedShape implements Oval {

	boolean filled = false;
	public AnOval(int initX, int initY, int initWidth, int initHeight) {
		super(initX, initY, initWidth, initHeight);
	}

	public void setFilled(boolean newVal){
		boolean oldVal = filled;
		filled = newVal;
		if (propertySupport != null)
			propertySupport.notifyAllListeners(new PropertyChangeEvent(
					this, "filled", oldVal, newVal));
	}
	
	public boolean getFilled(){
		return filled;
	}
}
