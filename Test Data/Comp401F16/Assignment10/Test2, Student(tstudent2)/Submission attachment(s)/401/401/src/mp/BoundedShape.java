package mp;

import mp.Locatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

@Tags({"BoundedShape"})
@PropertyNames({"x","y","width","height"})
@EditablePropertyNames({"x","y","width","height"}) 
public class BoundedShape extends Locatable implements BoundedShapeInter {
	public int width,height;
	// Getters-------------------------------------------------------------------------------------------------------------------------------------

		public int getWidth(){return width;}
		public int getHeight(){return height;}
		
	// Setters-------------------------------------------------------------------------------------------------------------------------------------
	
		public void setWidth(int newWidth){
			int oldWidth = width;
			width = newWidth;
			propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldWidth, newWidth));
			}
		public void setHeight(int newHeight){
			int oldHeight = height;
			height = newHeight;
			propertySupport.notifyAllListeners(new PropertyChangeEvent(this,"Height", oldHeight, newHeight));
			}
		public void propertyChangeWidth(int newWidth, PropertyChangeEvent evt){
			width = newWidth;
		}
		public void propertyChangeHeight(int newHeight, PropertyChangeEvent evt){
			height = newHeight;
		}
}
