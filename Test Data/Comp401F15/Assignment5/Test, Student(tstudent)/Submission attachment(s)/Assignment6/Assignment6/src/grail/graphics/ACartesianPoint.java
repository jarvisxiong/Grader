package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"X", "Y", "Angle", "Radius"})
@EditablePropertyNames({"X", "Y"})
public class ACartesianPoint extends ALocatable implements Point {
	
	public ACartesianPoint(int newX, int newY) {
		setX(newX);
		setY(newY);
	}

	public double getAngle() {
		return Math.atan2(getY(), getX());
	}
	
	public double getRadius() {
		return Math.sqrt(getX() * getX() + getY() * getY());
	}
}
