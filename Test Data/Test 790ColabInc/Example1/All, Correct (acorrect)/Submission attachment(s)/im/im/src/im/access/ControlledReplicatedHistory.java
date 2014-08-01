package im.access;

import im.ReplicatedHistory;

public interface ControlledReplicatedHistory<ElementType> extends ReplicatedHistory<ElementType> {
	public void addVetoer(Vetoer theVetoer);
	public void removeVetoer(Vetoer theVetoer);
}
