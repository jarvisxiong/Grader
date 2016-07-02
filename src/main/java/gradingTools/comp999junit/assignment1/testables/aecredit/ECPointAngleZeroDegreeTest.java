package gradingTools.comp999junit.assignment1.testables.aecredit;


//import org.junit.Test;
import org.junit.Test;

public class ECPointAngleZeroDegreeTest extends ECPointAngleTest {	
	@Test
	public void test() {	
		test(10, 0, 10.0, 0); // 0 degree angle		
	}
}
