package grader.interaction_logger;

import java.beans.PropertyChangeListener;
import java.util.Set;

import util.models.PropertyListenerRegisterer;
import util.trace.TraceableListener;

public interface InteractionLogWriter extends TraceableListener, PropertyListenerRegisterer {

	Set<Class> getDoNotLogEventsSet();

	void setDoNotLogEventsSet(Set<Class> doNotLogEventsSet);

	String getInteractionLogFolder();

	String createModuleProblemInteractionLogName();
	
	void addLogListener(InteractionLogListener aListener);

}
