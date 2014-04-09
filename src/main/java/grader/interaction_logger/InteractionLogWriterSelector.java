package grader.interaction_logger;


public class InteractionLogWriterSelector {
	static InteractionLogWriter graderTracer = new AnInteractionLogWriter();

	public static InteractionLogWriter getInteractionLogWriter() {
		return graderTracer;
	}

	public static void setInteractionlogWriter(InteractionLogWriter graderTracer) {
		InteractionLogWriterSelector.graderTracer = graderTracer;
	}

}
