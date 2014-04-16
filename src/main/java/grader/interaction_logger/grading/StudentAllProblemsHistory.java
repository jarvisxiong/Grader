package grader.interaction_logger.grading;

import java.util.List;

public interface StudentAllProblemsHistory {

	public abstract String getOnyen();

	public abstract void setOnyen(String onyen);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract List<StudentProblemGradingHistory> getProblemHistories();

	public abstract void setProblemHistories(
			List<StudentProblemGradingHistory> problemHistories);

	public abstract void addSavedStudentProblemGradingHistory(
			StudentProblemGradingHistory newValue);

}