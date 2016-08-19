package grail.draw.interfaces;

import java.awt.Color;

import util.annotations.Tags;

@Tags({"BoundedShape"})
public interface ColoredShape extends Shape{
	public Color getColor();
	public void setColor(Color newColor);
	public boolean getFilled();
	public void setFilled(boolean filled);
}
