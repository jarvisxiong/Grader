//package gradingTools.comp999junit.assignment1.testables.secredit;
import util.annotations.Explanation;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
public interface SEPoint {
	public int getX(); 
	public int getY(); 	
	public double getAngle(); 
	public double getRadius();
//	void print(String aString, SEPoint aPoint); 
	void print();
	public SEPoint translate (SEPoint aPoint, int anXDelta, int aYDelta);


}
