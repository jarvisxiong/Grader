package mp;


import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Sword"})



public class ASword implements Sword{
	Line sword;
	int x,y;
	private final int APPROACH_X = 850;
	private final int APPROACH_Y = 330;
	private final int PASS_X = 1500;
	private final int PASS_Y = 150;
	private final int FAIL_X = 1300;
	private final int FAIL_Y = 150;
	
	public ASword () {};
	public ASword (int initX, int initY, int initHeight, int swordWidth, int swordLength, int initWidth) {
		sword = new LineShape(initX + initWidth, initY + initHeight, swordWidth, swordLength);
		x = initX + initWidth;
		y = initY + initHeight;
	}
	
	public Line getSword () {
		return sword;
	}
	@Visible(false)
	public void move (int addX, int addY) {
		int tempX, tempY;
		tempX = sword.getX();
		tempY = sword.getY();
		sword.setX(tempX + addX);
		sword.setY(tempY + addY);
	}
@Visible(false)
	public void approach() {
		sword.setX(APPROACH_X);
		sword.setY(APPROACH_Y);
	}
@Visible(false) 
	public void pass() {
		sword.setX(PASS_X);
		sword.setY(PASS_Y);
	}
@Visible(false) 
	public void fail() {
		sword.setX(FAIL_X);
		sword.setY(FAIL_Y);
	}

@Visible(false)
	public void reset() {
		sword.setX(x);
		sword.setY(y);
	}
@Visible(false)
	public void relocate(int newX, int newY) {
		sword.setX(newX);
		sword.setY(newY);
	}
}
