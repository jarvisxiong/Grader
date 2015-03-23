package grader.interaction_logger;

import java.util.List;

public interface InteractionLogListener {
	void newStep(List<String> aRows);
	void newNavigation(List<String> aRows);
	

}
