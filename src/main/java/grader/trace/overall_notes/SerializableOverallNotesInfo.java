package grader.trace.overall_notes;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.feature.SerializableFeatureInfo;
import grader.trace.steppers.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SerializableOverallNotesInfo extends SerializableStepperInfo {
String overallNotes;



public SerializableOverallNotesInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		overallNotes = aNotes;
		// TODO Auto-generated constructor stub
	}

public String getOverallNotes() {
	return overallNotes;
}



public void setOverallNotes(String overallNotes) {
	this.overallNotes = overallNotes;
}

public static final int COLUMNS_USED = SerializableStepperInfo.COLUMNS_USED + 1; 


@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + normalize(overallNotes);
}

public static String overallNotesFromCSVRow(String[] aRow) {
	return  unNormalize(aRow[COLUMNS_USED-1]);
}

	
//	public static SerializableOverallNotesInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aNotes,
//			Object aFinder) {
//		String aMessage = "Overview Notes Manually Changed to:" + aNotes;
//		SerializableOverallNotesInfo retVal = new SerializableOverallNotesInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
