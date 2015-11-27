package mp.commands;

import grail.shape.AvatarInterface;
import grail.shape.BridgeSceneInterface;
import util.annotations.Tags;
@Tags({"ApproachCommand"})
public class ApproachCommand implements Runnable{
	BridgeSceneInterface theScene;
	AvatarInterface theAvatar;
	public ApproachCommand(BridgeSceneInterface aScene, AvatarInterface anAvatar){
		theScene = aScene;
		theAvatar = anAvatar;
	}

	public void run() {
		theScene.approachScene(theAvatar);
	}

}
