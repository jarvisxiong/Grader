package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.OVAL_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
public class AnOval extends ABoxDefinedShape implements Oval {	
	public AnOval(int initX, int initY, int initWidth, int initHeight) {
		setX(initX);
		setY(initY);
		setWidth(initWidth);
		setHeight(initHeight);
	}

}
