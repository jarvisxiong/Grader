package graphics.avatar;

import graphics.shapes.IRotatingLine;
import graphics.shapes.IStringShape;
import graphics.types.IMovable;

import java.awt.Color;
import java.awt.event.ActionListener;

import util.annotations.Tags;

@Tags({ "Avatar" })
public interface IAvatar extends IMovable {
	public void move(int dx, int dy);
	public void rotateNeck(int steps);
	public void rotateBody(int steps);
	public void rotateRightArm(int steps);
	public void rotateLeftArm(int steps);
	public void rotateRightLeg(int steps);
	public void rotateLeftLeg(int steps);
	public Color getBodyColor();
	public void setBodyColor(Color newColor);
	public Color getTextColor();
	public void setTextColor(Color newColor);
	public double getSizeScale();
	public void setSizeScale(double newScale);
	public IHead getHead();
	public IAngleShape getArms();
	public IAngleShape getLegs();
	public IRotatingLine getBody();
	public IRotatingLine getNeck();
	public IStringShape getText();
	public void angleNeck(double newAngle);
	public void angleBody(double newAngle);
	public void angleRightArm(double newAngle);
	public void angleLeftArm(double newAngle);
	public void angleRightLeg(double newAngle);
	public void angleLeftLeg(double newAngle);
	public void say(String text);
	public void addActionListener(ActionListener listener);
}
