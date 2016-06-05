package gradingTools.comp999junit.assignment1.testcases.reflection.tags;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.emory.mathcs.backport.java.util.Arrays;
import gradingTools.comp999junit.assignment1.testcases.APointAngleMinusNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleZeroDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointRadiusTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleFortyFiveDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.PointProxyFactory;

//@RunWith(Suite.class)
@RunWith(Suite.class)
@Suite.SuiteClasses({
   ATaggingPointProxy.class,
   APointAngleZeroDegreeTest.class,
   APointAngleNinetyDegreeTest.class,
   APointAngleFortyFiveDegreeTest.class,
   APointAngleMinusNinetyDegreeTest.class,
   APointRadiusTest.class,
   
})
public class ReflectiveCartesianPointSuite {
//	public static final String POLAR_COORDINATES = "Polar Coordinates";
//	public static final String ANGLE_TESTS = "Angle Tests";
//	public ReflectiveCartesianPointSuite() {
//		System.out.println ("Test suite");
//
//	}
//	static {
//		System.out.println ("Test suite");
//	}
	public static void main (String[] args) {
//		Suite.SuiteClasses aSuiteClassAnnotation = ReflectiveCartesianPointSuite.class.getAnnotation(Suite.SuiteClasses.class);
//		Class[] aTestClasses = aSuiteClassAnnotation.value();
//		System.out.println(Arrays.toString(aTestClasses));
		PointProxyFactory.setPointProxy(new ATaggingPointProxy());
		JUnitCore.runClasses(ReflectiveCartesianPointSuite.class);
	}

}

