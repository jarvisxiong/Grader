package grail;

import mp.Avatar;
import mp.BridgeScene;

public class ExecApproachCommand implements ExecutableApproach {
	BridgeScene aScene;
	Avatar anAvatar;
	
	public ExecApproachCommand (BridgeScene theScene, Avatar theAvatar) {
		aScene = theScene;
		anAvatar = theAvatar;
	}
	
	public void run() {
		aScene.approach(anAvatar);
	}
}
