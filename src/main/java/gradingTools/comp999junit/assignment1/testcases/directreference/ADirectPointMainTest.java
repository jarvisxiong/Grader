package gradingTools.comp999junit.assignment1.testcases.directreference;

import org.junit.Test;
import org.junit.internal.runners.statements.InvokeMethod;

import util.annotations.MaxValue;
import grader.util.ProjectExecution;
import gradingTools.comp999junit.assignment1.testables.wrongangle.Main;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiAbstractPointMainTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiAbstractPointTest;

@MaxValue(10)
public class ADirectPointMainTest extends MultiAbstractPointMainTest {

	@Override
	protected String runMain(String[] anArgs, String... anInput) {
		return ProjectExecution.invokeMain(Main.class, anArgs, anInput).out;
				
	}
	@Test
	public void test() {
		testMain(10, 10, 14.142, Math.PI); // 45 degree angle
	}
	

	

}
