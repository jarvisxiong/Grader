package sasa.echoerAndIM;

import java.util.ArrayList;
import java.util.List;

import util.session.Communicator;

public class AControlDispatcher extends AnOutCoupledEchoer implements ControlDispatcher {	
	List<Vetoer> vetoers = new ArrayList();
	public AControlDispatcher(Communicator theCommunicator) {
		super(theCommunicator);		
	}
	void processInput(String theNextInput) {
		if (vetoed(theNextInput))
			return;
		 super.processInput(theNextInput);
	}	
	public void addVetoer(Vetoer theVetoer) {
		vetoers.add(theVetoer);		
	}
	public void removeVetoer(Vetoer theVetoer) {
		vetoers.remove(theVetoer);		
	}
	boolean vetoed(String theValue) {
		for (Vetoer vetoer:vetoers) {
			if (vetoer.veto(theValue)) return true;
		}
		return false;
	}
}
