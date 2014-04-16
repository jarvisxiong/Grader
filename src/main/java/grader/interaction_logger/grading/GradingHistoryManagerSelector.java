package grader.interaction_logger.grading;

import grader.interaction_logger.InteractionLogListener;

public class GradingHistoryManagerSelector {
	static GradingHistoryManager gradingHistoryManager = new AGradingHistoryManager();

	public static GradingHistoryManager getGradingHistoryManager() {
		return gradingHistoryManager;
	}

	public static void setGradingHistoryManager(
			GradingHistoryManager gradingHistoryManager) {
		GradingHistoryManagerSelector.gradingHistoryManager = gradingHistoryManager;
	}
	

}