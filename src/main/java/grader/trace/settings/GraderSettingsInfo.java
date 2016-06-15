package grader.trace.settings;

import grader.settings.GraderSettingsModel;
import grader.trace.SerializableGraderInfo;

public class GraderSettingsInfo extends SerializableGraderInfo {
	GraderSettingsModel gradingSettingsModel; 

	
	public GraderSettingsInfo(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aFinder);
		 gradingSettingsModel = aGradingSettingsModel;
	}
	
	public GraderSettingsModel getGradingSettingsModel() {
		return gradingSettingsModel;
	}
	
	
	

}
