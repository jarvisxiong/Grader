package commandEngine.commands;

import util.annotations.Tags;
import graphics.avatar.IAvatar;
import graphics.view.IBridgeScene;

@Tags({ "Move Command" })
public class ApproachCommand implements IApproachCommand {
	private IAvatar avatar;
	private IBridgeScene scene;
	
	public ApproachCommand(IBridgeScene scene, IAvatar avatar) {
		this.scene = scene;
		this.avatar = avatar;
	}

	@Override
	public void run() {
		scene.approach(avatar);
	}

}
