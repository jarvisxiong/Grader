package grader.auto_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.ProjectStepper;

public interface NotesGenerator {
	public String scoreNotes (ProjectStepper aProjectStepper, double score) ;
	public String validationNotes (ProjectStepper aProjectStepper, GradingFeature aGradingFeature);
	
	public String scoreOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal);
	
	public String multiplierOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal) ;

}
