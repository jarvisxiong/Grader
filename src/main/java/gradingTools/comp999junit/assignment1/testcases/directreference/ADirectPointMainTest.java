package gradingTools.comp999junit.assignment1.testcases.directreference;

import org.junit.Test;

import grader.util.ExecutionUtil;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointMainTest;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointTest;
import gradingTools.testables.comp999junit.assignment1.wrongangle.Main;


public class ADirectPointMainTest extends AnAbstractPointMainTest {

	@Override
	protected String runMain(String anInput, String[] anArgs) {
		ExecutionUtil.redirectInputOutput(anInput);
		Main.main(anArgs);
		String anOutput = ExecutionUtil.restoreOutputAndGetRedirectedOutput();
		return anOutput;		
	}
	@Test
	public void test() {
		testMain(10, 10, 14.142, Math.PI); // 45 degree angle
	}
	

	

}
