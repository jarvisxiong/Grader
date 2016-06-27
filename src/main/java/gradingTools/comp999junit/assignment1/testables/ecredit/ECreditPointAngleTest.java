package gradingTools.comp999junit.assignment1.testables.ecredit;


//import org.junit.Test;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;

@Group(ECreditAbstractPointTest.ANGLE_TESTS)
public class ECreditPointAngleTest extends ECreditAbstractPointTest {
	
	
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertAngle(aComputedAngle, aCorrectAngle);
//		assertRadius(aComputedRadius, aCorrectRadius);
	}	

}
