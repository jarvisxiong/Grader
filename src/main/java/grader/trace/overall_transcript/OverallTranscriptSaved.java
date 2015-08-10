package grader.trace.overall_transcript;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallTranscriptSaved extends OverallTranscriptInfo {
	String TranscriptFileName;



public OverallTranscriptSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		TranscriptFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getTranscriptFileName() {
	return TranscriptFileName;
}



public void setTranscriptFileName(String TranscriptFileName) {
	this.TranscriptFileName = TranscriptFileName;
}

	
	public static OverallTranscriptSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Transcript Saved to File:" + anOverviewFileName + ". Transcript:" + aNotes;
		OverallTranscriptSaved retVal = new OverallTranscriptSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
