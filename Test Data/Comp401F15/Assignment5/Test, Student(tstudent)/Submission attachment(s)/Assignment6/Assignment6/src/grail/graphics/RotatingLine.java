package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
public interface RotatingLine extends Line {
	public void rotate(int amount);
	public void setAngle(double newAngle);
	public void setRadius(double newRadius);
	public void moveTopLeft(int newX, int newY);
	public void move(int newX, int newY);
}
