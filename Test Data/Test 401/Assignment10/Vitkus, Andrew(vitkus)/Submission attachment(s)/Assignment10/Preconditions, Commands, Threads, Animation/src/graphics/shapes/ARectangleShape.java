package graphics.shapes;

import graphics.types.MovableResizableShape;

import java.awt.Color;
import java.beans.PropertyChangeListener;

import util.APropertyChangeListenerManager;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.IsAtomicShape;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
@PropertyNames({ "Height", "Width", "X", "Y", "Color", "Filled" })
@EditablePropertyNames({ "Height", "Width", "X", "Y", "Color" })
@IsAtomicShape(true)
@Tags({ "Rectangle Shape" })
public class ARectangleShape extends MovableResizableShape implements
		IRectangleShape {
	private Color color;
	private boolean filled;

	private final APropertyChangeListenerManager pclm = new APropertyChangeListenerManager(this);

	public ARectangleShape(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
		filled = true;
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

	@Override
	public void setHeight(int newHeight) {
		pclm.firePropertyChange("height", getHeight(), newHeight);
		super.setHeight(newHeight);
	}

	@Override
	public void setWidth(int newWidth) {
		pclm.firePropertyChange("width", getWidth(), newWidth);
		super.setWidth(newWidth);
	}

	@Override
	public boolean isFilled() {
		return filled;
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pclm.addPropertyChangeListener(listener);
	}

}
