package gradingTools.comp999junit.assignment1.testcases;
import util.annotations.Explanation;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
public interface Point {
	public int getX(); 
	public int getY(); 	
	public double getAngle(); 
	public double getRadius();
	void print(String aString, Point aPoint);
	Point translate(Point aPoint, int anXDelta, int aYDelta); 
}
