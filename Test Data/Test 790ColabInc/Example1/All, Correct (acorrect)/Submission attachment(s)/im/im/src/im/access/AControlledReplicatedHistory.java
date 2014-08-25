package im.access;

import im.AReplicatedHistory;

import java.util.ArrayList;
import java.util.List;

import util.session.Communicator;

public class AControlledReplicatedHistory<ElementType> 
		extends AReplicatedHistory<ElementType> 
		implements ControlledReplicatedHistory<ElementType>  {	
	List<Vetoer<ElementType>> vetoers = new ArrayList();
	public AControlledReplicatedHistory(Communicator theCommunicator) {
		super(theCommunicator);		
	}
	public synchronized void replicatedAdd(ElementType anInput) {
		if (vetoed(anInput))
				return;
		super.replicatedAdd(anInput);
		
	}		
	public void addVetoer(Vetoer theVetoer) {
		vetoers.add(theVetoer);		
	}
	public void removeVetoer(Vetoer theVetoer) {
		vetoers.remove(theVetoer);		
	}
	boolean vetoed(ElementType theValue) {
		for (Vetoer vetoer:vetoers) {
			if (vetoer.veto(theValue)) return true;
		}
		return false;
	}
}
