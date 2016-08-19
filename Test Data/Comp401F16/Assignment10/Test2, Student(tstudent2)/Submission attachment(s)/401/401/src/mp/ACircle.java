package mp;

import java.beans.PropertyChangeListener;

import mp.BoundedShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;


@Tags({"Circle"})
@PropertyNames({"x","y","width","height"})
@EditablePropertyNames({"x","y","width","height"})
public class ACircle extends BoundedShape implements Circle, PropertyListenerRegisterer{
	public ACircle(int iniX, int iniY, int iniWidth, int iniHeight){
		x = iniX;
		y = iniY;
		width = iniWidth;
		height = iniHeight;
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);		
	}
}
