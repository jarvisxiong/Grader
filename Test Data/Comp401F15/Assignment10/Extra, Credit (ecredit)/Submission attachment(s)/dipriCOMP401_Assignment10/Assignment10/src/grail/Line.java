package grail;

import util.annotations.Tags;

@Tags({ "Line" })
public interface Line extends BoundedShape {
	public double getRadius();
	public double getAngle();
	public void setRadius(double aDouble);
	public void setAngle(double aDouble);
	public void rotate(int anInt);
}
