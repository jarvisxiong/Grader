package gradingTools.comp999junit.assignment1.testcases;


//import org.junit.Test;
import org.junit.Test;


public class PointAngleMinusNinetyDegreeTest extends PointAngleTest {
	
	public  PointAngleMinusNinetyDegreeTest() {
		checkStructure = false;
	}
	@Test
	public void test() {	
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
	}
	

}
