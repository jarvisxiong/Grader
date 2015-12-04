package mp.commandobjects;

import main.BridgeScene;
import mp.shapes.Avatar;
import util.annotations.Tags;

@Tags({"ApproachCommand"})
public class ApproachCommandObject implements Runnable{
	private BridgeScene scene;
	private Avatar avatar;
	
	public ApproachCommandObject(BridgeScene scene, Avatar avatar){
		this.scene = scene;
		this.avatar = avatar;
	}
	
	@Override
	public void run() {
		scene.approach(avatar);
	}

}
