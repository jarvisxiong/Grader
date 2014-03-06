package grader.settings.navigation;

import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public interface NavigationFilter {
	String getName();
	boolean includeProject (ProjectStepper aProjectState, SakaiProjectDatabase aDatabase);
	Object getParameters();

}
