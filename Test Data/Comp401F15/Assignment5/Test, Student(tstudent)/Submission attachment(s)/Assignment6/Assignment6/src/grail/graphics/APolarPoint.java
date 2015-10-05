package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"X", "Y", "Angle", "Radius"})
@EditablePropertyNames({"X", "Y"})
public class APolarPoint extends ALocatable implements Point {
	private double angle;
	private double radius;
	
	public APolarPoint (double newAngle, double newRadius) {
		angle = newAngle;
		radius = newRadius;
	}
	
	public APolarPoint (int xCoord, int yCoord) {
		radius = Math.sqrt(xCoord*xCoord + yCoord*yCoord);
		angle = Math.atan((double) yCoord/xCoord);
	}
	
	public int getX() {
		return (int) (radius * Math.cos(angle));
	}
	
	public int getY() {
		return (int) (radius * Math.sin(angle));
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setX(int newX) {
		angle = Math.atan((double) getY()/newX);
		radius = Math.sqrt(newX*newX + getY()*getY());
	}
	
	public void setY(int newY) {
		angle = Math.atan((double)newY/getX());
		radius = Math.sqrt(getX()*getX() + newY*newY);
	}

}