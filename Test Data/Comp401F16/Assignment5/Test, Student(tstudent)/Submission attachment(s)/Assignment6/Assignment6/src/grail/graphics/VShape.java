package grail.graphics;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Angle"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface VShape extends Locatable {
	public RotatingLine getLeftLine();
	public RotatingLine getRightLine();
	public void move(int newX, int newY);
}
