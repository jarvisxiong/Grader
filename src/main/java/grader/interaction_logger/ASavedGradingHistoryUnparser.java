package grader.interaction_logger;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grader.trace.interaction_logger.SavedAllStudentsProblemGradingHistoryCreated;
import grader.trace.interaction_logger.SavedAllStudentsProblemGradingHistoryFilled;
import grader.trace.settings.AutomaticNavigationEnded;
import grader.trace.settings.AutomaticNavigationStarted;
import grader.trace.settings.GraderSettingsInfo;
import grader.trace.settings.ManualNavigationEnded;
import grader.trace.settings.ManualNavigationStarted;
import grader.trace.stepper.ProjectStepEnded;
import grader.trace.stepper.ProjectStepStarted;
import grader.trace.stepper.ProjectStepperStarted;

public class ASavedGradingHistoryUnparser implements SavedGradingHistoryUnparser  {
	
	public static final int EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE = 4096;
	public static final int EXPECTED_NUMBER_STUDENTS = 80;

	public static final int EXPECTED_UNPARSE_ALL_STUDENTS_SIZE = EXPECTED_NUMBER_STUDENTS*EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE;


	public ASavedGradingHistoryUnparser() {
		
	
	}
	
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedGradingHistoryUnparser#unparseAllStudentsProblemGradingHistory(grader.stats.SavedAllStudentsProblemGradingHistory)
	 */
	@Override
	public String unparseAllStudentsProblemGradingHistory(SavedAllStudentsProblemGradingHistory anAllStudentsHistory) {
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_ALL_STUDENTS_SIZE);
//		List<SavedStudentProblemGradingHistory> allStudents = anAllStudentsHistory.getStudentsHistory();
		List<String> allStudents = anAllStudentsHistory.getVisitedStudents();

		stringBuilder.append(anAllStudentsHistory.getModuleName() + ":" + anAllStudentsHistory.getProblemName() + "\n");
		for (String student:allStudents) {
			stringBuilder.append("-------------------------------------------------\n");
			SavedStudentProblemGradingHistory savedStudentProblemGradingHistory = anAllStudentsHistory.getOnyenToStudentHistory().get(student);
			stringBuilder.append(unparseStudentProblemGradingHistory(savedStudentProblemGradingHistory));
		

		}
		
		return stringBuilder.toString();		
	}
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedGradingHistoryUnparser#unparseStudentProblemGradingHistory(grader.stats.SavedStudentProblemGradingHistory)
	 */
	@Override
	public String unparseStudentProblemGradingHistory(SavedStudentProblemGradingHistory aSingleStudentHistory) {
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE);
		stringBuilder.append (aSingleStudentHistory.getName() + 
				"(" + aSingleStudentHistory.getOnyen() + ")" +  
				" TOTAL:" + aSingleStudentHistory.getTotalScore());
		
//		
//				" FEATURES:" + aSingleStudentHistory.getFeaturesScore());
		double sourcePoints = aSingleStudentHistory.getSourcePoints();
		double multiplier = aSingleStudentHistory.getMultiplier();
		double featuresScore = aSingleStudentHistory.getFeaturesScore();
		
		if (featuresScore != aSingleStudentHistory.getTotalScore() ) {
			stringBuilder.append(" FEATURES:" + featuresScore);	

		if (sourcePoints != 0)
			stringBuilder.append(" SOURCE:" + sourcePoints);
		if (multiplier != 1)
			stringBuilder.append(" MULTIPLIER:" + multiplier);
		}
		
		long autoVisit = aSingleStudentHistory.getAutoVisitTime();
		if (autoVisit > 0)
			stringBuilder.append(" AUTO:" + toHourMinSecString(autoVisit));
		long manualVisit = aSingleStudentHistory.getManualVisitTime();
		if (manualVisit > 0)
			stringBuilder.append(" MANUAL:" + toHourMinSecString(manualVisit));
		String manualOverallNotes = aSingleStudentHistory.getManualOverallNotes();
		Set<String> tabsVisited = aSingleStudentHistory.getTabsVisited();
		if (tabsVisited.size() != 0) {
			stringBuilder.append(" SECTIONS:" + tabsVisited);

		}
		stringBuilder.append("\n");
		if (!manualOverallNotes.isEmpty()) {
			stringBuilder.append("OVERALL NOTES\n");
			stringBuilder.append(manualOverallNotes + "\n");
		}
		Map<String, String> featureToManualNotes = aSingleStudentHistory.getFeatureToManualNotes();
		
		Map<String, Double> featureToManualScore = aSingleStudentHistory.getFeatureToManualScore();
		Set<String> featuresWithManualNotes = featureToManualNotes.keySet();
		Set<String> featuresWithManualScores = featureToManualScore.keySet();
		Set<String> featuresWithManualIntervention = new HashSet(featuresWithManualNotes);
		featuresWithManualIntervention.addAll(featuresWithManualScores);
		if (featuresWithManualIntervention.size() > 0)
			stringBuilder.append("FEATURES\n");
		for (String feature:featuresWithManualIntervention) {
			stringBuilder.append(feature);
			Double featureScore = featureToManualScore.get(feature);
			String featureNotes = featureToManualNotes.get(feature);
			if ( featureScore != null) {
				stringBuilder.append("(" + featureScore + ")\n");				
			} else
				stringBuilder.append("\n");
			if (featureNotes != null) {
				stringBuilder.append(featureNotes);				
			}
			stringBuilder.append("\n");
		}
//		if (!manualOverallNotes.isEmpty()) {
//			stringBuilder.append("OVERALL NOTES\n");
//			stringBuilder.append(manualOverallNotes);
//		}

			
		return stringBuilder.toString();		
	}
	
	public static String toHourMinSecString(long timeInMilliSeconds) {
		long timeInSeconds = timeInMilliSeconds/1000;
		long timeInMinutes = timeInSeconds/60;
		long timeInHours = timeInMinutes/60;
		long minutesRemainder = timeInMinutes%60;
		long secondsRemainder = timeInSeconds - (timeInHours*36000 + minutesRemainder*60);
		return timeInHours + ":" + minutesRemainder + ":" + secondsRemainder;
	}
	
	public static void main (String[] args) {
//		System.out.println(
//				
//				"Hell,World\nGoodye,World".replaceAll(",", "COMMA"));
		SavedGradingHistoryParser parser = SavedGradingHistoryParserSelector.getSavedGradingHistoryParser();
		SavedAllStudentsProblemGradingHistory history = parser.parseAllStudentsProblemGradingHistory("log/AssignmentsData/interactionLogs/Dewan_interactionLog_Comp110_Assignment3.csv");
		SavedGradingHistoryUnparser unparser = SavedGradingHistoryUnparserSelector.getSavedGradingHistoryUnparser();
		String printString = unparser.unparseAllStudentsProblemGradingHistory(history);
		System.out.println(printString);
		

	}
	
	
	
	
	
	

}
