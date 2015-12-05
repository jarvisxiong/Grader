package grail.scene;

import java.beans.PropertyChangeEvent;

import grail.StandingArea;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({ "StandingArea" })
@PropertyNames({ "x", "y","width","height"})
@EditablePropertyNames({ "x", "y", "width", "height" })
@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class AStandingArea extends ABoundedShape implements StandingArea {

public AStandingArea(int initX, int initY, int width, int height){
	x = initX;
	y = initY;
	this.width = width;
	this.height = height;
}

public void setWidth(int newWidth){
	int oldVal = width;
	width = newWidth;
	propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldVal,
            width));
}
public void setHeight(int newHeight){
	int oldVal = height;
	height = newHeight;
	propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldVal,
            height));
}
}
