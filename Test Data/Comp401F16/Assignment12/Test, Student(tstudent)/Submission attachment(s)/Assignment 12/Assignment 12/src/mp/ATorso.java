package mp;


import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Torso"})

public class ATorso implements Torso{
	Line torso;
	int x, y;
	private final int APPROACH_X = 800;
	private final int APPROACH_Y = 280;
	private final int PASS_X = 1450;
	private final int PASS_Y = 100;
	private final int FAIL_X = 1250;
	private final int FAIL_Y = 100;
	
	public ATorso() {};
	public ATorso(int initX, int initY, int initWidth, int initHeight) {
		torso = new LineShape(initX, initY, 0, initHeight);
		x = initX;
		y = initY;
	}
	
	public Line getTorso() {
		return torso;
	}
	@Visible(false)
	public void move(int addX, int addY) {
		int tempX, tempY;
		tempX = torso.getX();
		tempY = torso.getY();
		torso.setX(tempX + addX);
		torso.setY(tempY + addY);
	}
@Visible(false)
	public void approach() {
		torso.setX(APPROACH_X);
		torso.setY(APPROACH_Y);
	}
@Visible(false) 
	public void pass() {
		torso.setX(PASS_X);
		torso.setY(PASS_Y);
	}
@Visible(false) 
	public void fail() {
		torso.setX(FAIL_X);
		torso.setY(FAIL_Y);
	}

@Visible(false)
	public void reset() {
		torso.setX(x);
		torso.setY(y);
	}
@Visible(false)
	public void relocate(int newX, int newY) {
		torso.setX(newX);
		torso.setY(newY);
	}
}
