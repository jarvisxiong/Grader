package gradingTools.comp999junit.assignment1.testables.ecredit;

import gradingTools.comp999junit.assignment1.testcases.directreference.ADirectPointProxy;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import util.annotations.MaxValue;

@RunWith(Suite.class)
@Suite.SuiteClasses({

   ECreditPointAngleZeroDegreeTest.class,
   ECreditPointAngleNinetyDegreeTest.class,
   ECreditPointAngleFortyFiveDegreeTest.class,
   ECredittPointAngleMinusNinetyDegreeTest.class,

   
})
public class ECreditPointAngleSuite {


}

