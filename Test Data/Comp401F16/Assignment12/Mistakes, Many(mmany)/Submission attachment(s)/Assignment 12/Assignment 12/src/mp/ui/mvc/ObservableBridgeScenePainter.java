package mp.ui.mvc;

import java.beans.PropertyChangeListener;

import util.annotations.Tags;

@Tags("ObservableBridgeScenePainter")

public interface ObservableBridgeScenePainter extends PropertyChangeListener {
	
	@Tags("addPaintListener")
	public void addPaintListener(PaintListener l);

}
