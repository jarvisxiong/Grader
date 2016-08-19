package grail.toolkit;

import java.beans.PropertyChangeListener;
import javax.swing.JFrame;

public interface AdvancedCommandViewer extends CommandWidgets, PropertyChangeListener{
	public JFrame getFrame();
	public void placeFrameOnTop();
	
}
