package grail.draw.interfaces;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
public interface RotatingShape extends ColoredShape {
	public void setRadius(double val);
	public double getRadius();
	public double getAngle();
	public void setAngle(double val);
	public void rotate(int units);
}
