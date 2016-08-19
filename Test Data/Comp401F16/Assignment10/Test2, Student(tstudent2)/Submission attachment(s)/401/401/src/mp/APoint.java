package mp;

import java.beans.PropertyChangeListener;

import mp.Locatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.models.PropertyListenerRegisterer;

@PropertyNames({"x","y"})
@EditablePropertyNames({"x","y"}) 
public class APoint extends Locatable implements Point, PropertyListenerRegisterer{
	public APoint(int iniX, int iniY){
		x = iniX;
		y = iniY;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		propertySupport.add(listener);
	}
}
