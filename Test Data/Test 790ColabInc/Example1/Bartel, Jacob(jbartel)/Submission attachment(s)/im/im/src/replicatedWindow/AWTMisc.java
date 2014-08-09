package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.InvocationEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.PaintEvent;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

public class AWTMisc {

	public static final int SEQUENCED_EVENT_ID = 1006;
	public static final int COMPONENT_EVENT_ID = 101;
//	public static final int INVOCATION_EVENT_ID = InvocationEvent.INVOCATION_DEFAULT;
	public static final int STOP_DISPATCH_EVENT = 0;
//	public static final int PAINT_EVENT = PaintEvent.PAINT;
//	public static final int MOUSE_MOVED_EVENT = 503;

	static Field isSystemGeneratedField;
	static Field disposedField;
	static Field nestedField;
	static Field runnableField;

	
	
	
	// not useful
	public static boolean disposed(AWTEvent event) {
		try {
			return (Boolean) disposedField(event).get(event);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	// used to serialize component of sequencedevent
	public static AWTEvent nested(AWTEvent event) {
		try {
			return (AWTEvent) nestedField(event).get(event);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	// not a useful method
	public static boolean isSystemGenerated(AWTEvent event) {
		try {
			return (Boolean) isSystemGeneratedField().get(event);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean isGlobalCursorEvent(AWTEvent anEvent) {
		return isInvocationEvent(anEvent) && 
				runnable(anEvent).toString().indexOf("GlobalCursorManager") >= 0;
	}
	
	public static Field isSystemGeneratedField() {
		if (isSystemGeneratedField == null) {
			try {
				isSystemGeneratedField = AWTEvent.class.getDeclaredField("isSystemGenerated");
				isSystemGeneratedField.setAccessible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isSystemGeneratedField;
	}
	public static Field runnableField() {
		if (runnableField == null) {
			try {
				runnableField = InvocationEvent.class.getDeclaredField("runnable");
				runnableField.setAccessible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return runnableField;
	}
	public static Runnable runnable(AWTEvent event) {
		try {
			return (Runnable) runnableField().get(event);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static Field disposedField(AWTEvent event) {
		if (disposedField == null) {
			try {
				disposedField = event.getClass().getDeclaredField("disposed");
				disposedField.setAccessible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return disposedField;
	}
	public static Field nestedField(AWTEvent event) {
		if (nestedField == null) {
			try {
				nestedField = event.getClass().getDeclaredField("nested");
				nestedField.setAccessible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return nestedField;
	}
	
	public static boolean isInvocationEvent(AWTEvent event) {
		return event instanceof InvocationEvent;
	}
	
	public static boolean isPaintEvent(AWTEvent event) {
		return event instanceof PaintEvent;
	}
	
	public static boolean isWindowEvent(AWTEvent event) {
		return event instanceof WindowEvent;
	}
	
	public static boolean isWindowIconfiedEvent(AWTEvent event) {
		return event.getID() == WindowEvent.WINDOW_ICONIFIED; 
	}
	public static boolean isWindowDeIconfiedEvent(AWTEvent event) {
		return event.getID() == WindowEvent.WINDOW_DEICONIFIED; 
	}
	
	public static boolean isComponentShown(AWTEvent anEvent) {
		return anEvent.getID() == WindowEvent.COMPONENT_SHOWN;
	}
	

	public static boolean isResizeEvent(AWTEvent event) {
		if (!(event instanceof ComponentEvent))
			return false;
		ComponentEvent componentEvent = (ComponentEvent) event;
		return componentEvent.getID() == ComponentEvent.COMPONENT_RESIZED;
	}
	
	public static boolean isSequenceEvent(AWTEvent event) {
		return event.getID() == SEQUENCED_EVENT_ID;
	}
	
	public static boolean isMouseEvent(AWTEvent event) {
		return event instanceof MouseEvent;
	}

	public static boolean isKeyEvent(AWTEvent event) {
		return event instanceof KeyEvent;
	}
	
	

	public static boolean isMouseMovedEvent(AWTEvent event) {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_MOVED;
	}
	
	public static boolean isMouseDraggedEvent(AWTEvent event) {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_DRAGGED;
	}

	public static boolean isMousePressedEvent(AWTEvent event) {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_PRESSED;
	}

	public static boolean isMouseClickedEvent(AWTEvent event) {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_CLICKED;
	}

	public static boolean isMouseReleasedEvent(AWTEvent event) {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_RELEASED;
	}

	public static boolean isMouseEnteredEvent(AWTEvent event) {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_ENTERED;
	}

	public static boolean isMouseExitedEvent(AWTEvent event) {
		if (!(event instanceof MouseEvent))
			return false;
		MouseEvent mousevent = (MouseEvent) event;
		return mousevent.getID() == MouseEvent.MOUSE_EXITED;
	}

	public static Point getLocation(AWTEvent event) {
		
			if (!(event instanceof MouseEvent))
				throw new RuntimeException("Event  with ID: " + event.getID()
						+ " is not a mouse event");
			return getLocation(event.paramString());
		
	}

	public static int getX(AWTEvent event) {
		return getLocation(event).x;
	}

	public static int getY(AWTEvent event) {
		return getLocation(event).y;
	}

	

	public static Dimension getSize(String paramString) {
		int secondSpace = paramString.lastIndexOf(' ');
		int firstX = paramString.indexOf('x');
		int firstClosedParen = paramString.indexOf(')');
		try {
			String stringWidth = paramString.substring(secondSpace + 1, firstX);
			int width = Integer.parseInt(stringWidth);
			String stringHeight = paramString.substring(firstX + 1,
					firstClosedParen);
			int height = Integer.parseInt(stringHeight);
			return new Dimension(width, height);
		} catch (Exception e) {
			return new Dimension(0, 0);
		}
	}
	
	public static Dimension getSize(AWTEvent event) {
		return getSize(event.paramString());
	}

	public static Point getLocation(String paramString) {
		int leftPara = paramString.indexOf('(');
		int comma = paramString.indexOf(',', leftPara);
		int rightPara = paramString.indexOf(')');
		try {
			String stringX = paramString.substring(leftPara + 1, comma);
			int x = Integer.parseInt(stringX);
			String stringY = paramString.substring(comma + 1, rightPara);
			int y = Integer.parseInt(stringY);
			return new Point(x, y);
		} catch (Exception e) {
			e.printStackTrace();
			return new Point(0, 0);
		}
	}

}
