package grail.scene;
import java.beans.PropertyChangeEvent;

import grail.StringShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"StringShape"})
@PropertyNames({ "x", "y","text"})
@EditablePropertyNames({ "x", "y","text" })
@StructurePattern(StructurePatternNames.STRING_PATTERN)
public class AStringShape extends ALocatable implements StringShape {
	String text;

	public AStringShape(String initText, int initX, int initY) {
		text = initText;
		x = initX;
		y = initY;
	}
	public String getText() {
		return text;
	}
	public void setText(String newVal) {
		String oldVal = text;
		text = newVal;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Text", oldVal,
                text));
	}
}