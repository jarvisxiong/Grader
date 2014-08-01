package gradingTools.comp790ColabInc.assignment1.testcases;

import framework.execution.AnAbstractOutputBasedInputGenerator;

public class AnOutputBasedMixedArithmeticInputGenerator extends AnAbstractOutputBasedInputGenerator implements OutputBasedMixedArithmeticInputGenerator{
	protected Integer intInput;
	protected Double doubleInput;
//	protected StringBuilder output;
	protected boolean foundIntPrompt;
	protected boolean foundDoublePrompt;
	protected boolean foundOutput;
	public AnOutputBasedMixedArithmeticInputGenerator(Integer anIntInput, Double aDoubleInput) {
		intInput = anIntInput;
		doubleInput = aDoubleInput;
		
		
	}
	
	protected boolean terminationConditionMet() {
		return ((intInput == null || foundIntPrompt())) &&
				((doubleInput == null) || foundDoublePrompt());
	}
	protected void maybeTerminate(String aProcessName) {
		setTerminatedSuccessfully(terminationConditionMet());
		if (isTerminatedSuccessfully()) {
			notifyTermination(aProcessName);
		}
	}
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (isTerminatedSuccessfully())
			return; //ignore additional input
		System.out.println("Got output" + anOutputLine );
		foundOutput = true;
		if (IncrementalInputPromptTestCase.hasIntegerPrompt(anOutputLine)) {
			foundIntPrompt = true;
			if (intInput != null)
				notifyNewInput(aProcessName, intInput + "\n");
			
		} else if (IncrementalInputPromptTestCase.hasDoublePrompt(anOutputLine)) {
			foundDoublePrompt = true;
			if (doubleInput != null)
				notifyNewInput(aProcessName, doubleInput + "\n");
			
		}
		maybeTerminate(aProcessName);
		

		
	}
	@Override
	public boolean foundIntPrompt() {
		return foundIntPrompt;
	}
	@Override
	public boolean foundDoublePrompt() {
		return foundDoublePrompt;
	}
	@Override
	public boolean foundOutput() {
		return foundOutput;
	}
	

}
