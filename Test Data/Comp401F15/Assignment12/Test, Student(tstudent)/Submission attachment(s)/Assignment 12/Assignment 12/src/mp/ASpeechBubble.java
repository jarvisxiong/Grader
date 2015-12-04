package mp;

import java.beans.PropertyChangeEvent;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Text"})

public class ASpeechBubble implements SpeechBubble{
	StringShape text;
	int x, y, height;
	private final int APPROACH_X = 775;
	private final int APPROACH_Y = 220;
	private final int PASS_X = 1425;
	private final int PASS_Y = 40;
	private final int FAIL_X = 1225;
	private final int FAIL_Y = 40;
	
	public ASpeechBubble() {}
	public ASpeechBubble (String initText, int initX, int initY, int initHeight) {
		x = initX;
		y = initY;
		height = initHeight;
		text = new AStringShape(initText, initX, initY-initHeight);
	}
	
	public StringShape getText() {
		return text;
	}
	public void setText(String newText) {
		text.setText(newText);
	}
@Visible(false)
	public void move (int addX, int addY) {
		int tempX, tempY;
		tempX = text.getX();
		tempY = text.getY();
		text.setX(tempX + addX);
		text.setY(tempY + addY);
	}
@Visible(false)
	public void approach() {
		text.setX(APPROACH_X);
		text.setY(APPROACH_Y);
	}
@Visible(false) 
	public void pass() {
		text.setX(PASS_X);
		text.setY(PASS_Y);
	}
@Visible(false) 
	public void fail() {
		text.setX(FAIL_X);
		text.setY(FAIL_Y);
	}
@Visible(false)
public void setString(StringShape newStringShape) {text = newStringShape;}

@Visible(false)
	public void reset() {
		text.setX(x);
		text.setY(y-height);
	}

@Visible(false) 
	public void relocate(int newX, int newY) {
		text.setX(newX);
		text.setY(newY-height);
	}
}
