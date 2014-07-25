package gradingTools.comp110_2.assignment1;

import framework.execution.AnAbstractOutputBasedInputGenerator;

public class AnOutputBasedMixedArithmeticInputGenerator extends AnAbstractOutputBasedInputGenerator implements OutputBasedMixedArithmeticInputGenerator{
	protected Integer intInput;
	protected Double doubleInput;
//	protected StringBuilder output;
	protected boolean foundIntPrompt;
	protected boolean foundDoublePrompt;
	public AnOutputBasedMixedArithmeticInputGenerator(Integer anIntInput, Double aDoubleInput) {
		intInput = anIntInput;
		doubleInput = aDoubleInput;
		
		
	}
	
	protected boolean terminationConditionMet() {
		return (intInput == null || foundIntPrompt()) &&
				(doubleInput == null) || foundDoublePrompt();
	}
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
//		if (Prompot)
//		output.append(anOutputLine);
		
	}
	@Override
	public boolean foundIntPrompt() {
		return foundIntPrompt;
	}
	@Override
	public boolean foundDoublePrompt() {
		return foundDoublePrompt;
	}
	

}
