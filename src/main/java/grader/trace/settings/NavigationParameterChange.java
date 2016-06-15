package grader.trace.settings;

import grader.trace.SerializableGraderInfo;

public class NavigationParameterChange extends SerializableGraderInfo {
	
	Object navigationParameter;
	
	
	public NavigationParameterChange(String aMessage, Object aObject,  Object aFinder) {
		super(aMessage, aFinder);
		navigationParameter = aObject;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public Object getNavigationParameter() {
		return navigationParameter;
	}
	public void setNavigationParameter(Object newVal) {
//		if (navigationParameter == newVal) return;
		this.navigationParameter = newVal;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + navigationParameter;
	}
	
	
	public static NavigationParameterChange newCase(Object aObject,  Object aFinder) {
		String aMessage = "Navigation Parameter Changed:" + aObject;
		NavigationParameterChange retVal = new NavigationParameterChange(aMessage, aObject,  aFinder);
		retVal.announce();		
		return retVal;
	}

}
