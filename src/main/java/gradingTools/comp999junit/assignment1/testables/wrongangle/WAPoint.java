package gradingTools.comp999junit.assignment1.testables.wrongangle;
import util.annotations.Explanation;
import util.annotations.Tags;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
@Tags({"Point"}) // the interface is named wrong
public interface WAPoint {
	public int getX(); 
	public int getY(); 	
	public double getAngle(); 
	public double getRadius();
	void print(WAPoint aPoint, String aString);
	WAPoint translate(int anXDelta, int aYDelta, WAPoint aPoint); 
}
