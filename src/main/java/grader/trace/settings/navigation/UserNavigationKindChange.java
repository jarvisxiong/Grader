package grader.trace.settings.navigation;

import java.util.Date;

import grader.navigation.NavigationKind;
import grader.settings.GraderSettingsModel;
import grader.trace.settings.GraderSettingsInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class UserNavigationKindChange extends GraderSettingsInfo {
	
	NavigationKind navigationKind;
	
	
	public UserNavigationKindChange(String aMessage, NavigationKind aNavigationKind, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		navigationKind = aNavigationKind;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public NavigationKind getNavigationKind() {
		return navigationKind;
	}
	public void setNavigationKind(NavigationKind navigationKind) {
		this.navigationKind = navigationKind;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + navigationKind;
	}
	
	
	public static UserNavigationKindChange newCase(NavigationKind aNavigationKind, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "NavigationKind Changed:" + aNavigationKind;
		UserNavigationKindChange retVal = new UserNavigationKindChange(aMessage, aNavigationKind,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
