package gradingTools.comp999junit.assignment1.testables.aecredit;


//import org.junit.Test;

public class ECPointAngleTest extends ECAbstractPointTest {
	
	
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertAngle(aComputedAngle, aCorrectAngle);
//		assertRadius(aComputedRadius, aCorrectRadius);
	}	

}
