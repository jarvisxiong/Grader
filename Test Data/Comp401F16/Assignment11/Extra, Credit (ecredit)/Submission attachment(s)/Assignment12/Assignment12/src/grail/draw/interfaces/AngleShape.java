package grail.draw.interfaces;

import java.awt.Color;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Angle"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface AngleShape extends MutableShape{
		public RotatingShape getLeftLine();
		public RotatingShape getRightLine();
		public void move(int x, int y);
		public void setRadius(double radius);
		public void setAngle(double angle);
		public void setColor(Color newColor);
		public Color getColor();
		public void rotate(int unit);
		public double getRadius();
		public double getAngle();
		public void scale(double scaleFactor);
}
