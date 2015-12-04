package mp.commandobjects;

import main.BridgeScene;
import util.annotations.Tags;

@Tags({"FailCommand"})
public class FailCommandObject implements Runnable{
	private BridgeScene scene;
	
	public FailCommandObject(BridgeScene scene){
		this.scene = scene;
	}
	
	@Override
	public void run(){
		scene.failed();
	}
}
