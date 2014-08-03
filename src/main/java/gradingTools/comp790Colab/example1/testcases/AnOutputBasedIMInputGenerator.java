package gradingTools.comp790Colab.example1.testcases;

import util.pipe.AnAbstractOutputBasedInputGenerator;

public class AnOutputBasedIMInputGenerator extends AnAbstractOutputBasedInputGenerator implements OutputBasedIMInputGenerator{
	protected Integer intInput;
	protected Double doubleInput;
//	protected StringBuilder output;
//	protected boolean foundIntPrompt;
//	protected boolean foundDoublePrompt;
//	protected boolean foundOutput;
//	public AnOutputBasedIMInputGenerator(Integer anIntInput, Double aDoubleInput) {
//		intInput = anIntInput;
//		doubleInput = aDoubleInput;
//		
//		
//	}
	
	protected boolean terminationConditionMet() {
		return false;
	}
	protected void maybeTerminate(String aProcessName) {
		setTerminatedSuccessfully(null, terminationConditionMet());
		if (isTerminatedSuccessfully(null)) {
			notifyTermination(aProcessName);
		}
	}
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (isTerminatedSuccessfully(null))
			return; //ignore additional input
		System.out.println("Got output" + anOutputLine );
		
		maybeTerminate(aProcessName);
		

		
	}
//	@Override
//	public boolean foundIntPrompt() {
//		return foundIntPrompt;
//	}
//	@Override
//	public boolean foundDoublePrompt() {
//		return foundDoublePrompt;
//	}
//	@Override
//	public boolean foundOutput() {
//		return foundOutput;
//	}
	

}
