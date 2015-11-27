package grail.shape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.LINE_PATTERN) 
@Tags({"ALine"})

//we need a rotateline method here
@PropertyNames({"x","y","width","height"})
@EditablePropertyNames({"x","y","width","height"})
public class ALine extends APoint implements Line{
    int width, height;
    //Point location;
    double radius, angle;
    final int n=32;
    private final double degreePerUnit=Math.PI/n;
	//PropertyListenerSupport propertySupport = new APropertyListenerSupport();

	
    public ALine (Point initLocation, int initWidth, int initHeight) {
    	super(initLocation.getX(),initLocation.getY());
    	//location = initLocation;
        width = initWidth;
        height = initHeight;    
        this.radius=Math.sqrt(width*width+height*height);
        this.angle=Math.atan2(initHeight, initWidth);
    }
    
   // public Point getLocation(){return location;}
    //public void setLocation(Point newLocation){location = newLocation;}
    public int getWidth() {return width;}
    //public void setWidth(int newVal) {width = newVal;}
    public int getHeight() {return height;}
    //public void setHeight(int newHeight) {height = newHeight;}
//    

    public void setWidth(int w)
    {
    	int oldVal=this.width;
    	this.width=w;
    	propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "width",
				oldVal, w));
    }
    public void setHeight(int h)
    {
    	int oldVal=this.height;
    	this.height=h;
    	propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "height",
				oldVal, h));
    }
	
    
    //this may need modification
    @Tags({"rotate"})
    public void rotate(int units)
    {
    	double d=this.angle +units*degreePerUnit;
    	int oldHeight=this.height;
    	int oldWidth=this.width;
    	this.height=(int)(this.radius*Math.sin(d));
    	this.width=(int)(this.radius*Math.cos(d));
    	propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Height",
				oldHeight, this.height));
    	propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Width",
				oldWidth, this.width));
    }
    public int listenerCount() {
		return propertySupport.size();
	}
    
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);
	}
	
//	public double getRadius()
//	{
//		return this.radius;
//	}
//	public double getAngle()
//	{
//		return this.angle;
//	}
}
