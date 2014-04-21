package grader.auto_notes;

import java.util.List;

import grader.assignment.GradingFeature;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;

public class ANotesGenerator implements NotesGenerator{
	
	public ANotesGenerator (SakaiProjectDatabase aDatabase) {
		
	}
	
//	public String scoreNotes (ProjectStepper aProjectStepper, double score) {
//		return "";
//	}	
	public String validationNotes (ProjectStepper aProjectStepper, GradingFeature aGradingFeature) {
//		return " " + aGradingFeature.getFeature() + " auto grading validated.";
		String retVal = "Validated by instructor.";
		if (aGradingFeature.getManualNotes().contains(retVal)) return "";
		return retVal;
	}
	
	public String totalScoreOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal) {
		return  "Total score manually changed from " + oldVal + " to " + newVal + "." ;
	}
	@Override
	public String missingProjectNotes (ProjectStepper aProjectStepper) {
		String submissionFolder = aProjectStepper.getProject().getStudentAssignment().getSubmissionFolder().displayTree();
		String retVal =  "Missing project in submission folder:" + submissionFolder;
		if (aProjectStepper.getOverallNotes().contains(retVal)) return "";
		return retVal;
	}
	
	@Override
	public String uncompiledFilesNotes (ProjectStepper aProjectStepper) {
		List<String> uncompiledFiles = aProjectStepper.getProject().getNonCompiledClasses();
		String retVal =  "Uncompilable files in project:" + uncompiledFiles;
		if (aProjectStepper.getOverallNotes().contains(retVal)) return "";
		return retVal;
	}
	
	public String autoFeatureScoreOverrideNotes (ProjectStepper aProjectStepper, GradingFeature aGradingFeature, double oldVal, double newVal) {
//		return  "Score manually changed from " + oldVal + " to " + newVal + "." ;
		return  "";
	}
	
	public String multiplierOverrideNotes (ProjectStepper aProjectStepper, double oldVal, double newVal) {
		return  "Multiplier manually changed from " + oldVal + " to " + newVal + "." ;
	}
	
	public  String appendNotes (String existingNotes, String newNotes) {
		if (existingNotes == null || existingNotes.isEmpty())
			return newNotes;
		if (newNotes == null || newNotes.isEmpty())
			return existingNotes;
		return existingNotes + " " + newNotes;
		
	}

	@Override
	public String sourcePointsOverrideNotes(ProjectStepper aProjectStepper,
			double oldVal, double newVal) {
		return "Source points manually changed from " + oldVal + " to " + newVal + "." ;
	}
	

}
