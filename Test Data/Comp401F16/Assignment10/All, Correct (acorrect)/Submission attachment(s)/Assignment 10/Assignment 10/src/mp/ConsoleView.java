package mp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Tags;
@Tags({"ConsoleSceneView"})
public interface ConsoleView extends PropertyChangeListener{
	public void propertyChange(PropertyChangeEvent evt);
}
