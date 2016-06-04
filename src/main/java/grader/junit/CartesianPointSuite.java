package grader.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.emory.mathcs.backport.java.util.Arrays;
import grader.junit.test.APointAngleFortyFiveDegreeTest;
import grader.junit.test.APointAngleMinusNinetyDegreeTest;
import grader.junit.test.APointAngleNinetyDegreeTest;
import grader.junit.test.APointAngleZeroDegreeTest;
import grader.junit.test.APointRadiusTest;
import grader.junit.test.APointStructureTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   APointStructureTest.class,
   APointAngleZeroDegreeTest.class,
   APointAngleNinetyDegreeTest.class,
   APointAngleFortyFiveDegreeTest.class,
   APointAngleMinusNinetyDegreeTest.class,
   APointRadiusTest.class,
   
})
public class CartesianPointSuite {
//	public static final String POLAR_COORDINATES = "Polar Coordinates";
	public static final String ANGLE_TESTS = "Angle Tests";
	public static void main (String[] args) {
		Suite.SuiteClasses aSuiteClassAnnotation = CartesianPointSuite.class.getAnnotation(Suite.SuiteClasses.class);
		Class[] aTestClasses = aSuiteClassAnnotation.value();
		System.out.println(Arrays.toString(aTestClasses));
	}

}

