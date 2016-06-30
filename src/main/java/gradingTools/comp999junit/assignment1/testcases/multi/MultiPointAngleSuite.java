package gradingTools.comp999junit.assignment1.testcases.multi;

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

   MultiPointAngleZeroDegreeTest.class,
   MultiPointAngleNinetyDegreeTest.class,
   MultiPointAngleFortyFiveDegreeTest.class,
   MultiPointAngleMinusNinetyDegreeTest.class,

   
})
@MaxValue(13)
public class MultiPointAngleSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	public static void main (String[] args) {
		MultiPointProxyFactory.setPointProxy(new ADirectPointProxy());
		Result aResult = JUnitCore.runClasses(MultiPointAngleSuite.class);
		
		for (Failure failure : aResult.getFailures()) {
	         System.err.println("Failed Test:" + failure.toString());
	    }
	    System.out.println(aResult.wasSuccessful());
	    System.exit(0);
	}
	

}

