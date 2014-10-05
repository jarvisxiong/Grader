package gradingTools.comp110f14lab.lab4.testcases;

import com.github.antlrjavaparser.api.stmt.ForStmt;

import framework.grading.testing.TestCaseResult;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

public class HasWhileLoop extends CodeInspectorTestCase {
	
	boolean hasForLoop = false;

	public HasWhileLoop() {
		super("Has while loop");
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
