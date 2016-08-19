package grail.runnables;

import grail.avatar.Avatar;
import grail.scene.BridgeScene;
import util.annotations.Tags;

@Tags({"ApproachCommand"})
public class ApproachCommand implements Runnable{
	private BridgeScene scene;
	private Avatar thisAvatar;
		
	public ApproachCommand(BridgeScene scene, Avatar avatar) {
		this.scene = scene;
		this.thisAvatar = avatar;
	}

	public void run() {
		this.scene.approach(thisAvatar);
		
	}

}
