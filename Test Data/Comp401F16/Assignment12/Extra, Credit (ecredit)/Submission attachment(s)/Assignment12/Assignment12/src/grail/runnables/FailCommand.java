package grail.runnables;

import grail.scene.BridgeScene;
import util.annotations.Tags;

@Tags({"FailCommand"})
public class FailCommand implements Runnable {
	private BridgeScene scene;
		
	public FailCommand(BridgeScene scene) {
		this.scene = scene;
	}

	public void run() {
		this.scene.failed();

	}

}
