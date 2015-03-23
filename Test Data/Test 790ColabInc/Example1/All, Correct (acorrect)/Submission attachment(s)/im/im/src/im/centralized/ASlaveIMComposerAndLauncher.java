package im.centralized;

import im.AnIMComposerAndLauncher;
import im.AnIMInteractor;
import im.ReplicatedHistory;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.PeerMessageListener;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
import echo.modular.AHistory;
import echo.modular.AnEchoComposerAndLauncher;
import echo.modular.EchoerInteractor;
import echo.modular.History;
public class ASlaveIMComposerAndLauncher extends AnIMComposerAndLauncher {
	
	
	protected History<String> createHistory() {
		return new AHistory<String>();
	}
	protected EchoerInteractor createInteractor() {
		return new ASlaveIMInteractor( history, communicator);
	}		

	protected  void addInCoupler(Communicator communicator, History<String> aHistory) {
		inCoupler = new ASlaveInCoupler(aHistory, communicator.getClientName());
		communicator.addPeerMessageListener(inCoupler);
	}
	public static void main (String[] args) {
		Tracer.showInfo(true);
		SessionTracerSetter.setSessionPrintStatus();
		(new ASlaveIMComposerAndLauncher()).composeAndLaunch(args);
	}
}
