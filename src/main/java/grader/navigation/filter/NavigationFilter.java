package grader.navigation.filter;

import util.models.PropertyListenerRegisterer;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public interface NavigationFilter<ParameterType> extends BasicNavigationFilter<ParameterType> , PropertyListenerRegisterer{
	String getName();
//	boolean includeProject (ProjectStepper aProjectState, SakaiProjectDatabase aDatabase);
	ParameterType getParameter();
	public void setParameter(ParameterType newVal);
	Object fromString(String newVal);

}
