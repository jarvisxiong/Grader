package grail.shape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.Explanation;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Visible(false)
@Explanation("Uses Cartesian representation.")
@Tags({"Locatable"})

@PropertyNames({"x","y"})
@EditablePropertyNames({"x","y"})
public class APoint implements Point {	
	protected int x, y;
	PropertyListenerSupport propertySupport = new APropertyListenerSupport();

	public APoint(int theX, int theY) {
		x = theX;
		y = theY;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int newX) 
	{
		int oldVal = x;
		x = newX;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "X",
				oldVal, newX));
	}
	public void setY(int newY)
	{
		int oldVal=y;
		y = newY;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Y",
				oldVal, newY));
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);
	}

	public int listenerCount() {
		return propertySupport.size();
	}
}
