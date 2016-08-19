package grail.graphics;

import util.annotations.Tags;
import util.annotations.IsAtomicShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@IsAtomicShape(false)
@Tags({"Angle"})
@PropertyNames({"X", "Y", "LeftLine", "RightLine"})
@EditablePropertyNames({"X", "Y"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
public class AVShape extends ALocatable implements VShape {
	private static final int DEFAULT_X_AND_Y = 100;
	private static final double DEFAULT_LEFT_LINE_ANGLE = Math.PI / 1.5;
	private static final double DEFAULT_RIGHT_LINE_ANGLE = Math.PI / 1.5 - Math.PI / 3;
	private static final double DEFAULT_LINE_LENGTH = 50.0;
	
	private RotatingLine leftLine;
	private RotatingLine rightLine;
	
	public AVShape() {
		super.setX(DEFAULT_X_AND_Y);
		super.setY(DEFAULT_X_AND_Y);
		leftLine = new ARotatingLine(getX(), getY(), DEFAULT_LEFT_LINE_ANGLE, 
						DEFAULT_LINE_LENGTH);
		rightLine = new ARotatingLine(getX(), getY(), DEFAULT_RIGHT_LINE_ANGLE, 
						DEFAULT_LINE_LENGTH);
	}
	
	public AVShape(int initX, int initY) {
		super.setX(initX);
		super.setY(initY);
		leftLine = new ARotatingLine(getX(), getY(), DEFAULT_LEFT_LINE_ANGLE, 
						DEFAULT_LINE_LENGTH);
		rightLine = new ARotatingLine(getX(), getY(), DEFAULT_RIGHT_LINE_ANGLE, 
						DEFAULT_LINE_LENGTH);
	}
	
	public void setX(int newX) {
		super.setX(newX);
		moveLines();
	}
	public void setY(int newY) {
		super.setY(newY);
		moveLines();
	}
	private void moveLines() {
		leftLine.moveTopLeft(getX(), getY());
		rightLine.moveTopLeft(getX(), getY());
	}
	public RotatingLine getLeftLine() {
		return leftLine;
	}
	public RotatingLine getRightLine() {
		return rightLine;
	}
	@Tags({"move"})
	public void move(int xAmount, int yAmount) {
		super.setX(getX() + xAmount);
		super.setY(getY() + yAmount);
		moveLines();
	}
}
