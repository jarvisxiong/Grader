package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
public interface Locatable {
	public int getX();
	public int getY();
	public void setX(int newX);
	public void setY(int newY);
}
