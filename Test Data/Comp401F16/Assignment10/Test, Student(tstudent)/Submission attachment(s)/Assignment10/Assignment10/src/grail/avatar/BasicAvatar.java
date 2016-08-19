package grail.avatar;

import java.awt.Color;
import java.beans.PropertyChangeListener;

import grail.draw.interfaces.AngleShape;
import grail.draw.interfaces.ImageShape;
import grail.draw.interfaces.RotatingShape;
import grail.draw.interfaces.StringShape;
import grail.draw.objects.BasicAngleShape;
import grail.draw.objects.BasicImageShape;
import grail.draw.objects.BasicStringShape;
import grail.draw.objects.LineRotatingShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Avatar"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Head", "Arms", "Legs", "Body", "X", "Y", "Height","Width","Text", "BasicText", "BodyColor", "TextColor"})
@EditablePropertyNames({"X", "Y", "BasicText", "Text","BodyColor", "TextColor"})
public class BasicAvatar implements Avatar{
	private AngleShape arms, legs;
	private RotatingShape body;
	private ImageShape head;
	private StringShape text;
	private String basicText;
	private Color bodyColor, textColor;
	private int scaleFactor;
	private int x,y;
	
	
	public BasicAvatar(ImageShape head, int xPosStart, int yPosStart) {
		super();
		final int startingScaleFactor = 100;
		this.scaleFactor = startingScaleFactor;
		this.x = xPosStart;
		this.y = yPosStart;
		this.head = head;
		this.text = new BasicStringShape("Default", 0, 0);
		this.basicText = this.text.getText();
		this.body = new LineRotatingShape();
		this.arms = new BasicAngleShape(0, 0);
		this.legs = new BasicAngleShape(0, 0);		
		
		
		connectAvatar(xPosStart, yPosStart);
		final int defaultArmPosition = 8;
		this.arms.rotate(defaultArmPosition);
		this.legs.rotate(-defaultArmPosition);
		setBodyColor(Color.BLACK);
		setTextColor(Color.BLACK);

	}
	
	public BasicAvatar(String headImageString) {
		this(new BasicImageShape(headImageString));
		
	}
	
	public BasicAvatar(ImageShape head) {
		this (head, 0, 0);
	}

	
	public String getBasicText() {
		return this.basicText;
	}

	
	public void setBasicText(String setVal) {
		this.basicText = setVal;
		this.text.setText(setVal);
		
	}

	
	public ImageShape getHead() {
		return head;
	}

	
	public AngleShape getArms() {
		return arms;
	}

	
	public AngleShape getLegs() {
		return legs;
	}

	
	public RotatingShape getBody() {
		return body;
	}

	@Tags({"move"})
	public void move(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
		setLocation(this.x, this.y);
		
	}

	
	public void setX(int xPos) {
		this.x = xPos;
		setLocation(xPos, this.y);
		
		
	}

	
	public void setY(int yPos) {
		this.y = yPos;
		setLocation(this.x, yPos);
		
	}


	@Tags({"scale"})
	public void scale(double scaleValue) {
		this.scaleFactor = (int) (this.scaleFactor * scaleValue);
		connectAvatar(this.x,this.y);
	}

	
	public void setText(StringShape basicText) {
		this.text = basicText;
	}

	
	public StringShape getText() {
		return this.text;
	}
	
	public void setLocation(int x, int y) {
		connectAvatar(x,y);
		
	}
	
	private void connectAvatar(int xPos, int yPos) {
		int singleScaleFactor = this.scaleFactor;
		this.x = xPos;
		this.y = yPos;
		int headWidth = head.getWidth();
		int headHeight = head.getHeight();
		int xBodyPosition = headWidth/2 + xPos;
		int yBodyPosition = headHeight + yPos;
		
		this.head.setX(xPos);
		this.head.setY(yPos);
		
		this.body.setX(xBodyPosition);
		this.body.setY(yBodyPosition);
		this.body.setRadius(singleScaleFactor);
		this.body.setAngle(Math.PI/2);
		
		final double armScaleFactor = .40;
		final double armLengthFactor = .5;
		int armLocation = (int) (headHeight + yPos + singleScaleFactor * (armScaleFactor));
		this.arms.setX(xBodyPosition);
		this.arms.setY(armLocation);
		this.arms.getRightLine().setRadius(singleScaleFactor * armLengthFactor);
		this.arms.getLeftLine().setRadius(singleScaleFactor * armLengthFactor);
		
		final double legScaleFactor = .5;
		int bodyLocation = headHeight + yPos + singleScaleFactor;
		this.legs.setX(xBodyPosition);
		this.legs.setY(bodyLocation);
		this.legs.getRightLine().setRadius(singleScaleFactor * legScaleFactor);
		this.legs.getLeftLine().setRadius(singleScaleFactor * legScaleFactor);

		final int speachDistanceFromMouth = 10;
		this.text.setX(headWidth + xPos + speachDistanceFromMouth);
		this.text.setY(headHeight + yPos);
		
	}
	
	
	public void setBodyColor(Color newBodyColor) {
		this.bodyColor = newBodyColor;
		this.body.setColor(newBodyColor);
		this.arms.setColor(newBodyColor);
		this.legs.setColor(newBodyColor);
		
	}

	
	public void setTextColor(Color newbasicTextColor) {
		this.textColor = newbasicTextColor;
		this.text.setTextColor(newbasicTextColor);
		
	}

	
	public Color getBodyColor() {
		return bodyColor;
	}

	
	public Color getTextColor() {
		return textColor;
	}

	
	public int getHeight() {
		return Math.abs(this.body.getHeight())+ Math.abs(this.head.getHeight()) + Math.abs(this.legs.getRightLine().getHeight());

	}

	
	public int getWidth() {
		return Math.abs(this.legs.getRightLine().getWidth())* 2;
	}
	

	
	public int getX() {
		return this.x;
	}

	
	public int getY() {
		return this.y;
	}
	
	public static void addBodyListener(Avatar avatar, PropertyChangeListener listener) {
		avatar.getArms().addPropertyChangeListener(listener);
		BasicAngleShape.addListenersToSubshapes(avatar.getArms(), listener);
		avatar.getLegs().addPropertyChangeListener(listener);
		BasicAngleShape.addListenersToSubshapes(avatar.getLegs(), listener);
		avatar.getHead().addPropertyChangeListener(listener);
		avatar.getBody().addPropertyChangeListener(listener);
		avatar.getText().addPropertyChangeListener(listener);
	}




}
