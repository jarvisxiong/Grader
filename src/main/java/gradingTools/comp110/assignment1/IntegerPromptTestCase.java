package gradingTools.comp110.assignment1;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class IntegerPromptTestCase extends BasicTestCase {
	public IntegerPromptTestCase() {
		  super("Hello World printer test case");
    }

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try{
			 RunningProject runningProject = project.launch("", 3);
	            String output = runningProject.await();

	            // Now you can test the output for certain things
	            if (output.trim().toLowerCase().contains("int"))
	                return pass();
	            else if(output.trim().length()==0)
	            	return fail("Program does not output any prompts");
	            else throw new NotGradableException();
		}
		
		catch (NotRunnableException e) {
            throw new NotGradableException();
        }
	}


}
