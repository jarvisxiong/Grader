package grail.draw.interfaces;

import java.awt.Color;

public interface StringShape extends Locator{

	public String getText();
	public void setText(String text);
	public Color getTextColor();
	public void setTextColor(Color newColor);
}
