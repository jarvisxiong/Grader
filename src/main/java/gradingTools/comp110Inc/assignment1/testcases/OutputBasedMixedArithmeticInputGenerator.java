package gradingTools.comp110Inc.assignment1.testcases;

import util.pipe.OutputBasedInputGenerator;

public interface OutputBasedMixedArithmeticInputGenerator extends OutputBasedInputGenerator{
	 boolean foundIntPrompt();
	 boolean foundDoublePrompt();
	boolean foundOutput();

}
