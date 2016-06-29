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

   ECPointAngleZeroDegreeTest.class,
   ECPointAngleNinetyDegreeTest.class,
   ECPointAngleFortyFiveDegreeTest.class,
   ECPointAngleMinusNinetyDegreeTest.class,

   
})
public class ECPointAngleSuite {


}

