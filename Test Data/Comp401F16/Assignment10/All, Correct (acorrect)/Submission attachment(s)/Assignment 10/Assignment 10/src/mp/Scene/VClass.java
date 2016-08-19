package mp.Scene;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

public interface VClass {
	public Line getRightLine();
	public Line getLeftLine();
	public void move(int deltaX, int deltaY);
	
}
