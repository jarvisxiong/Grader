package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.InvocationEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.PaintEvent;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

public class ACommunicatedAWTEventSupport implements CommunicatedAWTEventSupport  {
    Object wToolkit;
    InvocationEvent globalCursorEvent; 
    int[] notCommunicatedEventsArray = {
    		AWTMisc.SEQUENCED_EVENT_ID,
    		InvocationEvent.INVOCATION_DEFAULT,
    		InvocationEvent.INVOCATION_FIRST,
    		InvocationEvent.INVOCATION_LAST,

    		AWTMisc.STOP_DISPATCH_EVENT,
    		PaintEvent.PAINT,
    		// ignoring the rest is danegeorus
//    		MouseEvent.MOUSE_MOVED, // needed for combo box
    		// it seems the uncommented events can be ignored but there is some flakiness
    		FocusEvent.FOCUS_FIRST,
    		FocusEvent.FOCUS_LAST,
    		FocusEvent.FOCUS_GAINED,
    		FocusEvent.FOCUS_LOST,		
    		WindowEvent.WINDOW_ACTIVATED,
    		WindowEvent.WINDOW_CLOSED,
    		WindowEvent.WINDOW_CLOSING,
    		WindowEvent.WINDOW_DEACTIVATED,
    		WindowEvent.WINDOW_FIRST,
    		// these three cause problems with text field and check box
//    		WindowEvent.WINDOW_GAINED_FOCUS,
//    		WindowEvent.WINDOW_LAST,
//    		WindowEvent.WINDOW_LOST_FOCUS,
    		WindowEvent.WINDOW_OPENED,
    		WindowEvent.WINDOW_STATE_CHANGED, // we can iconfiy without it
    		WindowEvent.COMPONENT_FIRST,
    		WindowEvent.COMPONENT_HIDDEN,
    		WindowEvent.COMPONENT_LAST,
    		WindowEvent.COMPONENT_MOVED,
////    		WindowEvent.COMPONENT_RESIZED, // needed for frame resizing
    		WindowEvent.COMPONENT_SHOWN, // generated only for window and layout
    		
    		MouseEvent.MOUSE_ENTERED, // generated when you move the window
    		MouseEvent.MOUSE_EXITED, // generated when you move the window
    		
    };
	Set<Integer> notCommunicatedEventIDs = new HashSet();
	Class[] communicatedEventClassesArray = {
			MouseEvent.class,
			KeyEvent.class,
//			InputEvent.class, 
			ComponentEvent.class,
//			WindowEvent.class
			};
	Set<Class> communicatedEventClasses =  new HashSet();
	//do not think we need this
	Class[] notDispatchedRemoteEventClassesArray = {
			ComponentEvent.class
	};
	Set<Class> notDispatchedRemoteEventClasses = new HashSet();
	boolean onlyModifiedMouseEvents = true;
	boolean sendInvocationEvents = false;
	
	void initSets() {
		for (Integer anId:notCommunicatedEventsArray)
			notCommunicatedEventIDs.add(anId);
		for (Class aClass:communicatedEventClassesArray)
			communicatedEventClasses.add(aClass);
		for (Class aClass:notDispatchedRemoteEventClassesArray)
			notDispatchedRemoteEventClasses.add(aClass);
		
	}
	
//	void initNotCommunicatedEvents() {
//		notCommunicatedEvents.add(AWTMisc.SEQUENCED_EVENT_ID);
//		notCommunicatedEvents.add(InvocationEvent.INVOCATION_DEFAULT);
//		notCommunicatedEvents.add(InvocationEvent.INVOCATION_FIRST);
//		notCommunicatedEvents.add(InvocationEvent.INVOCATION_LAST);
//
//		notCommunicatedEvents.add(AWTMisc.STOP_DISPATCH_EVENT);
//		notCommunicatedEvents.add(PaintEvent.PAINT);
//		// ignoring the rest is danegeorus
////		notCommunicatedEvents.add(MouseEvent.MOUSE_MOVED); // needed for combo box
//		// it seems the uncommented events can be ignored but there is some flakiness
//		notCommunicatedEvents.add(FocusEvent.FOCUS_FIRST);
//		notCommunicatedEvents.add(FocusEvent.FOCUS_LAST);
//		notCommunicatedEvents.add(FocusEvent.FOCUS_GAINED);
//		notCommunicatedEvents.add(FocusEvent.FOCUS_LOST);		
//		notCommunicatedEvents.add(WindowEvent.WINDOW_ACTIVATED);
//		notCommunicatedEvents.add(WindowEvent.WINDOW_CLOSED);
//		notCommunicatedEvents.add(WindowEvent.WINDOW_CLOSING);
//		notCommunicatedEvents.add(WindowEvent.WINDOW_DEACTIVATED);
//		notCommunicatedEvents.add(WindowEvent.WINDOW_FIRST);
//		// these three cause problems with text field and check box
////		notCommunicatedEvents.add(WindowEvent.WINDOW_GAINED_FOCUS);
////		notCommunicatedEvents.add(WindowEvent.WINDOW_LAST);
////		notCommunicatedEvents.add(WindowEvent.WINDOW_LOST_FOCUS);
//		notCommunicatedEvents.add(WindowEvent.WINDOW_OPENED);
//		notCommunicatedEvents.add(WindowEvent.WINDOW_STATE_CHANGED); // we can iconfiy without it
//		notCommunicatedEvents.add(WindowEvent.COMPONENT_FIRST);
//		notCommunicatedEvents.add(WindowEvent.COMPONENT_HIDDEN);
//		notCommunicatedEvents.add(WindowEvent.COMPONENT_LAST);
//		notCommunicatedEvents.add(WindowEvent.COMPONENT_MOVED);
//////		notCommunicatedEvents.add(WindowEvent.COMPONENT_RESIZED); // needed for frame resizing
//		notCommunicatedEvents.add(WindowEvent.COMPONENT_SHOWN); // generated only for window and layout
//		
//		notCommunicatedEvents.add(MouseEvent.MOUSE_ENTERED); // generated when you move the window
//		notCommunicatedEvents.add(MouseEvent.MOUSE_EXITED); // generated when you move the window
//
//		
//
//
//
//
////		notCommunicatedEvents.add(WindowEvent.WINDOW_DEICONIFIED);
//	
//
//
////		notCommunicatedEvents.add(COMPONENT_EVENT_ID);
//		
//
////		addEventQueueHandler(new ALocalEventForwarder());
//		
//	}
	public ACommunicatedAWTEventSupport() {
		

		initSets();
	}
	// instance of obe ofthese events
	public static boolean isInstanceOf (Object anObject, Set<Class> aClasses) {
		return aClasses.contains(anObject.getClass());
//		for (Class aClass:aClasses) {
////			if ((aClass.isAssignableFrom(anObject.getClass()))) {
//			if (aClass == anObject.getClass()) {
//				return true;
//			}
//		}
//		return false;
	}
	
	public static boolean isUnModifiedMouseEvent (AWTEvent anAWTEvent) {
		if (anAWTEvent instanceof  MouseEvent) {
			return ((MouseEvent) anAWTEvent).getModifiers() == 0;
		}
		return false;
	}
	
	public AWTEvent toDispatchedEvent(SerializableEvent aReceivedEvent, Component component) {
		AWTEvent aReceivedAWTEvent = aReceivedEvent.getAWTEvent();
		if (aReceivedAWTEvent == null)
			return null;
		AWTEvent retVal = aReceivedAWTEvent;
		
			 if (aReceivedAWTEvent instanceof MouseEvent) {
					MouseEvent aMouseEvent = (MouseEvent) aReceivedAWTEvent;
					retVal = new MouseEvent(
							component,
							aMouseEvent.getID(), 
							aMouseEvent.getWhen(),
							aMouseEvent.getModifiers(), 
							aMouseEvent.getX(), 
							aMouseEvent.getY(),
							aMouseEvent.getClickCount(), 
							aMouseEvent.isPopupTrigger());
					return retVal;


				} else if (aReceivedAWTEvent instanceof KeyEvent) {
					KeyEvent aKeyEvent = (KeyEvent) aReceivedAWTEvent;
					retVal  = new KeyEvent(
							component, 
							aKeyEvent.getID(), 
							aKeyEvent.getWhen(), 
							aKeyEvent.getModifiers(), 
							aKeyEvent.getKeyCode(), 
							aKeyEvent.getKeyChar(), 
							aKeyEvent.getKeyLocation());
					return retVal;
					
					
				}
		
//		} else {
//			serializableEvent.getAWTEvent().setSource(wToolkit);
//		}
//			System.out.println("Starting dispatching of received event:" + serializableEvent.getAWTEvent());
//			System.out.println("param string:" + serializableEvent.getAWTEvent().paramString());
				else if (AWTMisc.isResizeEvent(aReceivedAWTEvent) && component instanceof Frame) {
				Dimension size = AWTMisc.getSize(aReceivedEvent.paramString());
//				System.out.println("Settint new size of " + component + " to:" + size);
				if (!(component.getSize().equals(size))) {
				component.setSize(size);
				}
				return null;
			} else if (AWTMisc.isWindowIconfiedEvent(aReceivedAWTEvent) && component instanceof Frame) {
				((Frame) component).setExtendedState(Frame.ICONIFIED);
				return null;
			} else if (AWTMisc.isWindowDeIconfiedEvent(aReceivedAWTEvent) && component instanceof Frame) {
				((Frame) component).setExtendedState(Frame.NORMAL);
				return null;
			}
//			try {
//				if (!isInstanceOf(localEvent, notDispatchedRemoteEventClasses)) {
//				System.out.println("Dispatching:" + localEvent);
//			super.dispatchEvent(localEvent);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("Finished dispatching of received event with source:" + serializableEvent.getAWTEvent().getSource() );
//			if (component instanceof EventProcessor) {
//				System.out.println("Processing on source " + component + " event:" + serializableEvent.getAWTEvent());
//				((EventProcessor) component).processEvent(serializableEvent);
//			}
			 return null;
		}

	
	
	// what is sent and locally dispatched not the same.
	public AWTEvent toSentEvent(AWTEvent anEvent) {

		AWTEvent sentEvent = anEvent; 


		Object source = anEvent.getSource();
		Component aComponent = null;
		if (!(source instanceof Component)) {
			wToolkit = source; // we do not really need this
			if (!sendInvocationEvents) {
//				System.out.println("Dispathcing without sending invocation event:" + anEvent);

				return null;
			
			}

		}
		
		if (AWTMisc.isSequenceEvent(anEvent)) {
			sentEvent = AWTMisc.nested(anEvent);
		}
		if (AWTMisc.isGlobalCursorEvent(anEvent)) {
			System.out.println("Dispathcing as null:" + anEvent);

			sentEvent = null;
//			anId = ComponentRegistry.GLOBAL_CURSOR_EVENT_ID;
			globalCursorEvent = (InvocationEvent) anEvent;
		} else if (sentEvent == null ||
				notCommunicatedEventIDs.contains(sentEvent.getID()) ||
				!(isInstanceOf(sentEvent, communicatedEventClasses)) ||
				onlyModifiedMouseEvents && isUnModifiedMouseEvent(sentEvent)
				) {
//			System.out.println("Dispathcing without sending:" + anEvent);
			sentEvent = null;
		}

//		if (anEvent instanceof PaintEvent) {
//			System.out.println("Broadcasting paint event:" + anEvent + " with param string" + anEvent.paramString());
//		}
		return sentEvent;

	}
//	public static Object deepCopy(Object orig) {
//		Object obj = orig;
//		try {
//			// Write the object out to a byte array
//			FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
//			ObjectOutputStream out = new ObjectOutputStream(fbos);
//			out.writeObject(orig);
//			out.flush();
//			out.close();
//
//			// Retrieve an input stream from the byte array and read
//			// a copy of the object back in.
//			ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
//			obj = in.readObject();
//		} catch (InvalidClassException ice) {
//			
//		} catch (NotSerializableException  nse) {
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException cnfe) {
//			cnfe.printStackTrace();
//		}
//		return obj;
//	}

	public Set<Integer> getNotCommunicatedEventIDs() {
		return notCommunicatedEventIDs;
	}

	public void setNotCommunicatedEventIDs(Set<Integer> notCommunicatedEventIDs) {
		this.notCommunicatedEventIDs = notCommunicatedEventIDs;
	}

	public Set<Class> getCommunicatedEventClasses() {
		return communicatedEventClasses;
	}

	public void setCommunicatedEventClasses(Set<Class> communicatedEventClasses) {
		this.communicatedEventClasses = communicatedEventClasses;
	}

	public Set<Class> getNotDispatchedRemoteEventClasses() {
		return notDispatchedRemoteEventClasses;
	}

	public void setNotDispatchedRemoteEventClasses(
			Set<Class> notDispatchedRemoteEventClasses) {
		this.notDispatchedRemoteEventClasses = notDispatchedRemoteEventClasses;
	}
	
}
