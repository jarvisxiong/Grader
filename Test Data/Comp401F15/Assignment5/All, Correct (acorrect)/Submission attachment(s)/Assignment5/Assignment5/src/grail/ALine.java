package grail;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.LINE_PATTERN) 
//@Tags({"Angle"})

//we need a rotateline method here
public class ALine implements Line{
    int width, height;
    Point location;
    double radius, angle;
    private final double degreePerUnit=Math.PI/32;
    
    public ALine (Point initLocation, int initWidth, int initHeight) {
    	location = initLocation;
        width = initWidth;
        height = initHeight;    
        this.radius=Math.sqrt(width*width+height*height);
        this.angle=Math.atan2(initHeight, initWidth);
    }
    
    public Point getLocation(){return location;}
    public void setLocation(Point newLocation){location = newLocation;}
    public int getWidth() {return width;}
    public void setWidth(int newVal) {width = newVal;}
    public int getHeight() {return height;}
    public void setHeight(int newHeight) {height = newHeight;}
    
    //this may need modification
    @Tags({"rotate"})
    public void rotate(int units)
    {
    	double d=this.angle +units*degreePerUnit;
    	this.height=(int)(this.radius*Math.sin(d));
    	this.width=(int)(this.radius*Math.cos(d));
    }
}
