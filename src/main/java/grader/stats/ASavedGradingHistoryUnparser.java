package grader.stats;

import java.util.List;

import grader.interaction_logger.AnInteractionLogReader;
import grader.interaction_logger.AnInteractionLogWriter;
import grader.interaction_logger.InteractionLogReader;
import grader.interaction_logger.InteractionLogWriter;
import grader.trace.settings.AutomaticNavigationEnded;
import grader.trace.settings.AutomaticNavigationStarted;
import grader.trace.settings.GraderSettingsInfo;
import grader.trace.settings.ManualNavigationEnded;
import grader.trace.settings.ManualNavigationStarted;
import grader.trace.stats.SavedAllStudentsProblemGradingHistoryCreated;
import grader.trace.stats.SavedAllStudentsProblemGradingHistoryFilled;
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
			SavedStudentProblemGradingHistory savedStudentProblemGradingHistory = anAllStudentsHistory.getOnyenToStudentHistory().get(student);
			stringBuilder.append(unparseStudentProblemGradingHistory(savedStudentProblemGradingHistory) + "\n");
		}
		
		return stringBuilder.toString();		
	}
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedGradingHistoryUnparser#unparseStudentProblemGradingHistory(grader.stats.SavedStudentProblemGradingHistory)
	 */
	@Override
	public String unparseStudentProblemGradingHistory(SavedStudentProblemGradingHistory aSingleStudentHistory) {
		StringBuilder stringBuilder = new StringBuilder(EXPECTED_UNPARSE_SINGLE_STUDENTS_SIZE);
		stringBuilder.append (aSingleStudentHistory.getName() + "(" + aSingleStudentHistory.getOnyen() + ")" + "\n");
		stringBuilder.append ("Auto Visit Duration:" + toHourMinSecString(aSingleStudentHistory.getAutoVisitTime()) + "\n");
		stringBuilder.append ("Manual Visit Duration:" + toHourMinSecString(aSingleStudentHistory.getManualVisitTime()) + "\n");

		return stringBuilder.toString();		
	}
	
	public static String toHourMinSecString(long timeInMilliSeconds) {
		long timeInSeconds = timeInMilliSeconds/1000;
		long timeInMinutes = timeInSeconds/60;
		long timeInHours = timeInMinutes/60;
		long minuteRemainder = timeInMinutes%60;
		long secondsRemainder = timeInSeconds%(60*60);
		return timeInHours + ":" + timeInMinutes + ":" + secondsRemainder;
	}
	
	public static void main (String[] args) {
		System.out.println(
				
				"Hell,World\nGoodye,World".replaceAll(",", "COMMA"));
		SavedGradingHistoryParser parser = SavedGradingHistoryParserSelector.getSavedGradingHistoryParser();
		SavedAllStudentsProblemGradingHistory history = parser.parseAllStudentsProblemGradingHistory("log/AssignmentsData/interactionLogs/Dewan_interactionLog_Comp110_Assignment3.csv");
		SavedGradingHistoryUnparser unparser = SavedGradingHistoryUnparserSelector.getSavedGradingHistoryUnparser();
		String printString = unparser.unparseAllStudentsProblemGradingHistory(history);
		System.out.println(printString);
		

	}
	
	
	
	
	
	

}
