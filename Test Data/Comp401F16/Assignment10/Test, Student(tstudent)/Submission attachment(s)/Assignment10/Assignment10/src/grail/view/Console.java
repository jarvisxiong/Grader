package grail.view;

import java.beans.PropertyChangeListener;

import grail.scene.BridgeScene;
import util.annotations.Tags;

@Tags({"ConsoleSceneView"})
public interface Console extends PropertyChangeListener {
	public BridgeScene getScene();
	public void setScene(BridgeScene toListen);
	
}
