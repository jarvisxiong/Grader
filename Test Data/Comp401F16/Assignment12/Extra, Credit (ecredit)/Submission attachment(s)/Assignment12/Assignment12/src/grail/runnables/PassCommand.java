package grail.runnables;

import grail.scene.BridgeScene;
import util.annotations.Tags;

@Tags({"PassCommand"})
public class PassCommand implements Runnable {
	private BridgeScene scene;
		
	public PassCommand(BridgeScene scene) {
		this.scene = scene;
	}

	public void run() {
		this.scene.passed();
	}

}
