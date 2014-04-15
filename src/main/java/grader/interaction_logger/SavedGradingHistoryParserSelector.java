package grader.interaction_logger;

public class SavedGradingHistoryParserSelector {
	static SavedGradingHistoryParser savedGradingHistoryParser = new ASavedGradingHistoryParser();

	public static SavedGradingHistoryParser getSavedGradingHistoryParser() {
		return savedGradingHistoryParser;
	}

	public static void setSavedGradingHistoryParser(
			SavedGradingHistoryParser savedGradingHistoryParser) {
		SavedGradingHistoryParserSelector.savedGradingHistoryParser = savedGradingHistoryParser;
	}

}
