package grader.navigation.filter;

import grader.letter_grade.AScoreToCoarseLetterGradeMapper;
import grader.letter_grade.CoarseLetterGrade;
import grader.letter_grade.ScoreToCoarseLetterGradeMapper;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public class ALetterGradeBasedFilter implements NavigationFilter<CoarseLetterGrade>{
	public static final String NAME = "Letter Grade";
	 CoarseLetterGrade parameter = CoarseLetterGrade.C;
	 ScoreToCoarseLetterGradeMapper gradeComputer = new AScoreToCoarseLetterGradeMapper(); // factory needed!!
	

	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		CoarseLetterGrade grade = gradeComputer.toCoarseLetterGrade(aProjectState.getScore());
		return grade == parameter;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public CoarseLetterGrade getParameter() {
		return parameter;
	}

}
