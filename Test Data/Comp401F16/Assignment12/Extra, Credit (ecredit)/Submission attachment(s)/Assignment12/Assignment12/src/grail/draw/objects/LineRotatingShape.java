package grail.draw.objects;

import java.beans.PropertyChangeEvent;

import grail.draw.interfaces.RotatingShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"RotatingLine"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width","Angle", "Radius" ,"Color", "Filled"})
@EditablePropertyNames({"X", "Y","Angle", "Radius","Color", "Filled"})
public class LineRotatingShape extends BasicColoredShape implements RotatingShape{
	private double radius, angle;

	public LineRotatingShape() {
	this(0,0,0,0);
	}
	
	public LineRotatingShape(int x, int y, double radius, double angle) {
		super();
		this.x = x;
		this.y = y;
		setRadius(radius);
		setAngle(angle);
	}


	
	public void setRadius(double val) {
		int oldHeight = this.height;
		int oldWidth = this.width;
		this.radius = val;
		double tempHeight =  this.radius * Math.sin(this.angle);
		this.height = (int) tempHeight;
		double tempWidth = this.radius * Math.cos(this.angle);
		this.width = (int) tempWidth;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this,"Height", oldHeight, this.height));
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this,"Width", oldWidth, this.width));
		}
		
	}

	
	public void setAngle(double val) {
		int oldHeight = this.height;
		int oldWidth = this.width;
		this.angle = val;
		double tempHeight =  this.radius * Math.sin(this.angle);
		this.height = (int) tempHeight;
		double tempWidth = this.radius * Math.cos(this.angle);
		this.width = (int) tempWidth;
		
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this,"Height", oldHeight, this.height));
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this,"Width", oldWidth, this.width));
		}
		
	}

	@Tags({"rotate"})
	public void rotate(int units) {
		final double secondsInAMinute = 60;
		final double fullRotation = Math.PI * 2;
		final double clockUnit = fullRotation/secondsInAMinute;
		double distanceToRotate = units * clockUnit;
		double angleToSet = this.angle + distanceToRotate;
		setAngle(angleToSet);
	}

	
	public double getRadius() {
		return this.radius;
	}
	
	public double getAngle() {
		return this.angle;
	}

}
