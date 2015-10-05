package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
public interface Point extends Locatable {
	public double getAngle();
	public double getRadius();
}
