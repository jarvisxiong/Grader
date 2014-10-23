package gradingTools.comp110f14lab.lab5.testcases;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

import com.github.antlrjavaparser.api.expr.VariableDeclarationExpr;

public class HasArray extends CodeInspectorTestCase {

	boolean hasArray = false;
	
	public HasArray() {
		super("Has Array test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resetVariablesForEachProject() {
		hasArray = false;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		hasArray = false;
		return super.test(project, autoGrade);
	}
	
	@Override
	protected void inspectVariableDeclarationExpr(VariableDeclarationExpr expr) {
		// Code to check if it is an array
		expr.getType().toString();  //This gets the String of the name of the type
		super.inspectVariableDeclarationExpr(expr);
	}

	@Override
	public TestCaseResult codeInspectionResult() {
		if (hasArray) {
			return pass();
		} else {
			return fail("No array declared");
		}
	}

}
