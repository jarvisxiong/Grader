package gradingTools.comp401f15.assignment3.testcases;

import java.util.List;

import framework.execution.NotRunnableException;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;


public abstract class AbstractTokensTestCase extends OutputAndErrorCheckingTestCase {

    public AbstractTokensTestCase(String aTestCase) {
        super(aTestCase);
    }
    


//    String simplifiedInputWithNoEndingSpace = "10 20 \n40 50 \n.\n";
//    String simplifiedInputWithEndingSpace = "10 20\n40 50 \n.\n";
//    String inputWithEndingSpace = "10 20 \n40 50 \n.the end\n";
//    String inputWithNoEndingSpace = "10 20\n40 50 \n.the end\n";
//    String[] expectedOutputs = {"30", "200", "90", "2000"};
    
    abstract protected String simplifiedInputWithNoEndingSpace();
    abstract protected String simplifiedInputWithEndingSpace();
    abstract protected String inputWithEndingSpace() ;
    abstract String inputWithNoEndingSpace ();

 
//    String[] expectedOutputs = {"30", "200", "90", "2000"};
   abstract String[] expectedOutputs(); 
    
    protected double simplifiedCredit = 0.3;
    
    @Override
    protected boolean isValidOutput(List<String> anOutput, String[] anExpectedStrings){
 	   return isValidOutputInDifferentLines(anOutput, anExpectedStrings);
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            OutputErrorStatus retVal = test(project, inputWithEndingSpace(), expectedOutputs(), autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS 
            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return pass();
            retVal = test(project, inputWithNoEndingSpace(), expectedOutputs(), autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
            	|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return pass();
            retVal = test(project, simplifiedInputWithNoEndingSpace(), expectedOutputs(), autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return partialPass(simplifiedCredit, "Cannot scan numbers in mixed token case");            
            retVal = test(project, simplifiedInputWithEndingSpace(), expectedOutputs(), autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return partialPass(simplifiedCredit, "Cannot scan numbers in mixed token case");

            return fail("Cannot scan numbers", autoGrade);
            
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

