package gradingTools.comp401f16.assignment1.testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import util.annotations.Explanation;
import util.annotations.MaxValue;

@RunWith(Suite.class)
@Suite.SuiteClasses({

   TerminatesWithPeriodOutputTest.class,
   TerminatesWithPeriodSumTest.class,
   TerminatesWithPeriodProductTest.class,
   
})
@MaxValue(5)
@Explanation("Handles Variable Spaces")
public class TerminatesWithPeriodSuite {


}

