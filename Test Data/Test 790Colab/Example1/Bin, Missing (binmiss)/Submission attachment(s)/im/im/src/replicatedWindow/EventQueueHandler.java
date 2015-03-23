package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Dimension;

public interface EventQueueHandler {
	public void newEvent(SerializableEvent event);
	public void newEvent(AWTEvent anEvent);
}
