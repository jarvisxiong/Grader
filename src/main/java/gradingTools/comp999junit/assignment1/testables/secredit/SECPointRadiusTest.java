package gradingTools.comp999junit.assignment1.testables.secredit;


//import org.junit.Test;
import org.junit.Test;
public class SECPointRadiusTest extends SEAbstractPointTest  {	
		
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
