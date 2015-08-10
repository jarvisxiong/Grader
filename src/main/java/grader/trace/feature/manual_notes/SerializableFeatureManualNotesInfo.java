package grader.trace.feature.manual_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.SerializableGraderInfo;
import grader.trace.feature.SerializableFeatureInfo;
import grader.trace.steppers.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SerializableFeatureManualNotesInfo extends SerializableFeatureInfo {
String featureManualNotes;



public SerializableFeatureManualNotesInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			GradingFeature aFeature,
			String aNotes, Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureManualNotes = aNotes;
		// TODO Auto-generated constructor stub
	}

public String getFeatureManualNotes() {
	return featureManualNotes;
}



public void setFeatureManualNotes(String featureManualNotes) {
	this.featureManualNotes = featureManualNotes;
}


@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + normalize(featureManualNotes);
}

public static final int COLUMNS_USED = SerializableFeatureInfo.COLUMNS_USED + 1; 


public static String featureManualNotesFromCSVRow(String[] aRow) {
	return unNormalize(aRow[COLUMNS_USED - 1]);
}

	
//	public static SerializableFeatureManualNotesInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aNotes,
//			Object aFinder) {
//		String aMessage = "Overview Notes Manually Changed to:" + aNotes;
//		SerializableFeatureManualNotesInfo retVal = new SerializableFeatureManualNotesInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
