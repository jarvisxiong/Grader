package grader.trace.settings;

import grader.settings.GraderSettingsModel;

public class GraderSettingsDisplayed extends GraderSettingsInfo {

	
	public GraderSettingsDisplayed(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	
	
	public static GraderSettingsDisplayed newCase(GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Grading Settings Displayed";
		GraderSettingsDisplayed retVal = new GraderSettingsDisplayed(aMessage, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}
//	public GraderSettingsModel getGradingSettingsModel() {
//		return gradingSettingsModel;
//	}
	

}
