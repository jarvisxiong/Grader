package grader.trace.navigation;

import java.util.Date;

import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.settings.GraderSettingsInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class AutomaticNavigationEnded extends GraderSettingsInfo {

	SakaiProjectDatabase projectDatabase;
	
	public AutomaticNavigationEnded(String aMessage, GraderSettingsModel aGradingSettingsModel, SakaiProjectDatabase aProjectDatabase, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		projectDatabase = aProjectDatabase;

//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public static AutomaticNavigationEnded newCase(GraderSettingsModel aGradingSettingsModel, SakaiProjectDatabase aProjectDatabase, Object aFinder) {
		String aMessage = "Automatic Navigation Ended";
		AutomaticNavigationEnded retVal = new AutomaticNavigationEnded(aMessage, aGradingSettingsModel, aProjectDatabase, aFinder);
		retVal.announce();		
		return retVal;
	}
	public SakaiProjectDatabase getProjectDatabase() {
		return projectDatabase;
	}
	public void setProjectDatabase(SakaiProjectDatabase projectDatabase) {
		this.projectDatabase = projectDatabase;
	}

}
