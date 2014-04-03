package grader.trace.settings.navigation;

import java.util.Date;

import grader.navigation.filter.NavigationFilter;
import grader.settings.GraderSettingsModel;
import grader.trace.settings.GraderSettingsInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class UserNavigationFilterChange extends GraderSettingsInfo {
	
	NavigationFilter navigationFilter;
	
	
	public UserNavigationFilterChange(String aMessage, NavigationFilter aNavigationFilter, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		navigationFilter = aNavigationFilter;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public NavigationFilter getNavigationFilter() {
		return navigationFilter;
	}
	public void setNavigationFilter(NavigationFilter navigationFilter) {
		this.navigationFilter = navigationFilter;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + navigationFilter;
	}
	
	
	public static UserNavigationFilterChange newCase(NavigationFilter aNavigationFilter, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "NavigationFilter Changed:" + aNavigationFilter;
		UserNavigationFilterChange retVal = new UserNavigationFilterChange(aMessage, aNavigationFilter,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
