package gradingTools.comp401f16.assignment1.testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import util.annotations.Explanation;
import util.annotations.MaxValue;

@RunWith(Suite.class)
@Suite.SuiteClasses({

   VariableSpaceOutputTest.class,
   VariableSpaceSumTest.class,
   VariableSpaceProductTest.class,
   
})
@MaxValue(5)
@Explanation("Handles Variable Spaces")
public class VariableSpaceSuite {


}

