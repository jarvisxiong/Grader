package grader.trace.stepper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grader.assignment.GradingFeature;
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

public static final int MIN_COLUMNS_USED = SerializableStepperInfo.COLUMNS_USED + 6; 

public static Map<String, String> getFeatureNotes(OverviewProjectStepper aProjectStepper) {
	Map<String, String> retVal = new HashMap<>();
	
	List<GradingFeature> gradingFeatures = aProjectStepper.getGradingFeatures() ;
	for (GradingFeature aGradingFeature: gradingFeatures) {
		if (aGradingFeature.getManualNotes().isEmpty()) continue;
		retVal.put(aGradingFeature.getFeatureName(), aGradingFeature.getManualNotes());
		
	}
	return retVal;
}



public static String toCSVRow(Map<String, String> aFeatureNotes) {
	StringBuilder stringBuilder = new StringBuilder(1024);
	stringBuilder.append( "," + aFeatureNotes.size());
	Set<String> features = aFeatureNotes.keySet();
	for (String feature:features) {
		stringBuilder.append("," + feature + "," + normalize( aFeatureNotes.get(feature)) );
	}
	return stringBuilder.toString();
	
}

@Override
public String toCSVRow() {
	Map<String, String> featureNotes = getFeatureNotes(overviewProjectStepper);
	String featureColumns = toCSVRow(featureNotes);
	String retVal = super.toCSVRow() 
			+ "," +  ASakaiCSVFeatureGradeManager.getTotalGrade(overviewProjectStepper.getScore(), overviewProjectStepper.getMultiplier(), overviewProjectStepper.getSourcePoints())
			+ "," + overviewProjectStepper.getScore()
			+ "," + overviewProjectStepper.getSourcePoints()
			+ "," + overviewProjectStepper.getMultiplier() 
			+ "," + normalize(overviewProjectStepper.getOverallNotes())
			+ "," + normalize(overviewProjectStepper.getTASourceCodeComments())
			+ featureColumns
			; 
	
	return retVal;

//			overviewProjectStepper.getScore();
}

public static Map<String, String> featureNotes(String[] aRow) {
	Map<String, String> retVal = new HashMap();
	int size = sizeFeatureNotesFromCSVRow(aRow);
	for (int i =0 ; i < size -1; i++) {
		String featureName = aRow[MIN_COLUMNS_USED + i*2];
		String featureNotes = unNormalize(aRow[MIN_COLUMNS_USED + i*2 + 1]);
		retVal.put(featureName, featureNotes);
	}
	return retVal;
}

public static double totalScoreFromCSVRow(String[] aRow) {
	return Double.parseDouble(aRow[SerializableStepperInfo.COLUMNS_USED]);
}
public static double featuresScoreFromCSVRow(String[] aRow) {
	return Double.parseDouble(aRow[SerializableStepperInfo.COLUMNS_USED + 1]);
}
public static double sourcePointsFromCSVRow(String[] aRow) {
	return Double.parseDouble(aRow[SerializableStepperInfo.COLUMNS_USED + 2]);
}
public static double multiplierFromCSVRow(String[] aRow) {
	return Double.parseDouble(aRow[SerializableStepperInfo.COLUMNS_USED + 3]);
}

public static String overallNotesFromCSVRow(String[] aRow) {
	return unNormalize(aRow[SerializableStepperInfo.COLUMNS_USED + 4]);
}

public static int sizeFeatureNotesFromCSVRow(String[] aRow) {
	return Integer.parseInt(aRow[SerializableStepperInfo.COLUMNS_USED + 5]);
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
