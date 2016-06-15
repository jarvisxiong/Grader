package grader.trace.settings;

import grader.settings.GraderSettingsModel;

public class GraderSettingsEnded extends GraderSettingsInfo {

	
	public GraderSettingsEnded(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public static GraderSettingsEnded newCase(GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Grading Settings Ended";
		GraderSettingsEnded retVal = new GraderSettingsEnded(aMessage, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
