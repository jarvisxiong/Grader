package gradingTools.comp110Inc.assignment1.testcases;

import util.pipe.InputGenerator;

public interface OutputBasedMixedArithmeticInputGenerator extends InputGenerator{
	 boolean foundIntPrompt();
	 boolean foundDoublePrompt();
	boolean foundOutput();

}
