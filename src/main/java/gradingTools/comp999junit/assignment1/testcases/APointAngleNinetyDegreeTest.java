package gradingTools.comp999junit.assignment1.testcases;


//import org.junit.Test;
import org.junit.Test;

import util.annotations.Group;
import util.annotations.MaxValue;

@MaxValue(2)
@Group(AnAbstractPointTest.ANGLE_TESTS)
public class APointAngleNinetyDegreeTest extends APointAngleTest {	
	@Test
	public void test() {
		test(0, 10, 10.0, Math.PI / 2); // 90 degree angle
	}

}
