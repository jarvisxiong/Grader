package gradingTools.comp999junit.assignment1.testcases.multi;
import util.annotations.Explanation;
import util.annotations.Tags;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
@Tags({"Point"})
public interface MultiPoint {
	public int getX(); 
	public int getY(); 	
	public double getAngle(); 
	public double getRadius();
//	void print(String aString, Point aPoint);
	void print();
	MultiPoint translate(MultiPoint aPoint, int anXDelta, int aYDelta); 
}
