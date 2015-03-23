package grader.interaction_logger.manual_grading_stats;

import java.util.List;

public interface AllProblemsStudentHistory {

	public abstract String getOnyen();

	public abstract void setOnyen(String onyen);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract List<StudentProblemHistory> getProblemHistories();

	public abstract void setProblemHistories(
			List<StudentProblemHistory> problemHistories);

	public abstract void addSavedStudentProblemGradingHistory(
			StudentProblemHistory newValue);

}