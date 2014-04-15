package grader.interaction_logger;

import java.util.List;

public interface SavedStudentAllProblemsGradingHistory {

	public abstract String getOnyen();

	public abstract void setOnyen(String onyen);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract List<SavedStudentProblemGradingHistory> getProblemHistories();

	public abstract void setProblemHistories(
			List<SavedStudentProblemGradingHistory> problemHistories);

	public abstract void addSavedStudentProblemGradingHistory(
			SavedStudentProblemGradingHistory newValue);

}