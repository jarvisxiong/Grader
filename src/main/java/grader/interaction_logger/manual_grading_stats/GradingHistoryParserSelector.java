package grader.interaction_logger.manual_grading_stats;

public class GradingHistoryParserSelector {
	static GradingHistoryParser savedGradingHistoryParser = new AGradingHistoryParser();

	public static GradingHistoryParser getSavedGradingHistoryParser() {
		return savedGradingHistoryParser;
	}

	public static void setSavedGradingHistoryParser(
			GradingHistoryParser savedGradingHistoryParser) {
		GradingHistoryParserSelector.savedGradingHistoryParser = savedGradingHistoryParser;
	}

}
