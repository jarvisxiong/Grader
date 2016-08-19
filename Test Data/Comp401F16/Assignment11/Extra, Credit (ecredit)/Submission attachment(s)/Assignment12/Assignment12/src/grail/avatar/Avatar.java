package grail.avatar;

import java.awt.Color;
import grail.draw.interfaces.AngleShape;
import grail.draw.interfaces.ImageShape;
import grail.draw.interfaces.RotatingShape;
import grail.draw.interfaces.StringShape;
import util.annotations.Tags;

@Tags({"Avatar"})
public interface Avatar {
	public String getBasicText();
	public void setBasicText(String setVal);
	public ImageShape getHead();
	public AngleShape getArms();
	public AngleShape getLegs();
	public RotatingShape getBody();
	public void move(int x, int y);
	public void scale(double scaleValue);
	public void setText(StringShape text);
	public StringShape getText();
	public void setLocation(int x, int y);
	public void setBodyColor(Color newBodyColor);
	public void setTextColor(Color newTextColor);
	public Color getBodyColor();
	public Color getTextColor();
	public int getHeight();
	public int getWidth();
	public int getX();
	public void setX(int newVal);
	public int getY();
	public void setY(int newVal);
	public void rebuild ();

}
