package grail.CharacterAnimation;

import java.beans.PropertyChangeEvent;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"x","y","text"})
@EditablePropertyNames({"x","y","text"})
public class StringShape extends LocatableShape implements StringShapeInterface {
	String text;


	public StringShape(String initText, int initX, int initY) {
		text = initText;
		x = initX;
		y = initY;
	}

	public String getText() {return text;}
	public void setText(String newVal) {
		String oldVal = text;
		text = newVal;
		notifyAllListeners(new PropertyChangeEvent(this, "X", oldVal, newVal));
	}
	

}
