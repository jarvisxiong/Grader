package gradingTools.comp999junit.assignment1.testcases;

import gradingTools.comp999junit.assignment1.testcases.directreference.ADirectPointProxy;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import util.annotations.MaxValue;

//@RunWith(Suite.class)
@RunWith(Suite.class)
@Suite.SuiteClasses({

   APointAngleZeroDegreeTest.class,
   APointAngleNinetyDegreeTest.class,
   APointAngleFortyFiveDegreeTest.class,
   APointAngleMinusNinetyDegreeTest.class,

   
})
@MaxValue(13)
public class PointAngleSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	public static void main (String[] args) {
		PointProxyFactory.setPointProxy(new ADirectPointProxy());
		Result aResult = JUnitCore.runClasses(PointAngleSuite.class);
		
		for (Failure failure : aResult.getFailures()) {
	         System.err.println("Failed Test:" + failure.toString());
	    }
	    System.out.println(aResult.wasSuccessful());
	    System.exit(0);
	}
	

}
