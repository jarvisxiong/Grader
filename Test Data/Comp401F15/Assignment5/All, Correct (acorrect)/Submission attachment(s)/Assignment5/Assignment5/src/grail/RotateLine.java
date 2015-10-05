package grail;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags({"RotatingLine"})

//this is for assignment4

public class RotateLine implements Shape{
	//width,height is the property of bounding box
    //x, y is the upper-left corner point of bounding box of the shape
    int x, y, width, height;
    double radius, angle;
    private final double degreePerUnit=Math.PI/32;
   // Point lrPoint;//for lower-right corner point
    
    //angle is in radian measurement, radian, angle
    public RotateLine(double r, double a)
    {
    	radius=r;
    	angle=a;
    	height=(int)(r*Math.sin(a));
    	width=(int)(r*Math.cos(a));
    	//lrPoint=new APolarPoint(r,a);
    	//lrPoint.setX(x);
    }
    
    
    public void setRadius(double r)
    {
    	radius=r;
    	height=(int)(r*Math.sin(this.angle));
    	width=(int)(r*Math.cos(this.angle));

    }
    
    public void setAngle(double a)
    {
     	angle=a;
    	this.height=(int)(this.radius*Math.sin(this.angle));
    	this.width=(int)(this.radius*Math.cos(this.angle));
    	//System.out.println("height: "+height+"width: "+width);
    }
    
    public void setX(int x)
    {
    	this.x=x;
    }
    
    public void setY(int y)
    {
    	this.y=y;
    }
    public int getX()
    {
    	return x;
    }
    public int getY()
    {
    	return y;
    }
    public int getWidth()
    {
    	return width;
    }
    
    public int getHeight()
    {
    	return height;
    }
    

    @Tags({"rotate"})
    public void changeRotateAngle (int units) 
    {
    	double d=units*degreePerUnit;
    	this.setAngle(d);
    }
    
}
