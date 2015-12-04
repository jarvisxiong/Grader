package mp.shapes;

import java.awt.Color;
import java.beans.PropertyChangeEvent;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"RotatingLine"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
public class ALineShape extends ABoundedShape implements LineShape{
	private double radius;
	private double angle;
	private Color color;
	
	public ALineShape(double radius, double angle){
		setRadius(radius);
		setAngle(angle);
		rotate(0);
	}
	
	@Override
	public void setAngle(double angle) {
		this.angle = angle;
		if(angle > 2*Math.PI){
			this.angle = angle - 2*Math.PI;
		}
	}

	@Override
	public double getRadius(){
		return radius;
	}
	
	@Override
	public void setRadius(double radius) {
		this.radius = radius;
		rotate(0);
	}

	@Tags({"rotate"})
	@Override
	public void rotate(double rotateAmount) {
		int oldWidth = this.width;
		int oldHeight = this.height;
		
		setAngle(angle + rotateAmount);
		width = (int) (radius*Math.sin(angle));
		height = (int) (radius*Math.cos(angle));
		properties.notifyAllListeners(new PropertyChangeEvent(this, "Width", oldWidth, width));
		properties.notifyAllListeners(new PropertyChangeEvent(this, "Height", oldHeight, height));
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
}
