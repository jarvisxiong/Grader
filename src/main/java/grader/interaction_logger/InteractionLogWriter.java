package grader.interaction_logger;

import java.beans.PropertyChangeListener;
import java.util.Set;

import util.models.PropertyListenerRegistrar;
import util.trace.TraceableListener;

public interface InteractionLogWriter extends TraceableListener, PropertyListenerRegistrar {

	Set<Class> getDoNotLogEventsSet();

	void setDoNotLogEventsSet(Set<Class> doNotLogEventsSet);

	String getInteractionLogFolder();

	String createModuleProblemInteractionLogName();
	
	void addLogListener(InteractionLogListener aListener);

}
