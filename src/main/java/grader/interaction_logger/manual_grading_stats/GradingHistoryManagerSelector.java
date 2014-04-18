package grader.interaction_logger.manual_grading_stats;

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