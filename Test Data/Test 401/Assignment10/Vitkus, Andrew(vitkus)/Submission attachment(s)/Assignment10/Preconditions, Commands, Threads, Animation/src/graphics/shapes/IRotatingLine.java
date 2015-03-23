package graphics.shapes;

import graphics.types.IBoundedMovableShape;
import graphics.types.IColorable;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({ "Rotating Line" })
public interface IRotatingLine extends IBoundedMovableShape, IColorable,
		PropertyListenerRegisterer {
	public void setAngle(double angle);

	public double getAngle();

	public void setRadius(double radius);

	public double getRadius();

	public void rotate(int units);
}
