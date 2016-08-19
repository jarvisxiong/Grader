package grail.draw.objects;

import java.awt.Color;
import java.beans.PropertyChangeEvent;

import grail.draw.interfaces.StringShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"X", "Y", "Text", "TextColor"})
@EditablePropertyNames({"X", "Y", "Text", "TextColor"})
public class BasicStringShape extends BasicLocator implements StringShape{
	String text;
	private Color textColor;
	
	public BasicStringShape() {
		this("Default",0,0);
		
	}
	
	public BasicStringShape(String text) {
		this(text,0,0);
	}
	
	public BasicStringShape(String text, int xPos, int yPos) {
		super();
		this.x = xPos;
		this.y = yPos;
		this.text = text;
		this.textColor = Color.black;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		String oldText = this.text;
		this.text = text;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Text", oldText,this.text));
		}
	}
	

	@Override
	public Color getTextColor() {
		return this.textColor;
	}

	@Override
	public void setTextColor(Color newColor) {
		Color oldColor = this.textColor;
		this.textColor = newColor;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "TextColor", oldColor,this.textColor));
		}
		
	}


}
