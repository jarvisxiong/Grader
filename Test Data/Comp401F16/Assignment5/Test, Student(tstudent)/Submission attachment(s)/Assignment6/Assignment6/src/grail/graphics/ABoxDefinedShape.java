package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
public class ABoxDefinedShape extends ALocatable implements BoxDefinedShape {
	private int height, width;
	
	public int getHeight() { return height; }
	public int getWidth() { return width; }
	public void setHeight(int newHeight) { height = newHeight; }
	public void setWidth(int newWidth) { width = newWidth; }
}
