package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
public class ALine extends ABoxDefinedShape implements Line {
	public ALine(int initX, int initY, int initHeight, int initWidth) {
		setX(initX);
		setY(initY);
		setHeight(initHeight);
		setWidth(initWidth);
	}
	public ALine() { };
}
