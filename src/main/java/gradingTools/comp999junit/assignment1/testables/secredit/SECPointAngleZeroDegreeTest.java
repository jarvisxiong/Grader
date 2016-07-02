package gradingTools.comp999junit.assignment1.testables.secredit;


//import org.junit.Test;
import org.junit.Test;

import util.annotations.Tags;
@Tags({"AngleTest"})
public class SECPointAngleZeroDegreeTest extends SEAbstractPointTest {	
	@Test
	public void test() {	
		test(10, 0, 10.0, 0); // 0 degree angle		
	}
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertAngle(aComputedAngle, aCorrectAngle);
//		assertRadius(aComputedRadius, aCorrectRadius);
	}
}
