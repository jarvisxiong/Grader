package im;

import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;
import echo.modular.AnEchoInteractor;
import echo.modular.History;
@Tags({ApplicationTags.IM, InteractiveTags.INTERACTOR})

public class AnIMInteractor extends AnEchoInteractor  {
	protected Communicator communicator;
	public AnIMInteractor(History<String> aHistory, Communicator aCommunicator) {
		super(aHistory);
		communicator = aCommunicator;
	}
	
	protected void addToHistory(String newValue) {
//		((ReplicatedHistory) history).replicatedAdd(history.size(), newValue);
		((ReplicatedHistory) history).replicatedAdd(newValue);

	}
	protected void processQuit() {
		super.processQuit();
		communicator.leave();
	}
}
