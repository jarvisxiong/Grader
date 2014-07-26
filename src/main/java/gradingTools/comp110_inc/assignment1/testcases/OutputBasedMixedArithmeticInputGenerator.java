package gradingTools.comp110_inc.assignment1.testcases;

import framework.execution.OutputBasedInputGenerator;

public interface OutputBasedMixedArithmeticInputGenerator extends OutputBasedInputGenerator{
	 boolean foundIntPrompt();
	 boolean foundDoublePrompt();
	boolean foundOutput();

}
