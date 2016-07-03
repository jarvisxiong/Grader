package gradingTools.comp401f15.assignment2.testcases;

import java.util.List;

import framework.execution.NotRunnableException;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;


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
    abstract protected String inputWithNoEndingSpace ();
    abstract protected String inputWithStartingAndEndingSpace ();

 
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
            retVal = test(project, inputWithStartingAndEndingSpace(), expectedOutputs(), autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
            	|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return pass();
            retVal = test(project, simplifiedInputWithNoEndingSpace(), expectedOutputs(), autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return partialPass(simplifiedCredit,  this.getName() + " failed");            
            retVal = test(project, simplifiedInputWithEndingSpace(), expectedOutputs(), autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return partialPass(simplifiedCredit, this.getName() + " failed");

            return fail(this.getName() + " failed", autoGrade);
            
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

