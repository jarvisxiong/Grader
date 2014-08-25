package replicatedWindow;

import java.awt.AWTEvent;

import util.session.Communicator;

public class AnEventQueueBroadcaster implements EventQueueHandler{
	ComponentRegistry componentRegistry;
	Communicator communicator;
	public AnEventQueueBroadcaster(Communicator theCommunicator) {
		communicator = theCommunicator;
		AnInputQueue.getEventQueue().addEventQueueHandler(this);

	}
	public AnEventQueueBroadcaster(Communicator aCommunicator, ComponentRegistry aComponentRegistry) {
		componentRegistry = aComponentRegistry;
		communicator = aCommunicator;
		AnInputQueue.getEventQueue().addEventQueueHandler(this);

	}

	@Override
	public void newEvent(AWTEvent event) {
//		if (event != null && event.getAWTEvent().getSource() instanceof Component && ! (event.getAWTEvent().getSource() instanceof Frame)) {
//			System.out.println(event.getAWTEvent().getSource());
//		}
//		System.out.println("Started broadcasting of event:" + event.getAWTEvent() );
//		if (event.isKeyEvent()) {
//			System.out.println("Key Event Source:" + event.getAWTEvent().getSource() +
//					" source id: " + event.getSource());
//		}
		SerializableEvent serializedEvent = new ASerializableEvent(event, AnInputQueue.toID(event));
//		groupSessionPort.sendAll(serializedEvent);
		communicator.toOthers(serializedEvent);

//		System.out.println("Finished broadcasting");

	}

	@Override
	public void newEvent(SerializableEvent event) {
		// TODO Auto-generated method stub
//		groupSessionPort.sendAll(event);

		
	}

}
