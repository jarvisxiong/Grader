package grader.navigation.filter;

import grader.assignment.GradingFeatureList;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public class AGradingStatusFilter implements NavigationFilter<GradingStatus>{
	public static final String NAME = "Grading Status";
	GradingStatus parameter = GradingStatus.NOT_FULLY_GRADED;

	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		GradingStatus actualStatus = calculateGradingStatus(aDatabase);
		return compatible(parameter, actualStatus);
	}
	
	public static boolean compatible (GradingStatus parameter, GradingStatus actualStatus ) {
		if (parameter == GradingStatus.ALL) return true;
		return parameter == actualStatus;		
	}
	
	public static GradingStatus calculateGradingStatus (SakaiProjectDatabase aDatabase) {
		GradingFeatureList gradingFeatures = aDatabase.getGradingFeatures();
		if (gradingFeatures.isAllGraded())
			return GradingStatus.FULLY_GRADED;
		if (!gradingFeatures.isAllAutoGraded())
			return GradingStatus.NOT_FULLY_AUTO_GRADED;
		return GradingStatus.NOT_FULLY_GRADED;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public GradingStatus getParameter() {
		return parameter;
	}

}
