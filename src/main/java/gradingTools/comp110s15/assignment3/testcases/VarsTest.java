package gradingTools.comp110s15.assignment3.testcases;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

import com.github.antlrjavaparser.api.expr.VariableDeclarationExpr;

public class VarsTest extends CodeInspectorTestCase {

	boolean hasVar = false;
	
	public VarsTest() {
		super("Has variables test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resetVariablesForEachProject() {
		hasVar = false;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		hasVar = false;
		return super.test(project, autoGrade);
	}
	
	@Override
	protected void inspectVariableDeclarationExpr(VariableDeclarationExpr expr) {
		// Code to check if it is there
		String line=expr.getType().toString().toLowerCase();
		if(line.contains("string"))hasVar=true;
		super.inspectVariableDeclarationExpr(expr);
	}

	@Override
	public TestCaseResult codeInspectionResult() {
		if (hasVar) {
			return pass();
		} else {
			return fail("Did not use vars described in assignment prompt");
		}
	}

}
