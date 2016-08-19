package mp.command;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface CommandInterpreterView extends PropertyChangeListener{
	public void propertyChange(PropertyChangeEvent evt);
}
