package grader.trace.settings;

import java.util.Date;

import grader.navigation.NavigationKind;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class NavigationKindChange extends GraderSettingsInfo {
	
	NavigationKind navigationKind;
	
	
	public NavigationKindChange(String aMessage, NavigationKind aNavigationKind, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
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
	
	
	public static NavigationKindChange newCase(NavigationKind aNavigationKind, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "NavigationKind Changed:" + aNavigationKind;
		NavigationKindChange retVal = new NavigationKindChange(aMessage, aNavigationKind,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
