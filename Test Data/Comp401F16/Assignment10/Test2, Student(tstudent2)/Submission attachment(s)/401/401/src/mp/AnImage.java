package mp;

import java.beans.PropertyChangeListener;

import mp.Locatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


@Tags({"Images"})
@PropertyNames({"x","y","imageFileName"})
@EditablePropertyNames({"x","y","imageFileName"}) 
@StructurePattern(StructurePatternNames.IMAGE_PATTERN)

public class AnImage extends Locatable implements Image {
	String imageFileName;
	//ArrayList<PropertyChangeListener> listeners;
	//PropertyListenerSupport propertySupport = new APropertyListenerSupport();

	public AnImage (String initImageFileName, int initX, int initY) {
		//listeners = new ArrayList<PropertyChangeListener>();
		imageFileName = initImageFileName;
		x = initX;
		y = initY;
	}
	
	
	public String getImageFileName() {
		return imageFileName;
		}
	public void setImageFileName(String newVal) {
		String oldVal = imageFileName;
		imageFileName = newVal ;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "ImageFileName", oldVal,newVal));
		}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);
		//listeners.add(listener);
	}
	
	public int listenerCount() {
		return propertySupport.size();
	}
}