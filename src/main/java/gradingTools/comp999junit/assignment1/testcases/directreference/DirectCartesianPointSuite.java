package gradingTools.comp999junit.assignment1.testcases.directreference;

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
   ADirectPointMainTest.class,
   ADirectPointProxy.class,
   MultiPointAngleSuite.class,
//   APointAngleZeroDegreeTest.class,
//   APointAngleNinetyDegreeTest.class,
//   APointAngleFortyFiveDegreeTest.class,
//   APointAngleMinusNinetyDegreeTest.class,
   MultiPointRadiusTest.class,
   
})
public class DirectCartesianPointSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	public static void main (String[] args) {
		MultiPointProxyFactory.setPointProxy(new ADirectPointProxy());
		Result aResult = JUnitCore.runClasses(DirectCartesianPointSuite.class);
		
		for (Failure failure : aResult.getFailures()) {
	         System.err.println("Failed Test:" + failure.toString());
	    }
	    System.out.println(aResult.wasSuccessful());
	    System.exit(0);
	}
	

}

