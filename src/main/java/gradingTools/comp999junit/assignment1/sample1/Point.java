package gradingTools.comp999junit.assignment1.sample1;
import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
public interface Point {
	public int getX(); 
	public int getY(); 	
	public double getAngle(); 
	public double getRadius(); 
}