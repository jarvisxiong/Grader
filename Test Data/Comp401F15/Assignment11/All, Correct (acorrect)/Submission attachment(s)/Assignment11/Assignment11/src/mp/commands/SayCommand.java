package mp.commands;


import grail.shape.BridgeScene;
import grail.shape.BridgeSceneInterface;
import util.annotations.Tags;
@Tags("SayCommand")
public class SayCommand implements Runnable{
	String theSpeech;
	BridgeSceneInterface theScene;
	
	public SayCommand(BridgeSceneInterface aScene, String aSpeech){
		theSpeech = aSpeech;
		theScene = aScene;
	}

	public void run() {
		theScene.sayScene(theSpeech);
	}
}
