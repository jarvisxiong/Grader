package mp.shapes;

import java.beans.PropertyChangeEvent;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public class AStringShape extends ABoundedShape implements StringShape{
	private String text; 
	public AStringShape(String string, int initX, int initY){
		setText(string);
		this.x = initX;
		this.y = initY;
	}
	@Visible(false)
	@Override
	public int getWidth() {
		return width;
	}
	@Visible(false)
	@Override
	public int getHeight() {
		return height;
	}
	@Override
	public void setText(String string) {
		String oldText = this.text;
		this.text = string;
		properties.notifyAllListeners(new PropertyChangeEvent(this, "Text", oldText, text));
	}
	@Override
	public String getText() {
		return text;
	}
	
}
