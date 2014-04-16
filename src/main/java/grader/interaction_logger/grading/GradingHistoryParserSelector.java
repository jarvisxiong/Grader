package grader.interaction_logger.grading;

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
