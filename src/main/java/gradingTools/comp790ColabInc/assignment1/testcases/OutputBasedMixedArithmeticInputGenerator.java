package gradingTools.comp790ColabInc.assignment1.testcases;

import framework.execution.OutputBasedInputGenerator;

public interface OutputBasedMixedArithmeticInputGenerator extends OutputBasedInputGenerator{
	 boolean foundIntPrompt();
	 boolean foundDoublePrompt();
	boolean foundOutput();

}
