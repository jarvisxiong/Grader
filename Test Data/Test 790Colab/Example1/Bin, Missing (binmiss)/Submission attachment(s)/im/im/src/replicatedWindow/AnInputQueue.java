package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.InvocationEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnInputQueue extends EventQueue implements DelegateEventQueue {
	static AnInputQueue eventQueue; // serevres as a factory class
	CommunicatedAWTEventSupport communicatedEventSupport = new ACommunicatedAWTEventSupport();
	Object wToolkit;
	InvocationEvent globalCursorEvent;
	List<EventQueueHandler> eventQueueListeners = new ArrayList();
	Set<Integer> notCommunicatedEvents = new HashSet();
	Class[] communicatedEventClasses = { MouseEvent.class, KeyEvent.class,
			// InputEvent.class,
			ComponentEvent.class,
	// WindowEvent.class
	};
	Class[] notDispatchedRemoteEventClasses = { ComponentEvent.class };
	boolean onlyModifiedMouseEvents = true;
	boolean sendInvocationEvents = true;

	public AnInputQueue() {
		if (eventQueue != null)
			throw new RuntimeException(
					"Call ADelegateEventQueue.getDelegateEventQueue() for an instance of this singleton");
		eventQueue = this;
		EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		queue.push(this);
		// do we need this?
		// addEventQueueHandler(new ALocalEventForwarder());

		// notCommunicatedEvents.add(AWTMisc.SEQUENCED_EVENT_ID);
		// notCommunicatedEvents.add(InvocationEvent.INVOCATION_DEFAULT);
		// notCommunicatedEvents.add(InvocationEvent.INVOCATION_FIRST);
		// notCommunicatedEvents.add(InvocationEvent.INVOCATION_LAST);
		//
		// notCommunicatedEvents.add(AWTMisc.STOP_DISPATCH_EVENT);
		// notCommunicatedEvents.add(PaintEvent.PAINT);
		// // ignoring the rest is danegeorus
		// // notCommunicatedEvents.add(MouseEvent.MOUSE_MOVED); // needed for
		// combo box
		// // it seems the uncommented events can be ignored but there is some
		// flakiness
		// notCommunicatedEvents.add(FocusEvent.FOCUS_FIRST);
		// notCommunicatedEvents.add(FocusEvent.FOCUS_LAST);
		// notCommunicatedEvents.add(FocusEvent.FOCUS_GAINED);
		// notCommunicatedEvents.add(FocusEvent.FOCUS_LOST);
		// notCommunicatedEvents.add(WindowEvent.WINDOW_ACTIVATED);
		// notCommunicatedEvents.add(WindowEvent.WINDOW_CLOSED);
		// notCommunicatedEvents.add(WindowEvent.WINDOW_CLOSING);
		// notCommunicatedEvents.add(WindowEvent.WINDOW_DEACTIVATED);
		// notCommunicatedEvents.add(WindowEvent.WINDOW_FIRST);
		// // these three cause problems with text field and check box
		// // notCommunicatedEvents.add(WindowEvent.WINDOW_GAINED_FOCUS);
		// // notCommunicatedEvents.add(WindowEvent.WINDOW_LAST);
		// // notCommunicatedEvents.add(WindowEvent.WINDOW_LOST_FOCUS);
		// notCommunicatedEvents.add(WindowEvent.WINDOW_OPENED);
		// notCommunicatedEvents.add(WindowEvent.WINDOW_STATE_CHANGED); // we
		// can iconfiy without it
		// notCommunicatedEvents.add(WindowEvent.COMPONENT_FIRST);
		// notCommunicatedEvents.add(WindowEvent.COMPONENT_HIDDEN);
		// notCommunicatedEvents.add(WindowEvent.COMPONENT_LAST);
		// notCommunicatedEvents.add(WindowEvent.COMPONENT_MOVED);
		// //// notCommunicatedEvents.add(WindowEvent.COMPONENT_RESIZED); //
		// needed for frame resizing
		// notCommunicatedEvents.add(WindowEvent.COMPONENT_SHOWN); // generated
		// only for window and layout
		//
		// notCommunicatedEvents.add(MouseEvent.MOUSE_ENTERED); // generated
		// when you move the window
		// notCommunicatedEvents.add(MouseEvent.MOUSE_EXITED); // generated when
		// you move the window
		//
		//
		//
		//
		//
		//
		// // notCommunicatedEvents.add(WindowEvent.WINDOW_DEICONIFIED);
		//
		//
		//
		// // notCommunicatedEvents.add(COMPONENT_EVENT_ID);
		//
		//
		// // addEventQueueHandler(new ALocalEventForwarder());
	}

	// // instance of obe ofthese events
	// public static boolean isInstanceOf (Object anObject, Class[] aClasses) {
	// for (Class aClass:aClasses) {
	// // if ((aClass.isAssignableFrom(anObject.getClass()))) {
	// if (aClass == anObject.getClass()) {
	// return true;
	// }
	// }
	// return false;
	// }
	//
	// public static boolean isUnModifiedMouseEvent (AWTEvent anAWTEvent) {
	// if (anAWTEvent instanceof MouseEvent) {
	// return ((MouseEvent) anAWTEvent).getModifiers() == 0;
	// }
	// return false;
	// }

	@Override
	public void addEventQueueHandler(EventQueueHandler listener) {
		if (eventQueueListeners.contains(listener))
			return;
		eventQueueListeners.add(listener);
	}

	public static void addHandler(EventQueueHandler handler) {
		eventQueue.addEventQueueHandler(handler);
	}

	public void removeHandler(EventQueueHandler handler) {
		eventQueue.removeHandler(handler);
	}

	public void clearHandlers(EventQueueHandler handler) {
		eventQueue.clearEventQueuehandlers();
	}

	public void removeEventQueueHandler(EventQueueHandler listener) {
		eventQueueListeners.remove(listener);
	}

	public static Component toSource(SerializableEvent serializableEvent) {
		int sourceId = Integer.parseInt(serializableEvent.getSource());
		return StaticComponentRegistry.getComponent(sourceId);
	}

	public void dispatchEvent(SerializableEvent serializableEvent) {
		AWTEvent aDispatchedEvent = communicatedEventSupport.toDispatchedEvent(
				serializableEvent, toSource(serializableEvent));
		if (aDispatchedEvent != null)
			super.dispatchEvent(aDispatchedEvent);

	}
	
	public void dispatchReceivedEvent(AWTEvent anEvent) {
	
		if (anEvent != null)
			super.dispatchEvent(anEvent);

	}

	Set sources = new HashSet();

	// @Override
	// public Object getWToolkit() {
	// return wToolkit;
	// }

	public static String toID(AWTEvent anEvent) {
		int anId = 0;
		if (!(anEvent.getSource() instanceof Component)) { // event is either
			// a component
			// or instanceof
			// WToolkit, a
			// non public
			// class you
			// cannot seem
			// to name
			return "" + -1;
		}

	
			Component aComponent = (Component) anEvent.getSource();
			if (AWTMisc.isResizeEvent(anEvent)) {
				StaticComponentRegistry.registerComponentTree(aComponent);
			}
			anId = StaticComponentRegistry.getComponentId(aComponent); // should
																		// this
																		// not
																		// be
																		// above
																		// the
																		// first
																		// if
			if (anId < 0) { // this is the case when a resize evcent was not
							// received
				StaticComponentRegistry.registerComponentTree(aComponent);
				anId = StaticComponentRegistry.getComponentId(aComponent);
				System.out.println(anId);
			}
		return "" + anId;
	
	}

	public void dispatchEvent(AWTEvent anEvent) {

		AWTEvent sentEvent = communicatedEventSupport.toSentEvent(anEvent);

		// System.out.println("Dispatching:" + anEvent);
		// System.out.println("Sent event:" + sentEvent);

		if (sentEvent != null) {
//			int anId = 0;

			if (anEvent.getSource() instanceof Component) { // event is either
															// a component
															// or instanceof
															// WToolkit, a
															// non public
															// class you
															// cannot seem
															// to name
//				Component aComponent = (Component) anEvent.getSource();
//				if (AWTMisc.isResizeEvent(anEvent)) {
//					StaticComponentRegistry.registerComponentTree(aComponent);
//				}
//				anId = StaticComponentRegistry.getComponentId(aComponent); // should
//																			// this
//																			// not
//																			// be
//																			// above
//																			// the
//																			// first
//																			// if
//				if (anId < 0) { // this is the case when a resize evcent was not
//								// received
//					StaticComponentRegistry.registerComponentTree(aComponent);
//					anId = StaticComponentRegistry.getComponentId(aComponent);
//					System.out.println(anId);
//				}
				
				String id = toID(anEvent);

//				SerializableEvent serializedEvent = new ASerializableEvent(
//						sentEvent, "" + anId);
				SerializableEvent serializedEvent = new ASerializableEvent(
						sentEvent, id);

				for (EventQueueHandler listener : eventQueueListeners) {
					System.out.println(" sending:" + sentEvent);

					listener.newEvent(serializedEvent);
					listener.newEvent(sentEvent);
				}
			}
		}

		super.dispatchEvent(anEvent);

	}

	// public static Object deepCopy(Object orig) {
	// Object obj = orig;
	// try {
	// // Write the object out to a byte array
	// FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
	// ObjectOutputStream out = new ObjectOutputStream(fbos);
	// out.writeObject(orig);
	// out.flush();
	// out.close();
	//
	// // Retrieve an input stream from the byte array and read
	// // a copy of the object back in.
	// ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
	// obj = in.readObject();
	// } catch (InvalidClassException ice) {
	//
	// } catch (NotSerializableException nse) {
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException cnfe) {
	// cnfe.printStackTrace();
	// }
	// return obj;
	// }
	public static AnInputQueue getEventQueue() {
		if (eventQueue == null)
			eventQueue = new AnInputQueue();
		return eventQueue;
	}

	public static void useAsEventQueue() {
		getEventQueue();
	}

	@Override
	public void clearEventQueuehandlers() {
		eventQueueListeners.clear();

	}

	public CommunicatedAWTEventSupport getCommunicatedEventSupport() {
		return communicatedEventSupport;
	}

	public void setCommunicatedEventSupport(
			CommunicatedAWTEventSupport communicatedEventSupport) {
		this.communicatedEventSupport = communicatedEventSupport;
	}

	static {
		eventQueue = new AnInputQueue();
	}
}
