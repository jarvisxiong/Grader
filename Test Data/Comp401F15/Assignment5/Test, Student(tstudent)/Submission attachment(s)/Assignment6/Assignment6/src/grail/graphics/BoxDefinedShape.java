package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
public interface BoxDefinedShape extends Locatable {
	public int getHeight();
	public int getWidth();
	public void setHeight(int newHeight);
	public void setWidth(int newWidth);
}
