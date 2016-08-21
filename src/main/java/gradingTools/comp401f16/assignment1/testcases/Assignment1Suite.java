package gradingTools.comp401f16.assignment1.testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	SingleTokenSuite.class,
	MultipleTokenSuite.class,
	InvalidCharacterSuite.class,
	VariableSpaceSuite.class,
	TerminatesWithPeriodSuite.class,	   
})
public class Assignment1Suite {
	public static final String MAIN_CLASS_NAME = "main.Assignment1";
	
	
}
