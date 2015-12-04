package mp.shapes;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public interface AreaShape extends BoundedShape{
	public void move(int xOffset, int yOffset);
}
