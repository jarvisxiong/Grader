package grader.settings;

import java.util.List;

import bus.uigen.ObjectEditor;
import edu.emory.mathcs.backport.java.util.Arrays;
import util.misc.Common;
import util.models.ADynamicEnum;
import util.models.DynamicEnum;

public class AModuleProblemSelector {
	DynamicEnum<String> module;
	DynamicEnum<String> problem;
	public AModuleProblemSelector(List<String> aModules, List<String> aProblems) {
		module = new ADynamicEnum(aModules);
		problem = new ADynamicEnum(aProblems);		
	}

	public DynamicEnum<String> getModule() {
		return module;
	}

	public void setModule(DynamicEnum<String> module) {
		this.module = module;
//		problem.addChoice("15");
		problem.getChoices().clear();
		problem.addChoice("foo");
		problem.addChoice("bar");
	}

	public DynamicEnum<String> getProblem() {
		return problem;
	}

	public void setProblem(DynamicEnum<String> problem) {
		this.problem = problem;
	}
	
	public static void main (String[] args) {
		List<String> modules = Common.arrayToArrayList(new String[] {"Comp110", "Comp401"});
		List<String> problems = Common.arrayToArrayList(new String[] {"1", "2"});
		AModuleProblemSelector moduleProblem = new AModuleProblemSelector(modules, problems);
		ObjectEditor.edit(moduleProblem);
		

	}
	

}
