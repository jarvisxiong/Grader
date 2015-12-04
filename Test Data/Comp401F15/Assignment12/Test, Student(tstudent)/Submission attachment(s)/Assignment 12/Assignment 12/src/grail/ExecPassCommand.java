package grail;

import mp.BridgeScene;

public class ExecPassCommand implements ExecutablePass{
	BridgeScene aScene;
	
	public ExecPassCommand (BridgeScene theScene) {
		aScene = theScene;
	}
	
	public void run() {
		aScene.passed();
	}
}
