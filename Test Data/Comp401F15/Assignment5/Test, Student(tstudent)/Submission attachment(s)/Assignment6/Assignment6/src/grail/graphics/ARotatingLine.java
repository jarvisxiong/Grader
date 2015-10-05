package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"Height", "Width", "X", "Y"})
@EditablePropertyNames({"Angle", "Radius", "X", "Y", "Height", "Width"})
@Tags({"Rotating Line"})
public class ARotatingLine extends ALine implements RotatingLine {
	private static final double ROTATION_SCALE = 64;
	private Point topLeft;;
	private double angle;
	private double radius;
	private Point botRight;
	
	public ARotatingLine(double initAngle, double initRadius) {
		angle = initAngle;
		radius = initRadius;
		botRight = new APolarPoint(angle, radius);
		topLeft = new ACartesianPoint(0, 0);
	}
	
	public ARotatingLine(int initX, int initY, double initAngle, double initRadius) {
		angle = initAngle;
		radius = initRadius;
		botRight = new APolarPoint(angle, radius);
		topLeft = new ACartesianPoint(initX, initY);
	}
	
	public int getX() {
		return topLeft.getX();
	}
	
	public int getY() {
		return topLeft.getY();
	}
	
	public int getWidth() {
		return botRight.getX();
	}
	
	public int getHeight() {
		return botRight.getY();
	}
	
	public void setAngle(double newAngle) {
		angle = newAngle;
		botRight = new APolarPoint(angle, radius);
	}
	
	public void setRadius(double newRadius) {
		radius = newRadius;
		botRight = new APolarPoint(angle, radius);
	}
	
	@Tags({"rotate"})
	public void rotate(int amount) {
		angle += amount * (Math.PI/ROTATION_SCALE);
		setAngle(angle);
	}
	
	public void setX(int newX) {
		topLeft = new ACartesianPoint(newX, 0);
	}
	
	public void setY(int newY) {
		topLeft = new ACartesianPoint(0, newY);
	}
	
	public void moveTopLeft(int newX, int newY) {
		topLeft = new ACartesianPoint(newX, newY);
	}
	
	public void move(int xAmount, int yAmount) {
		topLeft.setX(topLeft.getX() + xAmount);
		topLeft.setY(topLeft.getY() + yAmount);
	}
}
 