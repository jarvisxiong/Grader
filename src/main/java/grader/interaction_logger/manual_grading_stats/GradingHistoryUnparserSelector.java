package grader.interaction_logger.manual_grading_stats;

public class GradingHistoryUnparserSelector {
	static GradingHistoryUnparser savedGradingHistoryUnparser = new AGradingHistoryUnparser();

	public static GradingHistoryUnparser getSavedGradingHistoryUnparser() {
		return savedGradingHistoryUnparser;
	}

	public static void setSavedGradingHistoryUnparser(
			GradingHistoryUnparser savedGradingHistoryUnparser) {
		GradingHistoryUnparserSelector.savedGradingHistoryUnparser = savedGradingHistoryUnparser;
	}

}
