package grader.trace.overall_transcript;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class OverallTranscriptLoaded extends OverallTranscriptInfo {
	String transcriptFileName;



public OverallTranscriptLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		transcriptFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getTranscriptFileName() {
	return transcriptFileName;
}



public void setTranscriptFileName(String transcriptFileName) {
	this.transcriptFileName = transcriptFileName;
}

	
	public static OverallTranscriptLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, 
			String anFeatureManualFileName,
			String aNotes,
			Object aFinder) {
		String aMessage =  "Transcript Loaded from File:" + anFeatureManualFileName + ". Transcript:" + aNotes;
		OverallTranscriptLoaded retVal = new OverallTranscriptLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  anFeatureManualFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
