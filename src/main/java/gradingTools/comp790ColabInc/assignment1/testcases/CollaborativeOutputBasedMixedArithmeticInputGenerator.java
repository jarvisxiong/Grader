package gradingTools.comp790ColabInc.assignment1.testcases;

import gradingTools.comp110Inc.assignment1.testcases.OutputBasedMixedArithmeticInputGenerator;

public interface CollaborativeOutputBasedMixedArithmeticInputGenerator extends OutputBasedMixedArithmeticInputGenerator {

	boolean foundIntPrompt(String aProcessName);

	boolean foundDoublePrompt(String aProcessName);

	boolean foundOutput(String aProcessName);

}
