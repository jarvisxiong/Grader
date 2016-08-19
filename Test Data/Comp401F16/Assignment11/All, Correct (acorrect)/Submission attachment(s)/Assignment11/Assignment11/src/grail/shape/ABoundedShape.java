package grail.shape;

import java.beans.PropertyChangeEvent;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"BoundedShape"})
@PropertyNames({"x","y","width","height"})
@EditablePropertyNames({"x","y","width","height"})
public class ABoundedShape extends APoint implements BoundedShape{
	protected int x, y, width, height;
	
	public ABoundedShape(int x, int y,int width, int height)
	{  
		super(x,y);
		this.width=width;
		this.height=height;
	}
	
//	public void setX(int x)
//	{
//		this.x=x;
//	}
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
    public int getWidth()
    {
    	return this.width;
    }
    public int getHeight()
    {
    	return this.height;
    }
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
	
}
