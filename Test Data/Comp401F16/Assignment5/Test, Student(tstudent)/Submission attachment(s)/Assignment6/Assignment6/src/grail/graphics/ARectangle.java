package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
public class ARectangle extends ABoxDefinedShape implements Rectangle {
	
	public ARectangle(int initX, int initY, int initHeight, int initWidth) {
		setX(initX);
		setY(initY);
		setWidth(initWidth);
		setHeight(initHeight);
	}
}
