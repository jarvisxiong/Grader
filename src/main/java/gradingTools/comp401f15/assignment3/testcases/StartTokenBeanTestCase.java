package gradingTools.comp401f15.assignment3.testcases;

import java.util.List;
import java.util.Map;

import util.misc.Common;
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


public class StartTokenBeanTestCase extends AbstractTokenBeanTestCase {
	public  String classIdentifier() { return "Start";}
	protected String[] beanDescriptions() { return new String[] {null, "Start", ".*Start.*", ".*Start.*"};};
//	protected double missingClassPenalty() {return 1.0;};
//	protected double missingExpectedConstructorPenalty() {return 0.2;};
//	protected double missingNullConstructorPenalty() {return 0.0;}
//	protected double missingPropertyPenalty() {return 0.2;};
//	protected double getsNotSetsPenalty() {return 0.2;}
//	protected double incorrectDependentsPenalty(){return 0.3;}
	
	protected String input(){ return "{";};
	protected Object value() {return null;};

    public StartTokenBeanTestCase() {
        super("Start Token Bean Case");
    }

   
}

