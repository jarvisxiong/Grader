package im.centralized;

import im.AReplicatedHistory;
import im.AnIMComposerAndLauncher;
import util.session.CommunicatorSelector;
import util.session.Communicator;
import util.session.PeerMessageListener;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
import echo.modular.AHistory;
import echo.modular.AnEchoComposerAndLauncher;
import echo.modular.EchoerInteractor;
import echo.modular.History;
public class AMasterIMComposerAndLauncher extends AnIMComposerAndLauncher {
	
		
	protected History<String> createHistory() {
		return new ACentralizedHistory<String>(communicator);
	}
	// no UI, so no interactor
	protected void connectModelInteractor() {
		
	}
	// same reason as above, no input
	public void launch() {
	}

	protected  void addInCoupler(Communicator communicator, History<String> aHistory) {
		inCoupler = new AMasterInCoupler(aHistory);
		communicator.addPeerMessageListener(inCoupler);
	}
	public static void main (String[] args) {
		Tracer.showInfo(true);
		SessionTracerSetter.setSessionPrintStatus();
		(new AMasterIMComposerAndLauncher()).composeAndLaunch(args);
	}
}
