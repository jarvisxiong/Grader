package grader.interaction_logger;

public interface InteractionHistoryUnparser {

	public abstract String unparseAllStudentsProblemGradingHistory(
			AllStudentsProblemHistory anAllStudentsHistory);

	public abstract String unparseStudentProblemGradingHistory(
			StudentProblemGradingHistory aSingleStudentHistory);

}