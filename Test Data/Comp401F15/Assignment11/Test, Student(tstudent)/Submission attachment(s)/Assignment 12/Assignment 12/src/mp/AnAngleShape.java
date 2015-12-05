package mp;


import util.annotations.Tags;
import util.annotations.Visible;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Angle"})

public class AnAngleShape implements AngleShape {
	Line leftLine, rightLine;
	int leftX, leftY, rightX, rightY;
	private final int LEGS_APPROACH_X = 800;
	private final int LEGS_APPROACH_Y = 330;
	private final int ARMS_APPROACH_X = 800;
	private final int ARMS_APPROACH_Y = 280;
	private final int ARMS_PASS_X = 1450;
	private final int ARMS_PASS_Y = 100;
	private final int LEGS_PASS_X = 1450;
	private final int LEGS_PASS_Y = 150;
	private final int ARMS_FAIL_X = 1250;
	private final int ARMS_FAIL_Y = 100;
	private final int LEGS_FAIL_X = 1250;
	private final int LEGS_FAIL_Y = 150;
	
	public AnAngleShape () {};
	public AnAngleShape (Line newLeftLine, Line newRightLine) {
		leftLine = newLeftLine;
		leftX = leftLine.getX();
		leftY = leftLine.getY();
		rightLine = newRightLine;
		rightX = rightLine.getX();
		rightY = rightLine.getY();
	}
	
	public Line getLeftLine() {return leftLine;}
	public Line getRightLine() {return rightLine;}

	@Visible(false)	
	public void move (int addX, int addY) {
		int tempX, tempY;
		tempX = leftLine.getX();
		tempY = leftLine.getY();
		leftLine.setX(tempX + addX);
		leftLine.setY(tempY + addY);
		tempX = rightLine.getX();
		tempY = rightLine.getY();
		rightLine.setX(tempX + addX);
		rightLine.setY(tempY + addY);
	}
@Visible(false)
	public void legsApproach() {
		leftLine.setX(LEGS_APPROACH_X);
		leftLine.setY(LEGS_APPROACH_Y);
		rightLine.setX(LEGS_APPROACH_X);
		rightLine.setY(LEGS_APPROACH_Y);
	}
@Visible(false)
	public void armsApproach() {
		leftLine.setX(ARMS_APPROACH_X);
		rightLine.setX(ARMS_APPROACH_X);
		leftLine.setY(ARMS_APPROACH_Y);
		rightLine.setY(ARMS_APPROACH_Y);
	}
@Visible(false) 
	public void armsPass() {
		leftLine.setX(ARMS_PASS_X);
		leftLine.setY(ARMS_PASS_Y);
		rightLine.setX(ARMS_PASS_X);
		rightLine.setY(ARMS_PASS_Y);
	}
@Visible(false) 
	public void legsPass() {	
		leftLine.setX(LEGS_PASS_X);
		leftLine.setY(LEGS_PASS_Y);
		rightLine.setX(LEGS_PASS_X);
		rightLine.setY(LEGS_PASS_Y);
	}
@Visible(false) 
	public void armsFail() {
		leftLine.setX(ARMS_FAIL_X);
		leftLine.setY(ARMS_FAIL_Y);
		rightLine.setX(ARMS_FAIL_X);
		rightLine.setY(ARMS_FAIL_Y);
	}
@Visible(false) 
	public void legsFail() {
		leftLine.setX(LEGS_FAIL_X);
		leftLine.setY(LEGS_FAIL_Y);
		rightLine.setX(LEGS_FAIL_X);
		rightLine.setY(LEGS_FAIL_Y);
	}

@Visible(false)
	public void reset() {
		leftLine.setX(leftX);
		leftLine.setY(leftY);
		rightLine.setX(rightX);
		rightLine.setY(rightY);
	}
@Visible(false)
	public void relocate(int newX, int newY) {
		leftLine.setX(newX);
		leftLine.setY(newY);
		rightLine.setX(newX);
		rightLine.setY(newY);
	}
}
