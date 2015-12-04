package mp.shapes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.helpers.APropertyListenerSupport;
import mp.helpers.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"Locatable"})
@StructurePattern("Bean Pattern")
@PropertyNames({"x", "y"})
@EditablePropertyNames({"x", "y"})
public abstract class ALocatable implements Locatable{
	protected int x;
	protected int y;
	protected PropertyListenerSupport properties;
	
	public ALocatable(){
		properties = new APropertyListenerSupport();
	}
	
	@Override
	public int getX() {
		return x;
	}
	@Override
	public void setX(int newVal) {
		int oldX = this.x;
		this.x = newVal;
		properties.notifyAllListeners(new PropertyChangeEvent(this, "X", oldX, this.x));
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public void setY(int newVal) {
		int oldY = this.y;
		this.y = newVal;
		properties.notifyAllListeners(new PropertyChangeEvent(this, "Y", oldY, this.y));
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener property) {
		properties.add(property);
	}
}
