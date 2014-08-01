package gradingTools.comp790ColabInc.assignment1.testcases;

import java.util.HashMap;
import java.util.Map;

import framework.execution.AnAbstractOutputBasedInputGenerator;
import gradingTools.comp110Inc.assignment1.testcases.AnOutputBasedMixedArithmeticInputGenerator;

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
		return ((intInput == null || foundIntPrompt())) ||
				((doubleInput == null) || foundDoublePrompt());
	}
	
	protected void setFoundIntPrompt(String aProcessName, boolean newVal) {
		processToFoundIntPrompt.put(aProcessName, newVal);
	}
	protected void setFoundDoublePrompt(String aProcessName, boolean newVal) {
		processToFoundDoublePrompt.put(aProcessName, newVal);
	}
	protected void setFoundOutput(String aProcessName, boolean newVal) {
		processToFoundOutput.put(aProcessName, newVal);
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
