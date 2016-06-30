package gradingTools.comp999junit.assignment1.testcases.multi;


//import org.junit.Test;
import org.junit.Test;

import util.annotations.Group;
import util.annotations.MaxValue;

@MaxValue(2)
//@Explanation("Radius and Angle Correctly Computed")
@Group(MultiAbstractPointTest.ANGLE_TESTS)
public class MultiPointAngleZeroDegreeTest extends MultiPointAngleTest {	
	@Test
	public void test() {	
		test(10, 0, 10.0, 0); // 0 degree angle		
	}
}
