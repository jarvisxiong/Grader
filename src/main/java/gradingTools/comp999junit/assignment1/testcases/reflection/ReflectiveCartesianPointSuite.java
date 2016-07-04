package gradingTools.comp999junit.assignment1.testcases.reflection;

import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleSuite;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxyFactory;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointRadiusTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

//@RunWith(Suite.class)
@RunWith(Suite.class)
@Suite.SuiteClasses({
   AReflectivePointMainTest.class,
   AReflectivePointProxy.class,
   MultiPointAngleSuite.class,
//   APointAngleZeroDegreeTest.class,
//   APointAngleNinetyDegreeTest.class,
//   APointAngleFortyFiveDegreeTest.class,
//   APointAngleMinusNinetyDegreeTest.class,
   MultiPointRadiusTest.class,
   
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
		MultiPointProxyFactory.setPointProxy(new AReflectivePointProxy());		
		Result aResult = JUnitCore.runClasses(ReflectiveCartesianPointSuite.class);
		for (Failure failure : aResult.getFailures()) {
	         System.out.println(failure.toString());
	      }
	    System.out.println(aResult.wasSuccessful());
	}
	

}

