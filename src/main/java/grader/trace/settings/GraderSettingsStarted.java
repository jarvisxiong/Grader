package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class GraderSettingsStarted extends GraderSettingsInfo {

	
	public GraderSettingsStarted(String aMessage, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	
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
	public static GraderSettingsStarted newCase(GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Grading Settings Started";
		GraderSettingsStarted retVal = new GraderSettingsStarted(aMessage, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}
//	public GraderSettingsModel getGradingSettingsModel() {
//		return gradingSettingsModel;
//	}
	

}
