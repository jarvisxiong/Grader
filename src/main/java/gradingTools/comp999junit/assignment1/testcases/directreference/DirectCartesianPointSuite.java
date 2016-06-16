package gradingTools.comp999junit.assignment1.testcases.directreference;

import gradingTools.comp999junit.assignment1.testcases.APointAngleFortyFiveDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleMinusNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleZeroDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointRadiusTest;
import gradingTools.comp999junit.assignment1.testcases.PointProxyFactory;

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
   APointAngleZeroDegreeTest.class,
   APointAngleNinetyDegreeTest.class,
   APointAngleFortyFiveDegreeTest.class,
   APointAngleMinusNinetyDegreeTest.class,
   APointRadiusTest.class,
   
})
public class DirectCartesianPointSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	public static void main (String[] args) {
		PointProxyFactory.setPointProxy(new ADirectPointProxy());
		Result aResult = JUnitCore.runClasses(DirectCartesianPointSuite.class);
		
		for (Failure failure : aResult.getFailures()) {
	         System.err.println("Failed Test:" + failure.toString());
	    }
	    System.out.println(aResult.wasSuccessful());
	    System.exit(0);
	}
	

}

