package im;

import trace.im.ListEditReceived;
import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;
import echo.modular.History;

public class AHistoryInCoupler implements PeerMessageListener {
	protected History<String> history;
	public AHistoryInCoupler(History<String>  theEchoer) {
		history = theEchoer;
	}
	public void objectReceived(Object message, String userName) {
		// need for integration with RPC
		if (message instanceof ListEdit)
			processReceivedListEdit((ListEdit<String>) message, userName);
	}
	public static String remoteEcho(String anInput, String aUserName) {
		return anInput + "[" + aUserName + "]";
	}
	protected void processReceivedListEdit (ListEdit<String> aRemoteEdit, String aUserName) {
		ListEditReceived.newCase(
				CommunicatorSelector.getProcessName(),
				aRemoteEdit.getOperationName(), 
				aRemoteEdit.getIndex(), 
				aRemoteEdit.getElement(), aUserName, 
				this);
		String anInput = aRemoteEdit.getElement();
		// not observable add so we do not get echo message
		// add before we print the message in case some  something reacts to the print
		// before we add
		history.add(aRemoteEdit.getIndex(), anInput);
		System.out.println(remoteEcho(anInput, aUserName));
	
	}

}
