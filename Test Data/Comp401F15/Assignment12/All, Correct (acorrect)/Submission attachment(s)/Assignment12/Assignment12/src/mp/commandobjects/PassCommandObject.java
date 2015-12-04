package mp.commandobjects;

import main.BridgeScene;
import util.annotations.Tags;

@Tags({"PassCommand"})
public class PassCommandObject implements Runnable{
	private BridgeScene scene;
	
	public PassCommandObject(BridgeScene scene){
		this.scene = scene;
	}
	
	@Override
	public void run(){
		scene.passed();
	}
}
