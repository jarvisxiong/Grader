package mp.Scene;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"RotatingLine"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"X","Y","Width","Height","Radius"})
public class ALine extends ABoundedShape implements Line{
	
	double radius, angle;
	final double oneeighty=180.0;
	final double fortyfive=45.0;
	public ALine (int intX,int intY,double theta, double r) {
		x=intX;
		y=intY;
		radius=r;
		angle=theta/oneeighty*Math.PI;
		width=(int)(radius*Math.cos(angle));
		height=(int)(radius*Math.sin(angle));
	}
//	public void setWidth(){
//		int oldVal=width;
//		width=(int)(radius*Math.cos(angle));
//		int newVal=width;
//		aListener.notifyAllListeners(new PropertyChangeEvent(this,"Width",oldVal,newVal));
//	}
//	public void setHeight(){
//		int oldVal=height;
//		 height=(int)(radius*Math.sin(angle));
//		 int newVal=height;
//		 aListener.notifyAllListeners(new PropertyChangeEvent(this,"Height",oldVal,newVal));
//	}
	public void setRadius(double a){
		radius=a;
	}
	public double getRadius(){
		return radius;
	}
	public void scale(double a){
		width*=a;
		height*=a;
		radius*=a;
	}
	
	
//	double beta=fortyfive/oneeighty*Math.PI;
	@Tags({"rotate"})
	public void rotate(int a){
		angle-=a/oneeighty*Math.PI;
		width=  (int) (radius*Math.cos(angle));
		height= (int) (radius*Math.sin(angle));	
		
	}
	
}
