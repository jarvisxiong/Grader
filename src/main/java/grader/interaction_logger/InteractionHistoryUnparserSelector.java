package grader.interaction_logger;

public class InteractionHistoryUnparserSelector {
	static InteractionHistoryUnparser savedGradingHistoryUnparser = new AnInteractionHistoryUnparser();

	public static InteractionHistoryUnparser getSavedGradingHistoryUnparser() {
		return savedGradingHistoryUnparser;
	}

	public static void setSavedGradingHistoryUnparser(
			InteractionHistoryUnparser savedGradingHistoryUnparser) {
		InteractionHistoryUnparserSelector.savedGradingHistoryUnparser = savedGradingHistoryUnparser;
	}

}
