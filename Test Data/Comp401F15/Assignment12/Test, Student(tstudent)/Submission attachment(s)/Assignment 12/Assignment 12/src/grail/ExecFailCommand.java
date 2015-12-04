package grail;

import mp.BridgeScene;

public class ExecFailCommand implements ExecutableFail{
	BridgeScene aBridgeScene;
	
	public ExecFailCommand (BridgeScene theScene) {
		aBridgeScene = theScene;
	}
	public void run() {
		aBridgeScene.failed();
	}
}
