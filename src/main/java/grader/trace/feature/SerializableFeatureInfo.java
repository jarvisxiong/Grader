package grader.trace.feature;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;

public class SerializableFeatureInfo extends SerializableStepperInfo {
GradingFeature feature;




public SerializableFeatureInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			GradingFeature aFeature,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		feature = aFeature;
		// TODO Auto-generated constructor stub
	}

public GradingFeature getGradingFeature() {
	return feature;
}



public void setGradingFeature(GradingFeature aFeature) {
	this.feature = aFeature;
}

public static final int COLUMNS_USED = SerializableStepperInfo.COLUMNS_USED + 1; 


@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + feature.getFeatureName();
}

public static String featureNameFromCSVRow(String[] aRow) {
	return aRow[COLUMNS_USED-1];
}

	
//	public static SerializableFeatureAutoNotesInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aNotes,
//			Object aFinder) {
//		String aMessage = "Overview Notes Autoly Changed to:" + aNotes;
//		SerializableFeatureAutoNotesInfo retVal = new SerializableFeatureAutoNotesInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
