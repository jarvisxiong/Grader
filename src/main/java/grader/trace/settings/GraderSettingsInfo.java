package grader.trace.settings;

import grader.settings.GraderSettingsModel;
import grader.trace.SerializableGraderInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

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
