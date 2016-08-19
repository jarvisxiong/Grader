package grail.shape;


import java.awt.Color;
import java.beans.PropertyChangeEvent;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags("BoundedShape")
@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
public class ARectangle extends ABoundedShape implements Rectangle {

	boolean filled;

	public ARectangle(int initX, int initY, int initWidth, int initHeight) {
		super(initX, initY, initWidth, initHeight);
	}


	public void setFilled(boolean newVal) {
		boolean oldVal = filled;
		filled = newVal;
		if (propertySupport != null)
			propertySupport.notifyAllListeners(new PropertyChangeEvent(
					this, "filled", oldVal, newVal));
	}

	public boolean getFilled() {
		return filled;
	}
}
