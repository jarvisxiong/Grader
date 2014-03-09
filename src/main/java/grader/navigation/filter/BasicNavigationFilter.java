package grader.navigation.filter;

import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public interface BasicNavigationFilter<ParameterType> {	
	boolean includeProject (ProjectStepper aProjectState, SakaiProjectDatabase aDatabase);


}
