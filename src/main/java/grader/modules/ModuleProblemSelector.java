package grader.modules;

import util.models.DynamicEnum;

public interface ModuleProblemSelector {
	public DynamicEnum<String> getModule();
	public void setModule(DynamicEnum<String> module) ;
	public DynamicEnum<String> getProblem() ;
	public void setProblem(DynamicEnum<String> problem) ;

}
