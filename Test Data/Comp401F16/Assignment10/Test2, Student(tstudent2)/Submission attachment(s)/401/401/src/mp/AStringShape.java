package mp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.Locatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.models.PropertyListenerRegisterer;

@PropertyNames({"x","y","text"})
@EditablePropertyNames({"x","y","text"}) 
@StructurePattern(StructurePatternNames.STRING_PATTERN)
public class AStringShape extends Locatable implements StringShape, PropertyListenerRegisterer{
	
	String text;
	public AStringShape(String iniText, int iniX, int iniY){
		text = iniText;
		x = iniX;
		y = iniY;
	}
	
	public void setText(String newText){
		String oldText = text;
		text = newText;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this,"Text", oldText,newText));
	}
	public String getText(){return text;}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);
		
	}
	
}
