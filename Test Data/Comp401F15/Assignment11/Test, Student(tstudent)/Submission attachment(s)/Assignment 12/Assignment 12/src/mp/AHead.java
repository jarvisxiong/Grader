package mp;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Head","X","Y"})

public class AHead extends ALocatable implements Head{
	ImageShape head;
	String file;
	protected int x, y;
	private final int APPROACH_X = 775;
	private final int APPROACH_Y = 230;
	private final int PASS_X = 1425;
	private final int PASS_Y = 50;
	private final int FAIL_X = 1225;
	private final int FAIL_Y = 50;
	
	public AHead () {};
	public AHead (String fileName, int initX, int initY, int initHeight, int initWidth) {
		x = initX;
		y = initY;
		file = fileName;
		head = new AnImage(fileName, initX, initY, initHeight, initWidth);
	}
	
	public ImageShape getHead () {
		return head;
	}
@Visible(false)
	public String getImageFileName() {
		return file;
	}
@Visible(false)
	public void move(int addX, int addY) {
		int tempX, tempY;
		tempX = head.getX();
		tempY = head.getY();
		head.setX(tempX + addX);
		head.setY(tempY + addY);
	}
@Visible(false)
	public void approach() {
		head.setX(APPROACH_X);
		head.setY(APPROACH_Y);
	}
@Visible(false) 
	public void pass() {
		head.setX(PASS_X);
		head.setY(PASS_Y);
	}
@Visible(false) 
	public void fail() {
		head.setX(FAIL_X);
		head.setY(FAIL_Y);
	}

@Visible(false)
	public void reset() {
		head.setX(x);
		head.setY(y);
	}
@Visible(false)
	public void relocate(int newX, int newY) {
		head.setX(newX);
		head.setY(newY);
	}
}
