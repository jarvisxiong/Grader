package gradingTools.comp999junit.assignment1.testcases.directreference;

import org.junit.Test;
import org.junit.internal.runners.statements.InvokeMethod;

import grader.util.ExecutionUtil;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointMainTest;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointTest;
import gradingTools.testables.comp999junit.assignment1.wrongangle.Main;


public class ADirectPointMainTest extends AnAbstractPointMainTest {

	@Override
	protected String runMain(String[] anArgs, String... anInput) {
		return ExecutionUtil.invokeMain(Main.class, anArgs, anInput);
				
	}
	@Test
	public void test() {
		testMain(10, 10, 14.142, Math.PI); // 45 degree angle
	}
	

	

}
