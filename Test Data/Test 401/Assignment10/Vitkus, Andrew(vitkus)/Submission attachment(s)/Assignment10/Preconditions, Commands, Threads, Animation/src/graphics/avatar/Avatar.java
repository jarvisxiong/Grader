package graphics.avatar;

import graphics.avatar.IHead.AvatarName;
import graphics.shapes.IRotatingLine;
import graphics.shapes.IStringShape;
import graphics.shapes.ARotatingLine;
import graphics.shapes.AStringShape;
import graphics.types.Movable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.EventListenerList;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.IsCompositeShape;

@PropertyNames({ "X", "Y", "SizeScale", "BodyColor", "TextColor", "Text",
		"Head", "Arms", "Legs", "Body", "Neck" })
@EditablePropertyNames({ "X", "Y", "SizeScale", "BodyColor", "TextColor" })
@Tags({ "Avatar" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@IsCompositeShape(true)
public class Avatar extends Movable implements IAvatar {
	private double sizeScale;
	private Color bodyColor, textColor;
	private IAngleShape arms, legs;
	private IHead head;
	private IRotatingLine body, neck;
	private IStringShape text;

	private final EventListenerList ell = new EventListenerList();

	public Avatar(AvatarName avatarName, int x, int y, Color color) {
		this(avatarName, x, y, color, color);
	}

	public Avatar(AvatarName avatarName, int x, int y, Color bodyColor,
			Color textColor) {
		super(x, y);

		body = new ARotatingLine(x, y, -Math.PI / 2., 50.);

		neck = new ARotatingLine(x + body.getWidth(), y + body.getHeight(), -Math.PI / 2., 15.);

		head = new Head(avatarName, x + neck.getWidth() + body.getWidth(), y + neck.getHeight() + body.getHeight());
		head.setX(x - head.getWidth() / 2 + neck.getWidth() + body.getWidth());
		head.setY(y - head.getHeight() + neck.getHeight() + body.getHeight());

		arms = new AngleShape(x + body.getWidth(), y + body.getHeight(), Math.PI / 4., 30., 3 * Math.PI / 4., 30.);

		legs = new AngleShape(x, y, Math.PI / 3., 40., 2 * Math.PI / 3., 40.);

		text = new AStringShape("", x + head.getWidth() / 2 + neck.getWidth() + body.getWidth(), y - head.getHeight() + neck.getHeight() + body.getHeight());
		text.setX(x + head.getWidth() / 2 + neck.getWidth() + body.getWidth());
		text.setY(y - head.getHeight() + neck.getHeight() + body.getHeight() - text.getFont().getSize());
		setBodyColor(bodyColor);
		setTextColor(textColor);
	}

	@Override
	public void setX(int newX) {
		super.setX(newX);
		fixLocations();
	}

	@Override
	public void setY(int newY) {
		super.setY(newY);
		fixLocations();
	}

	@Tags("Move")
	@Override
	public void move(int dx, int dy) {
		if (dx != 0) {
			setX(getX() + dx);
		}
		if (dy != 0) {
			setY(getY() + dy);
		}
	}
	
	

	private void fixLocations() {
		head.setX(getX() - head.getWidth() / 2 + neck.getWidth() + body.getWidth());
		head.setY(getY() - head.getHeight() + neck.getHeight() + body.getHeight());

		neck.setX(getX() + body.getWidth());
		neck.setY(getY() + body.getHeight());

		arms.setX(getX() + body.getWidth());
		arms.setY(getY() + body.getHeight());

		body.setX(getX());
		body.setY(getY());

		legs.setX(getX());
		legs.setY(getY());

		text.setX(getX() + head.getWidth() / 2 + neck.getWidth() + body.getWidth());
		text.setY(getY() - head.getHeight() + neck.getHeight() + body.getHeight() - text.getFont().getSize());

		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Body Fixed"));
	}

	@Override
	public Color getBodyColor() {
		return bodyColor;
	}

	@Override
	public void setBodyColor(Color newColor) {
		bodyColor = newColor;
		arms.getLeftLine().setColor(bodyColor);
		arms.getRightLine().setColor(bodyColor);
		legs.getLeftLine().setColor(bodyColor);
		legs.getRightLine().setColor(bodyColor);
		body.setColor(bodyColor);
		neck.setColor(bodyColor);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Body Color Changed"));
	}

	@Override
	public Color getTextColor() {
		return textColor;
	}

	@Override
	public void setTextColor(Color newColor) {
		textColor = newColor;
		text.setColor(textColor);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Text Color Changed"));
	}

	@Override
	public void setSizeScale(double newScale) {
		sizeScale = newScale;
		head.setHeight((int) Math.round(head.getHeight() * sizeScale));
		head.setWidth((int) Math.round(head.getWidth() * sizeScale));
		arms.getLeftLine().setRadius(arms.getLeftLine().getRadius() * sizeScale);
		arms.getRightLine().setRadius(arms.getRightLine().getRadius() * sizeScale);
		legs.getLeftLine().setRadius(legs.getLeftLine().getRadius() * sizeScale);
		legs.getRightLine().setRadius(legs.getRightLine().getRadius() * sizeScale);
		neck.setRadius(neck.getRadius() * sizeScale);
		body.setRadius(body.getRadius() * sizeScale);

		fixLocations();
	}

	@Override
	public double getSizeScale() {
		return sizeScale;
	}

	@Override
	public IHead getHead() {
		return head;
	}

	@Override
	public IAngleShape getArms() {
		return arms;
	}

	@Override
	public IAngleShape getLegs() {
		return legs;
	}

	@Override
	public IRotatingLine getBody() {
		return body;
	}

	@Override
	public IRotatingLine getNeck() {
		return neck;
	}

	@Override
	public IStringShape getText() {
		return text;
	}

	@Override
	public void angleNeck(double newAngle) {
		neck.setAngle(newAngle);
		fixLocations();
	}

	@Override
	public void angleBody(double newAngle) {
		body.setAngle(newAngle);
		fixLocations();
	}
	
	@Override
	public void angleRightArm(double newAngle) {
		arms.getRightLine().setAngle(newAngle);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Right Arm Rotated"));
	}

	@Override
	public void angleLeftArm(double newAngle) {
		arms.getLeftLine().setAngle(newAngle);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Left Arm Rotated"));
	}

	@Override
	public void angleRightLeg(double newAngle) {
		legs.getRightLine().setAngle(newAngle);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Right Leg Rotated"));
	}

	@Override
	public void angleLeftLeg(double newAngle) {
		legs.getLeftLine().setAngle(newAngle);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Left Leg Rotated"));
	}

	@Override
	public void rotateRightArm(int steps) {
		arms.getRightLine().rotate(steps);
		//System.out.println("Right");
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Right Arm Rotated"));
	}

	@Override
	public void rotateLeftArm(int steps) {
		arms.getLeftLine().rotate(steps);
		//System.out.println("Left");
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Left Arm Rotated"));
	}

	@Override
	public void rotateRightLeg(int steps) {
		legs.getRightLine().rotate(steps);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Right Leg Rotated"));
	}

	@Override
	public void rotateLeftLeg(int steps) {
		legs.getLeftLine().rotate(steps);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Left Leg Rotated"));
	}

	@Override
	public void rotateNeck(int steps) {
		neck.rotate(steps);
		fixLocations();
	}

	@Override
	public void rotateBody(int steps) {
		body.rotate(steps);
		fixLocations();
	}
	
	@Override
	public void say(String text) {
		this.text.setText(text);
		fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Text Changed"));
	}
	
	@Override
	public void addActionListener(ActionListener listener) {
		ell.add(ActionListener.class, listener);
	}
	
	private void fireActionEvent(ActionEvent e) {
		for(ActionListener listener : ell.getListeners(ActionListener.class)) {
			listener.actionPerformed(e);
		}
	}
}
