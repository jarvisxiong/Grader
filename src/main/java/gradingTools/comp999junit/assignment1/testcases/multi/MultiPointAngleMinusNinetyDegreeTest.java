package gradingTools.comp999junit.assignment1.testcases.multi;


//import org.junit.Test;
import org.junit.Test;

import util.annotations.Group;
import util.annotations.MaxValue;

@MaxValue(2)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.POLAR_COORDINATES)
@Group(MultiAbstractPointTest.ANGLE_TESTS)
public class MultiPointAngleMinusNinetyDegreeTest extends MultiPointAngleTest {
	
	public  MultiPointAngleMinusNinetyDegreeTest() {
		checkStructure = false;
	}
	@Test
	public void test() {	
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
	}
	

}
