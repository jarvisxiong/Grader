package grader.stats;

public interface SavedGradingHistoryUnparser {

	public abstract String unparseAllStudentsProblemGradingHistory(
			SavedAllStudentsProblemGradingHistory anAllStudentsHistory);

	public abstract String unparseStudentProblemGradingHistory(
			SavedStudentProblemGradingHistory aSingleStudentHistory);

}