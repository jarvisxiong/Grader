package grail;

import util.annotations.Tags;
@Tags({"Angle"})

public class VShape implements VShapeInterface{
	private Line leftLine,rightLine;
	private Point angleLocation;//this is defined to be the intersection point of two lines
	public VShape()
	{
	}
	
	public VShape(int x, int y)
	{
		angleLocation=new APoint(x,y);
		final int width=50,height=50;
		//Point leftPoint=new APoint(-width,height);
		leftLine=new ALine(angleLocation,-width,height);
		rightLine=new ALine(angleLocation,width,height);
	}
	
	public void setAngleLocation(int x, int y)
	{
		angleLocation.setX(x);
		angleLocation.setY(y);
	}
	
	public void setAngleLocation(Point p)
	{
		angleLocation=p;
	}
	
	
	public Point getAngleLocation()
	{
		return this.angleLocation;
	}
	public Line getLeftLine()
	{
		return leftLine;
	}
	
	public Line getRightLine()
	{
		return rightLine;
	}
	
	public void moveAngle(int x, int y)
	{
		angleLocation.setX(angleLocation.getX()+x);
		angleLocation.setY(angleLocation.getY()+y);
	}
}
