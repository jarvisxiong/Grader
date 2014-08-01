package gradingTools.comp790ColabInc.assignment1.testcases;

import framework.execution.AnAbstractOutputBasedInputGenerator;
import gradingTools.comp110Inc.assignment1.testcases.AnOutputBasedMixedArithmeticInputGenerator;

public class ACollaborativeOutputBasedMixedArithmeticInputGenerator extends AnOutputBasedMixedArithmeticInputGenerator {

	public ACollaborativeOutputBasedMixedArithmeticInputGenerator(Integer anIntInput, Double aDoubleInput) {
		super(anIntInput, aDoubleInput);
		
		
	}
	@Override
	protected boolean terminationConditionMet(String aProcessName) {
		return ((intInput == null || foundIntPrompt())) ||
				((doubleInput == null) || foundDoublePrompt());
	}
	
	

}
