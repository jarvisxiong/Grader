package grader.navigation.filter;

import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public interface NavigationFilter<ParameterType> extends BasicNavigationFilter<ParameterType> {
	String getName();
//	boolean includeProject (ProjectStepper aProjectState, SakaiProjectDatabase aDatabase);
	ParameterType getParameter();

}
