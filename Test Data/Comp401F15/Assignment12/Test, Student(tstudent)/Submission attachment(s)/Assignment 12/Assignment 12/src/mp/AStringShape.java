package mp;

import java.beans.PropertyChangeEvent;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.STRING_PATTERN)

public class AStringShape extends ALocatable implements StringShape{
	String text;
	
	public AStringShape () {};
	public AStringShape(String initText, int initX, int initY) {
		text = initText;
		x = initX;
		y = initY;
	}
	public String getText() {
		return text;
	}
	public void setText(String newVal) {
		String oldVal = getText();
		text = newVal;
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "Text", oldVal, newVal);
		alertListeners(newEvent);
	}
}
