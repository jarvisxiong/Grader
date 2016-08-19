package grail.runnables;

import grail.scene.BridgeScene;
import util.annotations.Tags;

@Tags({"SayCommand"})
public class SayCommand implements Runnable{
	private BridgeScene scene;
	private String toSay;
	
	public SayCommand(BridgeScene scene, String input) {
		this.scene = scene;
		this.toSay = input;
	}
	
	public void run() {
		this.scene.say(toSay);
		
	}


}
