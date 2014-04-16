package grader.interaction_logger;

public class InteractionHistoryParserSelector {
	static InteractionLogParser savedGradingHistoryParser = new AnInteractionLogParser();

	public static InteractionLogParser getSavedGradingHistoryParser() {
		return savedGradingHistoryParser;
	}

	public static void setSavedGradingHistoryParser(
			InteractionLogParser savedGradingHistoryParser) {
		InteractionHistoryParserSelector.savedGradingHistoryParser = savedGradingHistoryParser;
	}

}
