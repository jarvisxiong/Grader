package graphics.shapes;

import graphics.types.IColorable;
import graphics.types.IMovable;

import java.awt.Font;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({ "String Shape" })
public interface IStringShape extends IMovable, IColorable,
		PropertyListenerRegisterer {
	public String getText();

	public void setText(String newText);

	public Font getFont();

	public void setFont(Font newFont);
}
