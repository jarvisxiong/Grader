package mp.shapes;

import java.awt.Color;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
public interface LineShape extends BoundedShape{
	public void setAngle(double angle);
	public void setRadius(double radius);
	public void rotate(double rotateAmount);
	public double getRadius();
	public Color getColor();
	public void setColor(Color color);
}
