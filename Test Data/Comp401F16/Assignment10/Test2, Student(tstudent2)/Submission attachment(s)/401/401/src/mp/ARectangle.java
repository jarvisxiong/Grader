package mp;

import java.beans.PropertyChangeListener;

import mp.BoundedShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"Rectangle"})
@PropertyNames({"x","y","width","height"})
@EditablePropertyNames({"x","y","height","width"}) 
public class ARectangle extends BoundedShape implements Rectangle, PropertyListenerRegisterer {
	public ARectangle(int iniX, int iniY, int iniWidth, int iniHeight){
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
