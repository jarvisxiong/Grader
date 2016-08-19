package grail.draw.objects;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.draw.interfaces.AngleShape;
import grail.draw.interfaces.RotatingShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Angle"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width", "Radius", "Angle", "LeftLine", "RightLine" , "Color"})
@EditablePropertyNames({"X", "Y", "Height", "Width", "Radius", "Angle", "Color"})
public class BasicAngleShape extends BasicMutableShape implements AngleShape {
	private RotatingShape leftLine, rightLine;
	private double radius, angle;
	private Color color;
	
	public BasicAngleShape() {
		this(0,0);
	}
	
	public BasicAngleShape(int initialX, int initialY) {
		super();
		final double startingRadius = 50;
		final double leftStartingAngle = Math.PI;
		final double rightStartingAngle = 0;
		
		x = initialX;
		y = initialY;
		leftLine = new LineRotatingShape(x, y, startingRadius, leftStartingAngle);
		rightLine = new LineRotatingShape(x, y, startingRadius, rightStartingAngle);
		this.height = leftLine.getHeight();
		this.width = rightLine.getWidth() + leftLine.getWidth();
		setRadius(startingRadius);
		setAngle(rightStartingAngle);
		setColor(Color.BLACK);
	}


	
	public void setX(int x) {
		super.setX(x);
		leftLine.setX(this.x);
		rightLine.setX(this.x);
	}


	
	public void setY(int y) {
		super.setY(y);
		leftLine.setY(this.y);
		rightLine.setY(this.y);

	}
	
	public RotatingShape getLeftLine() {
		return leftLine;
	}

	
	public RotatingShape getRightLine() {
		return rightLine;
	}

	
	@Tags({"move"})
	public void move(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
		setX(this.x);
		setY(this.y);
	}
	
	public void setRadius(double radius) {
		double oldRadius = this.radius;
		this.radius = radius;
		leftLine.setRadius(radius);
		rightLine.setRadius(radius);
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Radius", oldRadius, this.radius));
		}
	}
	
	public void setAngle(double angle) {
		double oldAngle = this.angle;
		this.angle = angle;
		double halfAngle = angle/2;
		leftLine.setAngle(halfAngle + Math.PI);
		rightLine.setAngle(-halfAngle);
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Angle", oldAngle, this.angle));
		}
	}
	
	public void rotate(int units) {
		int unitToMoveRight = -units;
		int unitsToMoveLeft = units;
		rightLine.rotate(unitToMoveRight);
		leftLine.rotate(unitsToMoveLeft);
	}

	
	public void setHeight(int height) {
		int oldHeight = this.height;
		int halfWidth = this.width/2;
		double radius = (double) Math.sqrt((halfWidth * halfWidth) + (height * height));
		this.leftLine.setRadius(radius);
		this.rightLine.setRadius(radius);
		double angle = (double) Math.sinh(height/radius);
		this.rightLine.setAngle(-angle);
		this.leftLine.setAngle(Math.PI + angle);
		this.height = (this.leftLine.getHeight() >= this.rightLine.getHeight())? Math.abs(leftLine.getHeight()):Math.abs(this.rightLine.getHeight());
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldHeight, this.height));
		}
		
	}

	
	public void setWidth(int width) {
		int oldWidth = this.width;
		this.width = width;
		int halfWidth = this.width/2;
		double radius = (double) Math.sqrt((halfWidth * halfWidth) + (height * height));
		this.leftLine.setRadius(radius);
		this.rightLine.setRadius(radius);
		double angle = (double) Math.sinh(height/radius);
		this.rightLine.setAngle(-angle);
		this.leftLine.setAngle(Math.PI + angle);
		this.width = Math.abs(this.leftLine.getWidth()) + Math.abs(this.rightLine.getWidth());
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldWidth, this.width));
		}
		
	}
	
	@Override
	public int getWidth() {
		return Math.abs(this.leftLine.getWidth()) + Math.abs(this.rightLine.getWidth());
	}
	
	@Override
	public int getHeight() {
		return (this.leftLine.getHeight() >= this.rightLine.getHeight())? Math.abs(leftLine.getHeight()):Math.abs(this.rightLine.getHeight());
	}
	
	public double getRadius() {
		return radius;
	}

	
	public double getAngle() {
		return angle;
	}

	
	public Color getColor() {
		return color;
	}

	
	public void setColor(Color newColor) {
		Color oldColor = this.color;
		this.color = newColor;
		this.leftLine.setColor(newColor);
		this.rightLine.setColor(newColor);
		if (this.propertyListenerSupport != null){
		this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Color", oldColor, this.color));
		}
		
	}

	
	public void scale(double scaleFactor) {
		this.rightLine.setRadius(this.rightLine.getRadius() * scaleFactor);
		this.leftLine.setRadius(this.leftLine.getRadius() * scaleFactor);
		
	}
	
	public static void addListenersToSubshapes(AngleShape shape, PropertyChangeListener listener) {
		shape.getLeftLine().addPropertyChangeListener(listener);
		shape.getRightLine().addPropertyChangeListener(listener);
	}

}
