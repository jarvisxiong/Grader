package mp;

import java.beans.PropertyChangeEvent;

import mp.Scene.BridgeScene;
import mp.Scene.TheBridgeScene;
import util.annotations.Tags;

@Tags({"ConsoleSceneView"})
public class AConsoleView implements ConsoleView{
	BridgeScene model;
	public AConsoleView(BridgeScene aModel){
		model=aModel;
		TheBridgeScene.addPropertyChangeListener(model, this);
		model.addPropertyChangeListener(this);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Property change event: "+evt);
	}

}
