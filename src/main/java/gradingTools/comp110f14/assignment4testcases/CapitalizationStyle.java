package gradingTools.comp110f14.assignment4testcases;

import framework.grading.testing.TestCaseResult;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

import java.util.ArrayList;
import java.util.List;

public class CapitalizationStyle extends CodeInspectorTestCase {
	
	List<String> incorrectCases;
	
	
	public CapitalizationStyle() {
		super("Correct capitalization of variables, methods, and classes");
	}

	@Override
	public void resetVariablesForEachProject() {
		incorrectCases = new ArrayList<>();
	}
	
	private boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}
	
	private boolean isLowerCase(char c) {
		return c >= 'a' && c <= 'z';
	}

	@Override
	public void inspectClassOrInterfaceName(String name) {
		if (name.length() > 0 && isLowerCase(name.charAt(0))) {
			incorrectCases.add(name);
		}
	}

	@Override
	public void inspectMethodName(String name) {
		if (name.length() > 0 && isUpperCase(name.charAt(0))) {
			incorrectCases.add(name);
		}
	}

	@Override
	public void inspectVariableName(String variable) {
		if (name.length() > 0 && isUpperCase(name.charAt(0))) {
			incorrectCases.add(name);
		}
	}

	@Override
	public TestCaseResult codeInspectionResult() {
		if (incorrectCases.size() > 0) {
			return pass();
		} else {
			String wrongNames = "";
			for (int i=0; i < incorrectCases.size(); i++) {
				if (i>0){
					wrongNames += ", ";
				}
				wrongNames += incorrectCases.get(i);
			}
			return fail("The following names start with the incorrect case: "+wrongNames);
		}
	}
}
