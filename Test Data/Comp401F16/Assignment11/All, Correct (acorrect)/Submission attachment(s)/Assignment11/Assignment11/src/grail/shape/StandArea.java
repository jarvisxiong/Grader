package grail.shape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
//@StructurePattern("Label Pattern")
@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class StandArea extends ABoundedShape implements Shape{
	//int x,y,width,height;
	
	public StandArea(int x,int y)
	{
		super(x,y,100,100);

	}
	
//    public void setX(int x)
//    {
//    	this.x=x;
//    }
//    public void setY(int y)
//    {
//    	this.y=y;
//    }
//    public int getX()
//    {
//    	return this.x;
//    }
//    public int getY()
//    {
//    	return this.y;
//    }
//    public int getWidth()
//    {	
//    	return width;
//    }
//    public int getHeight()
//    {
//    	return this.height;
//    }
//    
    @Tags({"move"})
    public void move(int dx,int dy)
    {
    	this.x=this.x+dx;
    	this.y=this.y+dy;
    }
    public void changeRotateAngle (int units)
    {
    	
    }
    public void setRadius(double r)
	{
		
	}
    public void setAngle(double a)
    {
    	
    }
}
