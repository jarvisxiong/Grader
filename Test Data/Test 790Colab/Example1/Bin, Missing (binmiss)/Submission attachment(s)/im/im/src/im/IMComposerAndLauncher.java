package im;

import replicatedWindow.CommunicatorBasedComposerAndLauncher;
import util.session.Communicator;
import util.session.PeerMessageListener;
import echo.modular.EchoerComposerAndLauncher;

public interface IMComposerAndLauncher extends EchoerComposerAndLauncher,  CommunicatorBasedComposerAndLauncher {
//	public static final String DIRECT = "P2P";
//	public static final String RELAYED = "Relayed";
//	public String getApplicationName();
	public PeerMessageListener getRemoteInputEchoer();
//	public Communicator getCommunicator();

}
