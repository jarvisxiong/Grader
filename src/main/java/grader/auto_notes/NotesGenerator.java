package grader.auto_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.ProjectStepper;

public interface NotesGenerator {
//	public String scoreNotes (ProjectStepper aProjectStepper, double score) ;
	public String autoFeatureScoreOverrideNotes (ProjectStepper aProjectStepper, GradingFeature aGradingFeature, double oldVal, double newVal);

	public String validationNotes (ProjectStepper aProjectStepper, GradingFeature aGradingFeature);
	
	public String totalScoreOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal);
	
	public String multiplierOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal) ;
	
	String appendNotes (String existingNotes, String newNotes);

}
