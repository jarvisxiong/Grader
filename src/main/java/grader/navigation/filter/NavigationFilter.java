package grader.navigation.filter;

import util.models.PropertyListenerRegisterer;

public interface NavigationFilter<ParameterType> extends BasicNavigationFilter<ParameterType> , PropertyListenerRegisterer{
	String getName();
//	boolean includeProject (ProjectStepper aProjectState, SakaiProjectDatabase aDatabase);
	ParameterType getParameter();
	public void setParameter(ParameterType newVal);
	Object fromString(String newVal);

}
