package grail.runnables;

import grail.scene.BridgeScene;

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
