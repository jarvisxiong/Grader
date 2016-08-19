package mp.bean.commands;

import mp.ui.BridgeScene;
import util.annotations.Tags;

@Tags({"FailCommand"})

public class FailCommand implements Runnable {
	
	BridgeScene scene;

	public FailCommand(BridgeScene aScene) {
		scene = aScene;
	}
	
	public void run() {
		scene.failed();
	}

}