package mp.Scene;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.APropertyListenerSupport;
import mp.PropertyListenerSupport;
import util.annotations.PropertyNames;
import util.annotations.Tags;


@Tags({"Locatable"})
@PropertyNames({"X","Y"})
public class ALocatable implements Locatable{
	PropertyListenerSupport aListener= new APropertyListenerSupport();
	protected int x,y;
	public int getX() {
		return x;
		}
	public void setX(int newX) {
		int oldVal=x;
		x = newX;
		aListener.notifyAllListeners(new PropertyChangeEvent(this, "X",
				oldVal, newX));
		}
	public int getY() { 
		return y; 
		}
	public void setY(int newY) {
		int oldVal=y;
		y = newY;
		aListener.notifyAllListeners(new PropertyChangeEvent(this, "Y",
				oldVal, newY));
		}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		aListener.add(arg0);
	}
}
