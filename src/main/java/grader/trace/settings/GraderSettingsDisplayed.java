package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

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
