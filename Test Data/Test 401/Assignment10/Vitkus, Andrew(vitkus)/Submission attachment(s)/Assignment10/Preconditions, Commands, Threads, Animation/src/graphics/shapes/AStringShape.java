package graphics.shapes;

import graphics.types.Movable;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeListener;

import util.APropertyChangeListenerManager;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@PropertyNames({ "Text", "X", "Y", "Color", "Font", "Filled" })
@EditablePropertyNames({ "Text", "X", "Y", "Color", "Font" })
@StructurePattern(StructurePatternNames.STRING_PATTERN)
@Tags({ "String Shape" })
public class AStringShape extends Movable implements IStringShape {
	private String text;
	private Color color;
	private Font font;

	private final APropertyChangeListenerManager pclm = new APropertyChangeListenerManager(this);

	public AStringShape(String text, int x, int y) {
		this(text, x, y, new Font("Arial", Font.PLAIN, 12));
	}

	public AStringShape(String text, int x, int y, Font font) {
		super(x, y);
		this.text = text;
		this.font = font;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		pclm.firePropertyChange("text", oldText, text);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color newColor) {
		Color oldColor = color;
		color = newColor;
		pclm.firePropertyChange("color", oldColor, color);
	}

	@Override
	public void setX(int newX) {
		int oldX = getX();
		super.setX(newX);
		pclm.firePropertyChange("x", oldX, getX());
	}

	@Override
	public void setY(int newY) {
		int oldY = getY();
		super.setY(newY);
		pclm.firePropertyChange("y", oldY, getY());
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font newFont) {
		Font oldFont = font;
		font = newFont;
		pclm.firePropertyChange("font", oldFont, font);
	}

	@Override
	public boolean isFilled() {
		return true;
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pclm.addPropertyChangeListener(listener);
	}
}
