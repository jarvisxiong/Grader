package gradingTools.comp999junit.assignment1.testcases.multi;


//import org.junit.Test;
import org.junit.Test;

import util.annotations.Group;
import util.annotations.MaxValue;
@MaxValue(10)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.POLAR_COORDINATES)
@Group(MultiAbstractPointTest.ANGLE_TESTS)
public class MultiPointAngleFortyFiveDegreeTest extends MultiPointAngleTest {

	
	public  MultiPointAngleFortyFiveDegreeTest() {
		checkStructure = false;	
	}
	@Test
	public void test() {
		test(10, 10, 14.142, Math.PI/4); // 45 degree angle		
	}
	
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertAngle(aComputedAngle, aCorrectAngle);
//		assertRadius(aComputedRadius, aCorrectRadius);
	}	

}
