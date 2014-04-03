package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class UserStartOnyenChange extends GraderSettingsInfo {
	
	String startOnyen;
	
	
	public UserStartOnyenChange(String aMessage, String aStartOnyen, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		startOnyen = aStartOnyen;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public String getStartOnyen() {
		return startOnyen;
	}
	public void setStartOnyen(String startOnyen) {
		this.startOnyen = startOnyen;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + startOnyen;
	}
	
	
	public static UserStartOnyenChange newCase(String aStartOnyen, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "StartOnyen Changed:" + aStartOnyen;
		UserStartOnyenChange retVal = new UserStartOnyenChange(aMessage, aStartOnyen, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
