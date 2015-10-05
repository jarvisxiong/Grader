package grail;
import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
public interface Point {
	public int getX(); 
	public int getY(); 
	public void setX(int newX);
	public void setY(int newY);

}
