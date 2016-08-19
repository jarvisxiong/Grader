package grail.CharacterAnimation;

import java.beans.PropertyChangeListener;

public interface LocatableShapeInt extends PropertyListenerSupport{
	public int getX(); 
	public int getY(); 
	public void setX(int newX);
	public void setY(int newY);
	public void setXY(int newX, int newY);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	
}
