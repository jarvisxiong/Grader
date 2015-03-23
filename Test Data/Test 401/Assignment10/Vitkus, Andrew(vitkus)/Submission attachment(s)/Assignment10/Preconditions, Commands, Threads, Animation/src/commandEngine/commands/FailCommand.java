package commandEngine.commands;

import util.annotations.Tags;
import graphics.view.IBridgeScene;

@Tags({ "Fail Command" })
public class FailCommand implements IFailCommand {
	private IBridgeScene scene;
	
	public FailCommand(IBridgeScene scene) {
		this.scene = scene;
	}

	@Override
	public void run() {
		scene.fail();
	}

}
