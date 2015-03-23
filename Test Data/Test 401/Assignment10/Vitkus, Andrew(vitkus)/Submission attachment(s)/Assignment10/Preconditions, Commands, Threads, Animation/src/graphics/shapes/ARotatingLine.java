package graphics.shapes;

import graphics.types.BoundedMovableShape;

import java.awt.Color;
import java.beans.PropertyChangeListener;

import util.APropertyChangeListenerManager;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags({ "Rotating Line" })
@PropertyNames({ "Height", "Width", "X", "Y", "Angle", "Radius", "Color",
		"Filled" })
@EditablePropertyNames({ "Angle", "Radius", "X", "Y", "Color" })
public class ARotatingLine extends BoundedMovableShape implements IRotatingLine {

	private double radius, angle;
	private Color color;

	private final APropertyChangeListenerManager pclm = new APropertyChangeListenerManager(
			this);

	public ARotatingLine(int x, int y, double angle, double radius) {
		super(x, y, (int) Math.round(radius * Math.cos(angle)), (int) Math
				.round(angle * Math.sin(angle)));
		setRadius(radius);
		setAngle(angle);
	}

	public ARotatingLine() {
		this(0, 0, 0, 1);
	}

	@Override
	public void setAngle(double angle) {
		setWidth((int) Math.round(getRadius() * Math.cos(angle)));
		setHeight((int) Math.round(getRadius() * Math.sin(angle)));
		
		double oldAngle = this.angle;
		this.angle = angle;
		pclm.firePropertyChange("angle", oldAngle, this.angle);
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public void setRadius(double radius) {
		double prevRadius = getRadius();

		this.radius = radius;

		double limitPrevRadius = prevRadius == 0 ? 1. : prevRadius;

		setWidth((int) Math.round(((double) width) / limitPrevRadius * radius));
		setHeight((int) Math.round(((double) height) / limitPrevRadius * radius));
		
		pclm.firePropertyChange("radius", prevRadius, this.radius);
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	@Tags({ "rotate" })
	public void rotate(int units) {
		setAngle(getAngle() + Math.PI / 32. * units);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color newColor) {
		pclm.firePropertyChange("color", color, newColor);
		color = newColor;
	}

	@Override
	public void setX(int newX) {
		pclm.firePropertyChange("x", getX(), newX);
		super.setX(newX);
	}

	@Override
	public void setY(int newY) {
		pclm.firePropertyChange("y", getY(), newY);
		super.setY(newY);
	}

	private void setHeight(int newHeight) {
		pclm.firePropertyChange("height", getHeight(), newHeight);
		height = newHeight;
	}

	private void setWidth(int newWidth) {
		pclm.firePropertyChange("width", getWidth(), newWidth);
		width = newWidth;
	}

	@Override
	public boolean isFilled() {
		return false;
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pclm.addPropertyChangeListener(listener);
	}
}
