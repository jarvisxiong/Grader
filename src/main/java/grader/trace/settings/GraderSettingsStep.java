package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class GraderSettingsStep extends GraderSettingsInfo {

	
	public GraderSettingsStep(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
//		 gradingSettingsModel = aGradingSettingsModel;
	}
//	public static GraderSettingsStep newCase(GraderSettingsModel aGradingSettingsModel, Object aFinder) {
//		String aMessage = "Grading Settings Started";
//		GraderSettingsStep retVal = new GraderSettingsStep(aMessage, aGradingSettingsModel, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	@Override
	public String toCSVRow() {
		String moduleName = gradingSettingsModel.getModuleProblemSelector().getModule().getValue();
		String problemName = gradingSettingsModel.getModuleProblemSelector().getProblem().getValue();
		String startingOnyen = gradingSettingsModel.getOnyens().getStartingOnyen();
		String endingOnyen = gradingSettingsModel.getOnyens().getEndingOnyen();
		String navigationKind = gradingSettingsModel.getNavigationSetter().getNavigationKind().toString();
		String navigationFilter = gradingSettingsModel.getNavigationSetter().getNavigationFilterSetter().getNavigationFilterType().getValue().toString();
		String navigationParameter = gradingSettingsModel.getNavigationSetter().getNavigationFilterSetter().getParameter().toString();
		return super.toCSVRow() + "," + 
				moduleName + ","	 +
				problemName + "," +
				startingOnyen + "," +
				endingOnyen + "," + 
				navigationKind + "," +
				navigationFilter + "," +
				navigationParameter;
	}
//	public GraderSettingsModel getGradingSettingsModel() {
//		return gradingSettingsModel;
//	}
	

}
