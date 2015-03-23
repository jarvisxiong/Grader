package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public interface SerializableEvent extends Serializable {
//	public boolean isLocal();

	public AWTEvent getAWTEvent();

	// source translated to the window of receiving window system
	public String getSource();
	
	public void setSource(String aComponentId);
	public void setAWTEvent(AWTEvent anEvent) ;
	public String paramString() ;
	public void setParamString(String aParamString);

//	public boolean isMouseEvent();
//
//	public boolean isKeyEvent();
//
//	public boolean isResizeEvent();
//
//	public boolean isMouseMovedEvent();
//
//	public boolean isMouseEnteredEvent();
//
//	public boolean isMouseExitedEvent();
//
//	public Dimension getSize();
//
//	public Point getLocation();
//
//	public int getX();
//
//	public int getY();
//
//	public long getGenerationTime();
//
//	public boolean isMousePressedEvent();
//
//	public boolean isMouseClickedEvent();
//
//	public boolean isMouseReleasedEvent();
}