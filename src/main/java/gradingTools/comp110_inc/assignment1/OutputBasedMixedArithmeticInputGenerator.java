package gradingTools.comp110_inc.assignment1;

import framework.execution.OutputBasedInputGenerator;

public interface OutputBasedMixedArithmeticInputGenerator extends OutputBasedInputGenerator{
	 boolean foundIntPrompt();
	 boolean foundDoublePrompt();
	boolean foundOutput();

}
