package im.centralized;

import im.AHistoryInCoupler;
import im.ListEdit;
import im.ReplicatedHistory;
import trace.im.ListEditReceived;
import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;
import echo.modular.History;

public class AMasterInCoupler extends AHistoryInCoupler {
	
	public AMasterInCoupler(History<String>  aHistory) {
		super(aHistory);
	}	
//	protected void processReceivedListEdit (ListEdit<String> aRemoteEdit, String aUserName) {
//		history.add(aRemoteEdit.getElement());			
//	}	
	public void objectReceived(Object message, String userName) {
		// need for integration with RPC
		if (message instanceof String)
			((CentralizedHistory<String>) history).centralizedAdd((String) message, userName); 
	}
	protected void connectModelInteractor() {
		
	}

}
