package grader.trace;

import java.util.Date;

import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ManualNavigationEnded extends GraderSettingsInfo {

	SakaiProjectDatabase projectDatabase;
	
	public ManualNavigationEnded(String aMessage, GraderSettingsModel aGradingSettingsModel, SakaiProjectDatabase aProjectDatabase, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		projectDatabase = aProjectDatabase;

//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public static ManualNavigationEnded newCase(GraderSettingsModel aGradingSettingsModel, SakaiProjectDatabase aProjectDatabase, Object aFinder) {
		String aMessage = "Manual Navigation Ended";
		ManualNavigationEnded retVal = new ManualNavigationEnded(aMessage, aGradingSettingsModel, aProjectDatabase, aFinder);
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
