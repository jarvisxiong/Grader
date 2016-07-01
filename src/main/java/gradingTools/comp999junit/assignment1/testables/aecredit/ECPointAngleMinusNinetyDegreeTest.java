package gradingTools.comp999junit.assignment1.testables.aecredit;


//import org.junit.Test;
import org.junit.Test;


public class ECPointAngleMinusNinetyDegreeTest extends ECPointAngleTest {
	
	public  ECPointAngleMinusNinetyDegreeTest() {
		checkStructure = false;
	}
	@Test
	public void test() {	
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
	}
	

}
