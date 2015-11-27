package grail.shape;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"Gorge"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Gorge implements ParallelLinesInterface{
	private Line firstLine,secondLine;
	Point firstLineLocation,secondLineLocation;
	public Gorge()
	{
	}
	
	
	public Gorge(int leftX, int leftY,int rightX,int rightY)
	{
		firstLineLocation=new APoint(leftX,leftY);
		secondLineLocation=new APoint(rightX,rightY);
		final int width=0,height=500;
		//Point firstLineLocation=new APoint(-width,height);
		firstLine=new ALine(firstLineLocation,-width,height);
		secondLine=new ALine(secondLineLocation,width,height);
	}
	
	public void setFirstLineLocation(int x, int y)
	{
		firstLineLocation.setX(x);
		firstLineLocation.setY(y);
		firstLine.setX(x);
		firstLine.setY(y);
		//firstLine.setLocation(firstLineLocation);
	}
	
	public void setSecondLineLocation(int x, int y)
	{
		secondLineLocation.setX(x);
		secondLineLocation.setY(y);
		secondLine.setX(x);
		secondLine.setY(y);
		//secondLine.setLocation(secondLineLocation);
	}
	
	
	
	public Point getFirstLineLocation()
	{
		return this.firstLineLocation;
	}
	
	public Point getSecondLineLocation()
	{
		return this.secondLineLocation;
	}
	
	public Line getFirstLine()
	{
		return firstLine;
	}
	
	public Line getSecondLine()
	{
		return secondLine;
	}
	@Tags({"move"})
	public void move(int dx,int dy)
	{
		firstLineLocation.setX(this.firstLineLocation.getX()+dx);
		firstLineLocation.setY(this.firstLineLocation.getY()+dy);

		this.firstLine.setX(this.firstLine.getX()+dx);
		this.firstLine.setY(this.firstLine.getY()+dy);
		//this.firstLine.setLocation(firstLineLocation);
		
		secondLineLocation.setX(this.secondLineLocation.getX()+dx);
		secondLineLocation.setY(this.secondLineLocation.getY()+dy);
		
		this.secondLine.setX(this.secondLine.getX()+dx);
		this.secondLine.setY(this.secondLine.getY()+dy);
		//this.secondLine.setLocation(secondLineLocation);
	}
	/*
	public void moveAngle(int x, int y)
	{
		angleLocation.setX(angleLocation.getX()+x);
		angleLocation.setY(angleLocation.getY()+y);
	}*/
	
}
