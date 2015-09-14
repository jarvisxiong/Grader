package gradingTools.comp401f15.assignment3.testcases;

import java.util.List;
import java.util.Map;

import wrappers.framework.project.ProjectWrapper;
import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.ExecutionUtil;
import gradingTools.assignment1.FlexibleProgramRunner;


public class NumberTokenBeanTestCase extends BasicTestCase {
	protected String[] beanDescriptions =  {null, "Number", ".*Number.*", ".*Number.*"};

	protected String input = "006500";
	protected int value = 6500;

    public NumberTokenBeanTestCase() {
        super("Number Token Bean Case");
    }
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
        	Map<String, Object> retVal = ExecutionUtil.testBeanWithStringConstructor(getCheckable().getName(), project, beanDescriptions, input, "Input", input, "Value", value);
        	
        	return null;
        	
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
   
}

