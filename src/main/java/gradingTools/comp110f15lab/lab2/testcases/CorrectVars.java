package gradingTools.comp110f15lab.lab2.testcases;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

import com.github.antlrjavaparser.api.expr.VariableDeclarationExpr;

public class CorrectVars extends CodeInspectorTestCase {

	boolean hassum = false;
	boolean hasaverage = false;
	boolean hasarray=false;
	int numright=0;
	public CorrectVars() {
		super("Has correct variable test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resetVariablesForEachProject() {
		hassum = false;
		hasaverage = false;
		hasarray=false;
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
		if(line.contains("sum")){
			hassum=true;
			numright++;
		}
		if(line.contains("average")){
			hasaverage=true;
			numright++;
		}
		line=line.toLowerCase();
		if(line.contains("multiplesofthree")&&line.contains("[]")&&line.contains("int")){
			hasarray=true;
			numright++;
		}
		super.inspectVariableDeclarationExpr(expr);
	}

	@Override
	public TestCaseResult codeInspectionResult() {
		if (numright==3) {
			return pass();
		}
		if(numright==0) return fail("Could not find contain any of the required variables.");
		return partialPass(0.5,"Is missing one or more of the requried variables, TA note that we cannot check vars declared in logic...please eyeball code");
	}

}