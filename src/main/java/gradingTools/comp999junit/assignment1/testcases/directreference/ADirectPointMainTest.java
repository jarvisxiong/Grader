package gradingTools.comp999junit.assignment1.testcases.directreference;

import org.junit.Test;
import org.junit.internal.runners.statements.InvokeMethod;

import util.annotations.MaxValue;
import grader.util.ProjectExecution;
import gradingTools.comp999junit.assignment1.testables.wrongangle.Main;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointMainTest;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointTest;

@MaxValue(10)
public class ADirectPointMainTest extends AnAbstractPointMainTest {

	@Override
	protected String runMain(String[] anArgs, String... anInput) {
		return ProjectExecution.invokeMain(Main.class, anArgs, anInput).out;
				
	}
	@Test
	public void test() {
		testMain(10, 10, 14.142, Math.PI); // 45 degree angle
	}
	

	

}
