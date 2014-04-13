package grader.stats;

public class SavedGradingHistoryUnparserSelector {
	static SavedGradingHistoryUnparser savedGradingHistoryUnparser = new ASavedGradingHistoryUnparser();

	public static SavedGradingHistoryUnparser getSavedGradingHistoryUnparser() {
		return savedGradingHistoryUnparser;
	}

	public static void setSavedGradingHistoryUnparser(
			SavedGradingHistoryUnparser savedGradingHistoryUnparser) {
		SavedGradingHistoryUnparserSelector.savedGradingHistoryUnparser = savedGradingHistoryUnparser;
	}

}
