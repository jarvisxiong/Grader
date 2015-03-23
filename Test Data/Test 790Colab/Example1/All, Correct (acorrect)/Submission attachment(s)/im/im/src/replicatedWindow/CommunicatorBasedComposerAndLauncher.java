package replicatedWindow;

import util.session.Communicator;

public interface CommunicatorBasedComposerAndLauncher {

	public abstract String getApplicationName();

	public abstract void compose(String[] args);

	public abstract void composeAndLaunch(String[] args);

	public abstract void checkArgs(String[] args);

	// parameters to factory
	public abstract Communicator createCommunicator(String args[]);

	public abstract Communicator getCommunicator();

}