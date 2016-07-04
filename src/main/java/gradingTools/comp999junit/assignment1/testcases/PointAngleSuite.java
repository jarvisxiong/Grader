package gradingTools.comp999junit.assignment1.testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import util.annotations.MaxValue;

@RunWith(Suite.class)
@Suite.SuiteClasses({

   PointAngleZeroDegreeTest.class,
   PointAngleNinetyDegreeTest.class,
   PointAngleFortyFiveDegreeTest.class,
   PointAngleMinusNinetyDegreeTest.class,

   
})
@MaxValue(13)
public class PointAngleSuite {


}

