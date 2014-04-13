package grader.trace.stepper;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.spreadsheet.csv.ASakaiCSVFeatureGradeManager;
import grader.trace.CSVDeSerializable;
import grader.trace.SerializableGraderInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProjectStepEnded extends SerializableStepperInfo  {
public ProjectStepEnded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		// TODO Auto-generated constructor stub
	}
public static final int COLUMNS_USED = SerializableStepperInfo.COLUMNS_USED + 1; 

@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + 
			ASakaiCSVFeatureGradeManager.getTotalGrade(overviewProjectStepper.getScore(), overviewProjectStepper.getMultiplier(), overviewProjectStepper.getSourcePoints());
//			overviewProjectStepper.getScore();
}

public static double totalScoreFromCSVRow(String[] aRow) {
	return Double.parseDouble(aRow[COLUMNS_USED-1]);
}

	
	public static ProjectStepEnded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Object aFinder) {
		String aMessage = "Step Ended for Student:" + aProjectStepper.getOnyen();
		ProjectStepEnded retVal = new ProjectStepEnded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		retVal.announce();		
		return retVal;
	}



	
	

}
