package grader.settings.navigation;

import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public class AGradingStatusFilter implements NavigationFilter{
	public static final String NAME = "Grading Status";
	public static final GradingStatus parameters = GradingStatus.ALL;

	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		return true;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public GradingStatus getParameters() {
		return parameters;
	}

}
