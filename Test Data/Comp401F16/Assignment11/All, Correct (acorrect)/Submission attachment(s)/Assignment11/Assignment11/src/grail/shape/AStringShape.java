package grail.shape;
import java.beans.PropertyChangeEvent;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.STRING_PATTERN)

@PropertyNames({"x","y","text"})
@EditablePropertyNames({"x","y","text"})
public class AStringShape extends APoint implements StringShape {
	String text;
	//int x, y;

	public AStringShape(String initText, int initX, int initY) {
		super(initX,initY);
		text = initText;
//		x = initX;
//		y = initY;
	}
//	public int getX() {return x;}
//	public void setX(int newX) {x = newX;}
//	public int getY() {return y;}
//	public void setY(int newY) {y = newY;}
	public String getText() {return text;}
	public void setText(String newVal)
	{
		String oldVal=text;
		text = newVal;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Text",
				oldVal, newVal));
	}

}
