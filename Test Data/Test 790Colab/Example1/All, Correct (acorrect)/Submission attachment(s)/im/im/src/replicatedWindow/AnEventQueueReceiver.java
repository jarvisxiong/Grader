package replicatedWindow;

import java.awt.AWTEvent;

import util.session.PeerMessageListener;


public class AnEventQueueReceiver implements PeerMessageListener{
	
	ComponentRegistry componentRegistry;
	
	public AnEventQueueReceiver(ComponentRegistry aComponentRegistry) {
		componentRegistry = aComponentRegistry;		
	}
	
	




	@Override
	public void objectReceived(Object aMessage, String aSourceName) {
		if (aMessage instanceof SerializableEvent ) {
			SerializableEvent serializableEvent = (SerializableEvent) aMessage;
			AWTEvent aDispatchedEvent = AnInputQueue.getEventQueue().getCommunicatedEventSupport().toDispatchedEvent(
					serializableEvent, AnInputQueue.toSource(serializableEvent));
//			AnInputQueue.getEventQueue().dispatchEvent(serializableEvent);
			AnInputQueue.getEventQueue().dispatchReceivedEvent(aDispatchedEvent);

		}
		
	}
	

}
