package grader.interaction_logger;

public interface SavedGradingHistoryUnparser {

	public abstract String unparseAllStudentsProblemGradingHistory(
			SavedAllStudentsProblemGradingHistory anAllStudentsHistory);

	public abstract String unparseStudentProblemGradingHistory(
			SavedStudentProblemGradingHistory aSingleStudentHistory);

}