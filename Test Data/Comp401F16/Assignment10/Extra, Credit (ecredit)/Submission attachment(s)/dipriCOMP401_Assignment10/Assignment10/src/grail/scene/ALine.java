package grail.scene;

import java.beans.PropertyChangeEvent;

import grail.Line;
import grail.BoundedShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags({ "Line" })
@PropertyNames({ "x", "y","width","height", "radius", "angle"})
@EditablePropertyNames({ "x", "y", "radius", "angle"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
public class ALine extends ABoundedShape implements Line {
	private double radius;
	private double angle;
	final static int RADIAN_MODIFIER = 64;

	public ALine(double radius, double angle) {
		this.radius = radius;
		this.angle = angle;
		width = (int) (radius * Math.sin(angle));
		height = (int) (radius * Math.cos(angle));
	}
	
	public double getRadius() {
		return radius;
	}

	public double getAngle() {
		return angle;
	}

	public void setRadius(double aDouble) {
		double oldVal = radius;
		radius = aDouble;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Radius", oldVal,
                radius));
		
	}

	public void setAngle(double aDouble) {
		double oldVal = angle;
		int oldValue = width;
		angle = aDouble;
		width = (int) (radius * Math.sin(angle));
		height = (int) (radius * Math.cos(angle));

		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldValue,
                width));
		oldValue = height;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldValue,
                height));

	}

	@Tags({ "rotate" })
	public void rotate(int anInt) {
		double oldVal = angle;
		int oldValue = width;
		int oldValueH = height;
		angle = anInt * Math.PI / RADIAN_MODIFIER;
		width = (int) (radius * Math.sin(angle));
		height = (int) (radius * Math.cos(angle));
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldValue,
                width));
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldValueH,
                height));
	}

}
