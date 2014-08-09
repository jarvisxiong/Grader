package echo.modular;

import replicatedWindow.CommunicatorBasedComposerAndLauncher;


public interface EchoerComposerAndLauncher {
	public History<String> getHistory();
	public EchoerInteractor getInteractor();
	public void compose(String[] args);
	public void composeAndLaunch(String[] args);
	public void launch();

}
