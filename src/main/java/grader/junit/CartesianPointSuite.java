package grader.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.emory.mathcs.backport.java.util.Arrays;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   ACartesianPointJUnitTester.class,
   AReflectionBasedCartesianPointJUnitTester.class
})
public class CartesianPointSuite {
	public static final String POLAR_COORDINATES = "Polar Coordinates";
	public static void main (String[] args) {
		Suite.SuiteClasses aSuiteClassAnnotation = CartesianPointSuite.class.getAnnotation(Suite.SuiteClasses.class);
		Class[] aTestClasses = aSuiteClassAnnotation.value();
		System.out.println(Arrays.toString(aTestClasses));
	}

}

