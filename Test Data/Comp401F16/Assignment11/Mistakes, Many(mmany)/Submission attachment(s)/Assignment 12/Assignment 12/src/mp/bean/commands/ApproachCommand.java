package mp.bean.commands;

import mp.ui.BridgeScene;
import util.annotations.Tags;
import mp.ui.Avatar;

@Tags({"ApproachCommand"})

public class ApproachCommand implements Runnable {
	
	BridgeScene scene;
	Avatar avatar;

	public ApproachCommand(BridgeScene aScene, Avatar anAvatar){
		scene = aScene;
		avatar = anAvatar;
	}
	
	public void run() {
		scene.approach(avatar);
	}

}
