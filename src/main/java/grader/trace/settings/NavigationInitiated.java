package grader.trace.settings;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class NavigationInitiated extends GraderSettingsInfo {
	GraderSettingsModel gradingSettingsModel; 	
	public NavigationInitiated(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		 gradingSettingsModel = aGradingSettingsModel;
	}
	
	public static NavigationInitiated newCase(GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Navigation Initiated";
		NavigationInitiated retVal = new NavigationInitiated(aMessage, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
