package gradingTools.comp110f14lab.lab4.testcases;

import com.github.antlrjavaparser.api.stmt.ForStmt;

import framework.grading.testing.TestCaseResult;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

public class HasForLoop extends CodeInspectorTestCase {
	
	boolean hasForLoop = false;

	public HasForLoop(String name) {
		super(name);
	}
	
	@Override
	protected void inspectForStatement(ForStmt statement) {
		hasForLoop = true;
		super.inspectForStatement(statement);
	}

	@Override
	public TestCaseResult codeInspectionResult() {
		if (hasForLoop) {
			return pass();
		} else {
			return fail("No for loop found");
		}
	}
}
