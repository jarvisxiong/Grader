package grader.interaction_logger.manual_grading_stats;

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

public class AGradingHistoryUnparser implements GradingHistoryUnparser  {
	
	public static final int EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE = 4096;
	public static final int EXPECTED_NUMBER_STUDENTS = 80;

	public static final int EXPECTED_UNPARSE_ALL_STUDENTS_SIZE = EXPECTED_NUMBER_STUDENTS*EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE;


	public AGradingHistoryUnparser() {
		
	
	}
	@Override
	public String getAggregateStatistics(AllStudentsProblemHistory anAllStudentsHistory) {
		anAllStudentsHistory.computeAggregateStats();
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_ALL_STUDENTS_SIZE);
		String totalManualTime = toHourMinSecString(anAllStudentsHistory.getElapsedManualTime());
		String totalAutoTime =  toHourMinSecString(anAllStudentsHistory.getElapsedAutoTime());
		String averageManualTime = toHourMinSecString(anAllStudentsHistory.getAverageManualTime());
		String averageAutoTime = toHourMinSecString(anAllStudentsHistory.getAverageAutoTime());
		int totalOverallNotes = anAllStudentsHistory.getTotalManualOverallNotes();
		double averageOverallNotes = anAllStudentsHistory.getAverageManualOverallNotes();
		int totalFeatureNotes = anAllStudentsHistory.getTotalManualFeatureNotes();
		double averageFeatureNotes = anAllStudentsHistory.getAverageManualFeatureNotes();
		
		int totalSourceNotes = anAllStudentsHistory.getTotalSourceNotes();
		double averageSourceNotes = anAllStudentsHistory.getAverageSourceNotes();
		stringBuilder.append("Auto Grading Time:" + " Total: " + totalAutoTime + " Average: " + averageAutoTime + "\n");
		
		stringBuilder.append("Manual Grading Time:" + " Total: " + totalManualTime + " Average: " + averageManualTime + "\n");

		if (totalOverallNotes > 0) {
			stringBuilder.append("Number of Overall Notes:" + " Total: " + totalOverallNotes + " Average: " + averageOverallNotes + "\n");

		}
		if (totalFeatureNotes > 0) {
			stringBuilder.append("Number of Feature Notes:" + " Total: " + totalFeatureNotes + " Average: " + averageFeatureNotes + "\n");

		}
		if (totalSourceNotes > 0) {
			stringBuilder.append("Number of Source Notes:" + " Total: " + totalSourceNotes + " Average: " + averageSourceNotes + "\n");

		}
		return stringBuilder.toString();
	}
	
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedGradingHistoryUnparser#unparseAllStudentsProblemGradingHistory(grader.stats.SavedAllStudentsProblemGradingHistory)
	 */
	@Override
	public String unparseAllStudentsProblemGradingHistory(AllStudentsProblemHistory anAllStudentsHistory) {
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_ALL_STUDENTS_SIZE);
//		List<SavedStudentProblemGradingHistory> allStudents = anAllStudentsHistory.getStudentsHistory();
		List<String> allStudents = anAllStudentsHistory.getVisitedStudents();

		stringBuilder.append(anAllStudentsHistory.getModuleName() + ":" + anAllStudentsHistory.getProblemName() + "\n");
		for (String student:allStudents) {
			stringBuilder.append("-------------------------------------------------\n");
			StudentProblemHistory savedStudentProblemGradingHistory = anAllStudentsHistory.getOnyenToStudentHistory().get(student);
			stringBuilder.append(unparseStudentProblemGradingHistory(savedStudentProblemGradingHistory));
		

		}
		
		return stringBuilder.toString();		
	}
	
	@Override
	public String unparseAllProblemsStudentGradingHistory(AllProblemsStudentHistory anAllProblemsHistory) {
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_ALL_STUDENTS_SIZE);
		String onyen = anAllProblemsHistory.getOnyen();
		String studentName = anAllProblemsHistory.getName();
		List<StudentProblemHistory> problems = anAllProblemsHistory.getProblemHistories();
		stringBuilder.append (studentName + 
				"(" + onyen + ")\n" );
		for (StudentProblemHistory problem: problems) {
			unparseProblemStudentGradingHistory(problem, stringBuilder);
			
		}
		
		
		
		return stringBuilder.toString();		
	}
	
	
	public String unparseProblemStudentGradingHistory(StudentProblemHistory aSingleStudentHistory) {
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE);
		return unparseProblemStudentGradingHistory(aSingleStudentHistory, stringBuilder);
//		stringBuilder.append (aSingleStudentHistory.getModuleName() + ":" + 
//				aSingleStudentHistory.getProblemName());
//		return unparseGradingHistory(aSingleStudentHistory, stringBuilder);

	}
	
	 String unparseProblemStudentGradingHistory(StudentProblemHistory aSingleStudentHistory, StringBuilder stringBuilder) {
//		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE);
		stringBuilder.append (aSingleStudentHistory.getModuleName() + ":" + 
				aSingleStudentHistory.getProblemName());
		return unparseGradingHistory(aSingleStudentHistory, stringBuilder);

	}

	public String unparseStudentProblemGradingHistory(StudentProblemHistory aSingleStudentHistory) {
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE);
		
		stringBuilder.append (aSingleStudentHistory.getName() + 
				"(" + aSingleStudentHistory.getOnyen() + ")" );
		return unparseGradingHistory(aSingleStudentHistory, stringBuilder);
		
	}
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedGradingHistoryUnparser#unparseStudentProblemGradingHistory(grader.stats.SavedStudentProblemGradingHistory)
	 */
	
	 String unparseGradingHistory(StudentProblemHistory aSingleStudentHistory, StringBuilder stringBuilder) {
		 stringBuilder.append (
					" TOTAL:" + aSingleStudentHistory.getTotalScore());
			;
//		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE);
//		stringBuilder.append (aSingleStudentHistory.getName() + 
//				"(" + aSingleStudentHistory.getOnyen() + ")" +  
//				" TOTAL:" + aSingleStudentHistory.getTotalScore());
		
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
		Set<String> tabsVisited = aSingleStudentHistory.getTabsVisited();
		if (tabsVisited.size() != 0) {
			stringBuilder.append(" SECTIONS:" + tabsVisited);

		}
		List<String> graders = aSingleStudentHistory.getGraderNames();
		if (graders.size() == 1) {
			stringBuilder.append(" GRADER:" + graders.get(0));
		} else if (graders.size() > 1) {
			stringBuilder.append(" GRADERS:" + graders);

		}
		stringBuilder.append("\n");

		String manualOverallNotes = aSingleStudentHistory.getManualOverallNotes();
		if (!manualOverallNotes.isEmpty()) {
			
			stringBuilder.append("OVERALL NOTES\n");
			stringBuilder.append(manualOverallNotes + "\n");
		}
		
		String sourceNotes = aSingleStudentHistory.getSourceComments();
		if (!sourceNotes.isEmpty()) {
			stringBuilder.append("\n");
			stringBuilder.append("SOURCE NOTES\n");
			stringBuilder.append(sourceNotes + "\n");
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
			stringBuilder.append(feature + ":");
			Double featureScore = featureToManualScore.get(feature);
			String featureNotes = featureToManualNotes.get(feature);
			if ( featureScore != null) {
				stringBuilder.append("(" + featureScore + ")\n");				
			} 
//			else
//				stringBuilder.append("\n");
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
		long secondsRemainder = timeInSeconds - (timeInHours*3600 + minutesRemainder*60);
		return timeInHours + ":" + minutesRemainder + ":" + secondsRemainder;
	}
	
	public static void main (String[] args) {
//		System.out.println(
//				
//				"Hell,World\nGoodye,World".replaceAll(",", "COMMA"));
		GradingHistoryParser parser = GradingHistoryParserSelector.getSavedGradingHistoryParser();
		AllStudentsProblemHistory history = parser.parseAllStudentsProblemGradingHistory("log/AssignmentsData/interactionLogs/Dewan_interactionLog_Comp110_Assignment3.csv");
		GradingHistoryUnparser unparser = GradingHistoryUnparserSelector.getSavedGradingHistoryUnparser();
		String printString = unparser.unparseAllStudentsProblemGradingHistory(history);
		System.out.println(printString);
		

	}
	
	
	
	
	
	

}
