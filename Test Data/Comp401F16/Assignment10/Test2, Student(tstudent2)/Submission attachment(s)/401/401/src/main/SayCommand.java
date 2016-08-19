package main;

import mp.Avatar;
import util.misc.ThreadSupport;

public class SayCommand implements Runnable {

	BridgeSceneInterface scene;
	String text;
	
	public SayCommand(BridgeSceneInterface scene, String aText){
		this.scene = scene;
		text = aText;
	}
	
	public void run() {
		scene.getGuard().getText().setText(text);
		ThreadSupport.sleep(1000);
	}

}
