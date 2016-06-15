package grader.trace.feedback;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class FeedbackSaved extends FeedbackInfo {
	String feedbackFileName;



public FeedbackSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		feedbackFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeedbackFileName() {
	return feedbackFileName;
}



public void setFeedbackFileName(String feedbackFileName) {
	this.feedbackFileName = feedbackFileName;
}

	
	public static FeedbackSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		if (anOverviewFileName.contains("nosub"))
				System.out.println("gpothach");
		String aMessage = "Feedback Saved to File:" + anOverviewFileName + ". Notes:" + aNotes;
		FeedbackSaved retVal = new FeedbackSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
