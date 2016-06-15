package gradingTools.comp790ColabInc.assignment1.testcases;

import gradingTools.comp110Inc.assignment1.testcases.AnOutputBasedMixedArithmeticInputGenerator;

import java.util.HashMap;
import java.util.Map;

public class ACollaborativeOutputBasedMixedArithmeticInputGenerator 
	extends AnOutputBasedMixedArithmeticInputGenerator
	implements CollaborativeOutputBasedMixedArithmeticInputGenerator{
	protected Map<String, Boolean> processToFoundIntPrompt = new HashMap();
	protected Map<String, Boolean> processToFoundDoublePrompt = new HashMap();
	protected Map<String, Boolean> processToFoundOutput = new HashMap();
	public ACollaborativeOutputBasedMixedArithmeticInputGenerator(Integer anIntInput, Double aDoubleInput) {
		super(anIntInput, aDoubleInput);
		
		
	}
	@Override
	protected boolean terminationConditionMet(String aProcessName) {
		if (aProcessName == null) aProcessName ="";
		boolean retVal = ((intInput == null || foundIntPrompt(aProcessName))) ||
				((doubleInput == null) || foundDoublePrompt(aProcessName));
		if (retVal)
			System.out.println("Termination condition met:" + aProcessName);
		return retVal;
	}
	
	protected void setFoundIntPrompt(String aProcessName, boolean newVal) {
		processToFoundIntPrompt.put(aProcessName, newVal);
		System.out.println("found int prompt:" + aProcessName);
	}
	protected void setFoundDoublePrompt(String aProcessName, boolean newVal) {
		processToFoundDoublePrompt.put(aProcessName, newVal);
		System.out.println("found double prompt:" + aProcessName);

	}
	protected void setFoundOutput(String aProcessName, boolean newVal) {
		processToFoundOutput.put(aProcessName, newVal);
		System.out.println("found output:" + newVal + "process name" + aProcessName);

	}
	@Override
	public boolean foundIntPrompt(String aProcessName) {
		return getValue(processToFoundIntPrompt, aProcessName);
//		return foundIntPrompt;
	}
	@Override
	public boolean foundDoublePrompt(String aProcessName) {
		return getValue(processToFoundDoublePrompt, aProcessName);
	}
	@Override
	public boolean foundOutput(String aProcessName) {
		return getValue(processToFoundOutput, aProcessName);
	}

}
