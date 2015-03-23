package im.centralized;

import im.AnIMInteractor;
import util.session.Communicator;
import echo.modular.AnEchoInteractor;
import echo.modular.History;

public class ASlaveIMInteractor extends AnIMInteractor  {
	public ASlaveIMInteractor(History<String> aHistory, Communicator aCommunicator) {
		super(aHistory, aCommunicator);
	}
	protected void addToHistory(String newValue) {
		communicator.toClient(MasterIMModelLauncher.CLIENT_NAME, newValue);
	}
	
}
