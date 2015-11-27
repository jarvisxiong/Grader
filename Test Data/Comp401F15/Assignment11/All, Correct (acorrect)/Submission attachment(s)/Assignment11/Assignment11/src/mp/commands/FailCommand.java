package mp.commands;


import grail.shape.BridgeSceneInterface;
import util.annotations.Tags;
@Tags({"FailCommand"})
public class FailCommand implements Runnable{
	BridgeSceneInterface theScene;
	public FailCommand(BridgeSceneInterface aScene){
		theScene = aScene;
	}
	
	@Override
	public void run() {
		theScene.failScene();
	}

}
