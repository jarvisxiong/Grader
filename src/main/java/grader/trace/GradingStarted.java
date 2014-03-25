package grader.trace;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class GradingStarted extends TraceableInfo {
	GraderSettingsModel gradingSettingsModel; 

	
	public GradingStarted(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aFinder);
		 gradingSettingsModel = aGradingSettingsModel;
	}
	public static GradingStarted newCaseObject(GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Grading Started";
		GradingStarted retVal = new GradingStarted(aMessage, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}
	public GraderSettingsModel getGradingSettingsModel() {
		return gradingSettingsModel;
	}

}
