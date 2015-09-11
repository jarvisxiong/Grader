package gradingTools.comp110f15lab.lab1.testcases;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

import com.github.antlrjavaparser.api.expr.VariableDeclarationExpr;

public class Vars extends CodeInspectorTestCase {

	boolean hasnum1 = false;
	boolean hasnum2 = false;
	
	public Vars() {
		super("Has correct variable test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resetVariablesForEachProject() {
		hasnum1 = false;
		hasnum2 = false;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		resetVariablesForEachProject();
		return super.test(project, autoGrade);
	}
	
	@Override
	protected void inspectVariableDeclarationExpr(VariableDeclarationExpr expr) {
		// Code to check if it is there
		String line=expr.toString();
		System.out.println("Hey Max, it said this: "+line);
		if(line.contains("num1"))hasnum1=true;
		if(line.contains("num2"))hasnum2=true;
		super.inspectVariableDeclarationExpr(expr);
	}

	@Override
	public TestCaseResult codeInspectionResult() {
		if (hasnum1&&hasnum2) {
			return pass();
		} else if(!hasnum1&&hasnum2){
			return partialPass(0.5,"You do not have a correctly declared num1 var");
		}
		else if(hasnum1&&!hasnum2){
			return partialPass(0.5,"You do not have a correctly declared num2 var");
		}
		else return fail("neither variables were correctly declared");
	}

}