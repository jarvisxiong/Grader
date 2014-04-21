package grader.interaction_logger.manual_grading_stats;

public interface GradingHistoryUnparser {

	public abstract String unparseAllStudentsProblemGradingHistory(
			AllStudentsProblemHistory anAllStudentsHistory);

	public abstract String unparseStudentProblemGradingHistory(
			StudentProblemGradingHistory aSingleStudentHistory);

	String getAggregateStatistics(AllStudentsProblemHistory anAllStudentsHistory);

}