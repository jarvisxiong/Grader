package commandEngine.commands;

import util.annotations.Tags;
import graphics.view.IBridgeScene;

@Tags({ "Say Command" })
public class SayCommand implements ISayCommand {
	private IBridgeScene scene;
	private String text;

	public SayCommand(IBridgeScene scene, String text) {
		this.scene = scene;
		this.text = text;
	}

	@Override
	public void run() {
		scene.say(text);
	}

}
