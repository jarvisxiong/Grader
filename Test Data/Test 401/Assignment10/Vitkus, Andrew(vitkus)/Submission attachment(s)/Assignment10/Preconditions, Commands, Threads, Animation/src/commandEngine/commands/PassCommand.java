package commandEngine.commands;

import util.annotations.Tags;
import graphics.view.IBridgeScene;

@Tags({ "Pass Command" })
public class PassCommand implements IPassCommand {
	private IBridgeScene scene;
	
	public PassCommand(IBridgeScene scene) {
		this.scene = scene;
	}

	@Override
	public void run() {
		scene.pass();
	}

}
