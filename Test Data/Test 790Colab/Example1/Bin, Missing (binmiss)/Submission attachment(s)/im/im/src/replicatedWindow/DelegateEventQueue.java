package replicatedWindow;

import java.awt.AWTEvent;

public interface DelegateEventQueue {

	public void addEventQueueHandler(EventQueueHandler listener);

	public void removeEventQueueHandler(EventQueueHandler listener);

	public void clearEventQueuehandlers();

	public void dispatchEvent(SerializableEvent event);
	public void dispatchEvent(AWTEvent event);
//	public Object getWToolkit();



}