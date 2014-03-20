package grader.settings.moduleproblems;

import java.util.List;

import bus.uigen.ObjectEditor;
import edu.emory.mathcs.backport.java.util.Arrays;
import util.annotations.Column;
import util.misc.Common;
import util.models.ADynamicEnum;
import util.models.DynamicEnum;

public interface ModuleProblemSelector {
	public DynamicEnum<String> getModule();
	public void setModule(DynamicEnum<String> module) ;
	public DynamicEnum<String> getProblem() ;
	public void setProblem(DynamicEnum<String> problem) ;

}
