package mp.commands;


import grail.shape.BridgeSceneInterface;
import util.annotations.Tags;
@Tags({"PassCommand"})
public class PassCommand implements Runnable{
	BridgeSceneInterface theScene;
	public PassCommand(BridgeSceneInterface aScene){
		theScene = aScene;
	}
	
	@Override
	public void run() {
		theScene.passScene();
	}

}
