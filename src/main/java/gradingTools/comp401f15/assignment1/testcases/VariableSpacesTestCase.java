package gradingTools.comp401f15.assignment1.testcases;

import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.sharedTestCase.OldOutputAndErrorCheckingTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/7/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class VariableSpacesTestCase extends OldOutputAndErrorCheckingTestCase {

    public VariableSpacesTestCase() {
        super("Variable Space Test");
    }


    String simplifiedInputWithNoEndingSpace = "10   20 \n   40 50 \n.";
   
//    String[] expectedOutputs = {"30", "200", "90", "2000"};
    String[] expectedOutputs = {".*30.*", ".*200.*", ".*90.*", ".*2000.*"};

    


    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            OutputErrorStatus retVal = test(project, simplifiedInputWithNoEndingSpace, expectedOutputs, autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS) 
            	return pass();
            else if (retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS) {
            	return partialPass(0.5, "Errors with  variable space");
            }
            return fail ("Incorrect output with variable spaces");
           
            
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

