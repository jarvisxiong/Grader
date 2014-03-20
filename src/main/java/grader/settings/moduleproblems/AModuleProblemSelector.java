package grader.settings.moduleproblems;

import java.util.List;

import bus.uigen.ObjectEditor;
import edu.emory.mathcs.backport.java.util.Arrays;
import util.annotations.Column;
import util.misc.Common;
import util.models.ADynamicEnum;
import util.models.DynamicEnum;

public class AModuleProblemSelector implements ModuleProblemSelector {
	DynamicEnum<String> module;
	DynamicEnum<String> problem;
	public AModuleProblemSelector(List<String> aModules, List<String> aProblems) {
		module = new ADynamicEnum(aModules);
		problem = new ADynamicEnum(aProblems);		
	}
	@Column(0)
	public DynamicEnum<String> getModule() {
		return module;
	}

	public void setModule(DynamicEnum<String> module) {
		this.module = module;
//		problem.addChoice("15");
		problem.setChoices(Common.arrayToArrayList(new String[] {"one", "two"}));
	}
	@Column(1)
	public DynamicEnum<String> getProblem() {
		return problem;
	}

	public void setProblem(DynamicEnum<String> problem) {
		this.problem = problem;
	}
	
	public static void main (String[] args) {
//		System.out.println(Common.intSuffix("Assignment2"));
//		System.out.println(Common.intSuffix("Assignment"));

		List<String> modules = Common.arrayToArrayList(new String[] {"Comp110", "Comp401"});
		List<String> problems = Common.arrayToArrayList(new String[] {"1", "2"});
		AModuleProblemSelector moduleProblem = new AModuleProblemSelector(modules, problems);
		ObjectEditor.edit(moduleProblem);
		

	}
	

}
