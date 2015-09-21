package gradingTools.comp110f15.assignment1.testcases;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.sharedTestCase.CodeInspectorTestCase;

import com.github.antlrjavaparser.api.expr.VariableDeclarationExpr;

public class VarsA1 extends CodeInspectorTestCase {

	boolean uncScore = false; //3 points
	boolean dukeScore = false; //3 points
	boolean difference=false; //4 points
	int numright=0;
	
	public VarsA1() {
		super("Has correct variable test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resetVariablesForEachProject() {
		uncScore = false;
		dukeScore = false;
		difference=false;
		numright=0;
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
		if(line.contains("uncScore")){
			uncScore=true;
			numright++;
			
		}
		if(line.contains("dukeScore")){
			dukeScore=true;
			numright++;
		}
		if(line.contains("difference")){
			difference=true;
			numright++;
		}
		super.inspectVariableDeclarationExpr(expr);
	}

	@Override
	public TestCaseResult codeInspectionResult() {
		if (uncScore&&dukeScore&&difference) {
			return pass();
		} if(!uncScore&&!dukeScore&&!difference){
			return fail("You have not declared any of the specified variables");
		}
		return partialPass((0.3*numright)+0.1,"Did not correctly declare one or more of the specified variables");
	}

}