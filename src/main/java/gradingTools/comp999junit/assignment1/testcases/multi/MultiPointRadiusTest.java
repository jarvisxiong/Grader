package gradingTools.comp999junit.assignment1.testcases.multi;


//import org.junit.Test;
import org.junit.Test;

import util.annotations.IsExtra;
import util.annotations.MaxValue;
@IsExtra(true)
@MaxValue(15)
public class MultiPointRadiusTest extends MultiAbstractPointTest  {	
		
	@Test
	// all or nothing for radius
	public void test() {
		test(10, 10, 14.142, Math.PI); // 45 degree angle
		test(5, 0, 5.0, 0); // 0 degree angle
		test(0, 7, 7, Math.PI / 2); // 90 degree angle
		test(10, 10, 14.142, Math.PI / 4); // 90 degree angle
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
	}
	
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertRadius(aComputedRadius, aCorrectRadius);
	}	

}
