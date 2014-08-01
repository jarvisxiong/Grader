package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ASerializableEvent implements SerializableEvent {
	AWTEvent event;
	String componentId;
	String paramString;

	// transient boolean isLocal = false;
	public ASerializableEvent(AWTEvent theEvent, String theComponentId) {
		event = theEvent;
		componentId = theComponentId;
		if (theEvent != null)
		paramString = theEvent.paramString();
	}
	public AWTEvent getAWTEvent() {
		return event;
	}
	public String paramString() {
		return paramString;
	}
	public void setParamString(String aParamString) {
		paramString = aParamString;
	}
	public String getSource() {
		return componentId;
	}
	public void setSource(String aComponentId) {
		componentId = aComponentId;
	}
	public void setAWTEvent(AWTEvent anEvent) {
		event = anEvent;
	}

}
