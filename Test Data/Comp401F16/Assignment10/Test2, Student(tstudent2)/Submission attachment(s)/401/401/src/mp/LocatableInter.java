package mp;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Locatable"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface LocatableInter {
	public int getX();
	public int getY();
	
	public void setX(int newX);
	public void setY(int newY);
}
