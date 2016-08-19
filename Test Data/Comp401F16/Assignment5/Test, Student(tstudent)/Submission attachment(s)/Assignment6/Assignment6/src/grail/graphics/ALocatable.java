package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"X", "Y"})
@EditablePropertyNames({"X", "Y"})
public class ALocatable implements Locatable {
	private int x, y;
	
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int newX) { x = newX; }
	public void setY(int newY) { y = newY; }
}
