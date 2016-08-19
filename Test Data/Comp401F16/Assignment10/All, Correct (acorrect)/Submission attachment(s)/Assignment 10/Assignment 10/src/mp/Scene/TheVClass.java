package mp.Scene;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"Angle"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"rightline","leftline"})

public class TheVClass implements VClass{
	int x,y;
	double angle,radius;
	Line rightLine, leftLine;
	final double fortyfive=45.0;
	final double forty=40.0;
	final double ninety=90.0;
	
	//static PropertyListenerSupport ll= new APropertyListenerSupport();
	
	public TheVClass(int iniX, int iniY){
	angle=fortyfive;
	radius=forty;
	x= iniX;
	y= iniY;
	rightLine= new ALine(x,y,angle,radius);
	leftLine= new ALine(x,y,ninety+angle,radius);
	}
	
	public Line getRightLine(){
		return rightLine;
	}
	public Line getLeftLine(){
		return leftLine;
	}

	@Tags({"move"})
	public void move(int deltaX, int deltaY){
//		int oldX=x;
//		int oldY=y;
		x+=deltaX;
		y+=deltaY;
//		ll.notifyAllListeners(new PropertyChangeEvent(this, "X",
//				oldX, x));
//		ll.notifyAllListeners(new PropertyChangeEvent(this, "Y",
//				oldX, x));
	}
	public static void addPropretyChangeListener(VClass vv,PropertyChangeListener arg0) {
		vv.getLeftLine().addPropertyChangeListener(arg0);
		vv.getRightLine().addPropertyChangeListener(arg0);
	}
}
