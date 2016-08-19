package mp.commandobjects;

import main.BridgeScene;
import util.annotations.Tags;

@Tags({"SayCommand"})
public class SayCommandObject implements Runnable{
	private BridgeScene scene;
	private String text;

	public SayCommandObject(BridgeScene scene, String text){
		this.scene = scene;
		this.text = text;
	}
	
	@Override
	public void run() {
		scene.say(text);
	}
	
}
