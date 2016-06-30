package gradingTools.comp999junit.assignment1.testcases;


import org.junit.Test;
public class PointAngleFortyFiveDegreeTest extends PointAngleTest {

	
	public  PointAngleFortyFiveDegreeTest() {
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
