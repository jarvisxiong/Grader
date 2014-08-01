package sasa.echoerAndIM;

import util.session.PeerMessageListener;

public class AnInCoupler implements PeerMessageListener {
	Echoer echoer;
	public AnInCoupler(Echoer theEchoer) {
		echoer = theEchoer;
	}
	public void objectReceived(Object message, String userName) {
		if (message instanceof ARemoteInput)
			processRemoteInput((ARemoteInput) message, userName);
	}
	void processRemoteInput (ARemoteInput remoteInput, String userName) {
		System.out.println("Remote Input:" + remoteInput.getData() + " from:" + userName);
		echoer.addToHistory((String) remoteInput.getData());
	}
}
