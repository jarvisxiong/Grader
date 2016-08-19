package grail.scene;

import grail.BridgeScene;
import util.annotations.Tags;



@Tags({"SayCommand"})
public class SayCommand implements Runnable {

	BridgeScene scene;
	String say = "";
	
	
	public SayCommand(BridgeScene aScene, String aString){
		scene = aScene;
		aString = say;
	}
	
	public void run() {
		scene.sayScene(say);
		
	}

}
