package gradingTools.comp999junit.assignment1.testables.wrongangle;
import gradingTools.comp999junit.assignment1.testables.allcorrect.Point;
import util.annotations.Explanation;
import util.annotations.Tags;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
@Tags({"Point"}) // the interface is named wrong
public interface WrongPoint {
	public int getX(); 
	public int getY(); 	
	public double getAngle(); 
	public double getRadius();
	void print(Point aPoint, String aString); 
}
