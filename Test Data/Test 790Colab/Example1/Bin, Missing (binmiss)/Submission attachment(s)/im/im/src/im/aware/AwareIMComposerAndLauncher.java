package im.aware;

import im.IMComposerAndLauncher;
import util.session.SessionMessageListener;

public interface AwareIMComposerAndLauncher extends IMComposerAndLauncher {
	public SessionMessageListener getSessionAwarenesManager();

}
