package mp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.BoundedShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"RotatingLine"})
@PropertyNames({"x","y","width","height","radius", "angle"})
@EditablePropertyNames({"x","y","radius","angle","height","width"}) 

public class ALine extends BoundedShape implements Line, PropertyListenerRegisterer{
	double radius, angle, plus;
	
	// Constructors -------------------------------------------------------------------------------
	public ALine(int initX, int initY, int initHeight, int initWidth){
		x = initX;
		y = initY;
		height = initHeight - initY;
		width = initWidth - initX;
		radius = Math.sqrt(width*width + height*height);
		angle = Math.atan((double) height/width);
		}
	
	public ALine(double theRadius, double theAngle){
		radius = theRadius;
		angle = theAngle;
	}
	
	//Getters-----------------------------------------------------------------------------------------------------------------------------------------
	public double getRadius(){ return radius;}
	public double getAngle(){ return angle;}
	
	// Setters----------------------------------------------------------------------------------------------------------------------------------------
	public void setRadius(double newRadius){
		double oldRadius = radius;
		radius = newRadius;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Radius", oldRadius, newRadius));
	}
	public void setAngle(double newAngleUnit){
		plus = newAngleUnit*(Math.PI/32);
		double oldAngle = angle;
		angle += plus;
		double newAngle = angle;
		width = (int) Math.ceil(Math.sqrt(((radius*radius)*(Math.cos(angle)))));
		height = (int) Math.ceil(Math.sqrt((radius*radius)-(width*width)));
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Angle", oldAngle, newAngle));
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);
	}
	
}

