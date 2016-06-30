package gradingTools.comp999junit.assignment1.testcases.multi;


//import org.junit.Test;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;
@IsExtra(true)
@IsRestriction(false)
@MaxValue(15)
//@Explanation("Radius and Angle Correctly Computed")
@Group(MultiAbstractPointTest.ANGLE_TESTS)
public class MultiPointAngleTest extends MultiAbstractPointTest {
	
	
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertAngle(aComputedAngle, aCorrectAngle);
//		assertRadius(aComputedRadius, aCorrectRadius);
	}	

}
