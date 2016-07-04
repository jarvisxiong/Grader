package gradingTools.comp999junit.assignment1.testcases.directreference;

import grader.basics.execution.BasicProjectExecution;
import gradingTools.comp999junit.assignment1.testables.wrongangle.Main;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiAbstractPointMainTest;

import org.junit.Test;

import util.annotations.MaxValue;

@MaxValue(10)
public class ADirectPointMainTest extends MultiAbstractPointMainTest {

	@Override
	protected String runMain(String[] anArgs, String... anInput) {
		return BasicProjectExecution.invokeMain(Main.class, anArgs, anInput).out;
				
	}
	@Test
	public void test() {
		testMain(10, 10, 14.142, Math.PI); // 45 degree angle
	}
	

	

}
