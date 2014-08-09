package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Component;
import java.util.Set;

public interface CommunicatedAWTEventSupport {
	public Set<Integer> getNotCommunicatedEventIDs();

	public void setNotCommunicatedEventIDs(Set<Integer> notCommunicatedEventIDs) ;
	public Set<Class> getCommunicatedEventClasses() ;

	public void setCommunicatedEventClasses(Set<Class> communicatedEventClasses) ;

	public Set<Class> getNotDispatchedRemoteEventClasses();

	public void setNotDispatchedRemoteEventClasses(
			Set<Class> notDispatchedRemoteEventClasses) ;
	public AWTEvent toSentEvent(AWTEvent anEvent);
	public AWTEvent toDispatchedEvent(SerializableEvent aReceivedEvent, Component component);

}
