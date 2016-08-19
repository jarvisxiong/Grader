package mp.bean.commands;

import mp.ui.BridgeScene;
import util.annotations.Tags;

@Tags({"PassCommand"})

public class PassCommand implements Runnable {
	
	BridgeScene scene;

	public PassCommand(BridgeScene aScene) {
		scene = aScene;
	}
	
	public void run() {
		scene.passed();
	}

}
