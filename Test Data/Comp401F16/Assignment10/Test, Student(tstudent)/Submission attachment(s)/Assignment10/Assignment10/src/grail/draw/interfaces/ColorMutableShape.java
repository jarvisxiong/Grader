package grail.draw.interfaces;

import java.awt.Color;

public interface ColorMutableShape extends MutableShape{
	public void setColor(Color newColor);
	public Color getColor();
	public boolean getFilled();
	public void setFilled(boolean input);
}
