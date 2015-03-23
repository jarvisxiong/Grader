package grader.trace.settings;

import java.util.Date;

import grader.navigation.filter.GradingStatus;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class GradingStatusUserChange extends GraderSettingsInfo {
	
	GradingStatus gradingStatus;
	
	
	public GradingStatusUserChange(String aMessage, GradingStatus aGradingStatus, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		gradingStatus = aGradingStatus;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public GradingStatus getGradingStatus() {
		return gradingStatus;
	}
	public void setGradingStatus(GradingStatus gradingStatus) {
		this.gradingStatus = gradingStatus;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + gradingStatus;
	}
	
	
	public static GradingStatusUserChange newCase(GradingStatus aGradingStatus, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "GradingStatus Changed:" + aGradingStatus;
		GradingStatusUserChange retVal = new GradingStatusUserChange(aMessage, aGradingStatus,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
