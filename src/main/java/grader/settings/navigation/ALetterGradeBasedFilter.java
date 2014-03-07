package grader.settings.navigation;

import grader.letter_grade.CoarseLetterGrade;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public class ALetterGradeBasedFilter implements NavigationFilter{
	public static final String NAME = "Letter Grade";
	public static final CoarseLetterGrade parameters = CoarseLetterGrade.C;

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
	public Object getParameters() {
		return parameters;
	}

}
