package grail.shape;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"Avatar"})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

@PropertyNames({"text","head","arms","waist","legs","avatarLocation"})
@EditablePropertyNames({"text","avatarLocation"})
public class Avatar implements AvatarInterface{
	private StringShape text;
	private ImageShape head;
	private VShapeInterface arms, legs;
	private Line waist;//yao
	Point avatarLocation;//I define this as the upper left corner of the head
	public Avatar(String name, String headfilename,int headx,int heady)
	{
		final int headwidth=40;
		final int headheight=64;
		avatarLocation =new APoint(headx,heady);//
		//the codes below connects all the body parts
		head=new AShapeImage(headfilename,headx,heady,headwidth,headheight);
		final int textHeight=20;
		text=new AStringShape(name,headx+headwidth,heady-textHeight);
		int armX=(int)(headx+headwidth/2.0);
		//int armX=headx;
		final int armY=(int)(heady+(headheight*2/3.0));//left-upper is the original point
		arms=new VShape(armX,armY);
		final int waistHeight=50;
		waist=new ALine(new APoint(armX,armY),0,waistHeight);
		int legY=armY+waistHeight;
		legs=new VShape(armX,legY);
	}
	
	//This method is written to set the other body parts when head (avater location) moves 
	//private void connectBodyParts(int armX,int armY)
	//{
	//}
	
	public Point getAvatarLocation()
	{
		return this.avatarLocation;
	}
	public void setAvatarLocation(Point p)
	{
		this.avatarLocation=p;
	}
	
	
	@Tags("move")
	public void move(int dx,int dy)
	{
		avatarLocation.setX(avatarLocation.getX()+dx);//it seems no use
		avatarLocation.setY(avatarLocation.getY()+dy);
		
		//set position for all body parts
		text.setX(text.getX()+dx);
		text.setY(text.getY()+dy);
		head.setX(head.getX()+dx);
		head.setY(head.getY()+dy);
		//arms.setAngleLocation(arms.getAngleLocation().getX()+dx, arms.getAngleLocation().getY()+dy);
		//arms.getLeftLine().getLocation().setX(newX);
		//legs.setAngleLocation(legs.getAngleLocation().getX()+dx, legs.getAngleLocation().getY()+dy);
		//waist.setLocation(new APoint(waist.getLocation().getX()+dx,waist.getLocation().getY()+dy));
		arms.getLeftLine().setX(arms.getLeftLine().getX()+dx);
		arms.getLeftLine().setY(arms.getLeftLine().getY()+dy);
		arms.getRightLine().setX(arms.getRightLine().getX()+dx);
		arms.getRightLine().setY(arms.getRightLine().getY()+dy);
		legs.getLeftLine().setX(legs.getLeftLine().getX()+dx);
		legs.getLeftLine().setY(legs.getLeftLine().getY()+dy);
		legs.getRightLine().setX(legs.getRightLine().getX()+dx);
		legs.getRightLine().setY(legs.getRightLine().getY()+dy);
		waist.setX(this.waist.getX()+dx);
		waist.setY(this.waist.getY()+dy);
	}
	

	public StringShape getText()
	{
		return this.text;
	}
	
	public void setText(StringShape ss)
	{
		this.text=ss;
	}
	
	public ImageShape getHead()
	{
		return this.head;
	}
	public VShapeInterface getArms()
	{
		return this.arms;
	}
	public VShapeInterface getLegs()
	{
		return this.legs;
	}
	public Line getWaist()
	{
		return this.waist;
	}
	public void scale(int k)
	{
		//finish arms
		this.arms.getLeftLine().setHeight(this.arms.getLeftLine().getHeight()*k);
		this.arms.getLeftLine().setWidth(this.arms.getLeftLine().getWidth()*k);
		this.arms.getRightLine().setHeight(this.arms.getRightLine().getHeight()*k);
		this.arms.getRightLine().setWidth(this.arms.getRightLine().getWidth()*k);

		
		//finish legs
		int oldY=this.legs.getAngleLocation().getY();
		int oldHeight=this.waist.getHeight();
		int newLegY=this.legs.getAngleLocation().getY()+this.waist.getHeight()*(k-1);
		//this.legs.setAngleLocation(this.legs.getAngleLocation().getX(),newLegY );
		this.legs.getLeftLine().setY(newLegY);
		this.legs.getRightLine().setY(newLegY);
		this.legs.getLeftLine().setHeight(this.legs.getLeftLine().getHeight()*k);
		this.legs.getLeftLine().setWidth(this.legs.getLeftLine().getWidth()*k);
		this.legs.getRightLine().setHeight(this.legs.getRightLine().getHeight()*k);
		this.legs.getRightLine().setWidth(this.legs.getRightLine().getWidth()*k);
		
		//body
				this.waist.setHeight(this.waist.getHeight()*k);
	}
}
