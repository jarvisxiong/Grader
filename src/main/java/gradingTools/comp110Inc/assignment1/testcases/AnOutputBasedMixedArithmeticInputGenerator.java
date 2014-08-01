package gradingTools.comp110Inc.assignment1.testcases;

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
	
	protected boolean terminationConditionMet(String aProcessName) {
		return ((intInput == null || foundIntPrompt())) &&
				((doubleInput == null) || foundDoublePrompt());
	}
	protected void maybeTerminate(String aProcessName) {
		setTerminatedSuccessfully(aProcessName, terminationConditionMet(aProcessName));
		if (isTerminatedSuccessfully(aProcessName)) {
			notifyTermination(aProcessName);
		}
	}
	protected void setFoundIntPrompt(String aProcessName, boolean newVal) {
		foundIntPrompt = newVal;
	}
	protected void setFoundDoublePrompt(String aProcessName, boolean newVal) {
		foundDoublePrompt = newVal;
	}
	protected void setFoundOutput(String aProcessName, boolean newVal) {
		foundOutput = newVal;
	}
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (isTerminatedSuccessfully(aProcessName))
			return; //ignore additional input
		System.out.println("Got output" + anOutputLine );
		setFoundOutput(aProcessName, true);
		if (IncrementalInputPromptTestCase.hasIntegerPrompt(anOutputLine)) {
//			foundIntPrompt = true;
			setFoundIntPrompt(aProcessName, true);
			if (intInput != null)
				notifyNewInput(aProcessName, intInput + "\n");
			
		} else if (IncrementalInputPromptTestCase.hasDoublePrompt(anOutputLine)) {
			setFoundDoublePrompt(aProcessName, true);
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
