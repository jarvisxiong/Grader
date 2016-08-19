package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags("RotatingLine")
@PropertyNames({"X", "Y", "Location", "Height", "Width", "Radius", "Angle", "AdjLocation"}) 
@EditablePropertyNames({"X", "Y", "Location", "Height", "Width", "Radius", "Angle", "AdjLocation"})

public class ARotatingLine extends AShape implements RotatingLine {
	
	private double radius, angle;
	final private double incrAngle = Math.PI/360;
	
	public ARotatingLine() {super();}
	public ARotatingLine(double initRadius, double initAngle){
		radius = initRadius;
		angle = initAngle; 
		this.setWidth((int) (radius*Math.cos(angle)));
		this.setHeight((int) (radius*Math.sin(angle)));
	}
	public ARotatingLine(AdvLocatable initLocation, double initRadius, double initAngle) {
		this.setX(initLocation.getX());
		this.setY(initLocation.getY());
		radius = initRadius;
		angle = initAngle;
		this.setWidth((int) (radius*Math.cos(angle)));
		this.setHeight((int) (radius*Math.sin(angle)));
	}
	public double getRadius() { return radius; }
	public void setRadius(double newRadius) { 
		radius = newRadius; 
		this.setWidth((int) (radius*Math.cos(angle)));
		this.setHeight((int) (radius*Math.sin(angle)));
		}
	public double getAngle() { return angle; }
	public void setAngle(double newAngle) { 
		angle = newAngle; 
		this.setWidth((int) (radius*Math.cos(angle)));
		this.setHeight((int) (radius*Math.sin(angle)));
		}
	public void rotate(int units) {
		 angle = angle + (incrAngle)*units;
		 this.setWidth((int) (radius*Math.cos(angle)));
		 this.setHeight((int) (radius*Math.sin(angle)));
	}

}
