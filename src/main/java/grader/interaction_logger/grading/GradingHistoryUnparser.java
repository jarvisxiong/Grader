package grader.interaction_logger.grading;

public interface GradingHistoryUnparser {

	public abstract String unparseAllStudentsProblemGradingHistory(
			AllStudentsProblemHistory anAllStudentsHistory);

	public abstract String unparseStudentProblemGradingHistory(
			StudentProblemGradingHistory aSingleStudentHistory);

}