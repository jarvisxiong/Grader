package mp;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.IMAGE_PATTERN)

public interface Head extends Locatable{
	public ImageShape getHead ();
	public void move(int addX, int addY);
	public String getImageFileName();
	public void approach();
	public void pass();
	public void fail();
	public void reset();
	public void relocate(int newX, int newY);
}
