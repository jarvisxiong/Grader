package grader.trace;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class NavigationInitiated extends TraceableInfo {
	GraderSettingsModel gradingSettingsModel; 	
	public NavigationInitiated(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aFinder);
		 gradingSettingsModel = aGradingSettingsModel;
	}
	public GraderSettingsModel getGradingSettingsModel() {
		return gradingSettingsModel;
	}
	public static NavigationInitiated newCaseObject(GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Navigation Initiated";
		NavigationInitiated retVal = new NavigationInitiated(aMessage, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
