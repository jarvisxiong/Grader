package mp.bean.commands;

import mp.ui.BridgeScene;
import util.annotations.Tags;

@Tags({"SayCommand"})

public class SayCommand implements Runnable {
	
	BridgeScene scene;
	String text;

	public SayCommand(BridgeScene aScene, String aText) {
		scene = aScene;
		text = aText;
	}
	public void run() {
		scene.say(text);
	}

}
