package gradingTools.comp999junit.assignment1.testables.wrongangle;

import gradingTools.comp999junit.assignment1.testables.allcorrect.ACCartesianPoint;
import gradingTools.comp999junit.assignment1.testables.allcorrect.APoint;
import util.annotations.Explanation;
import util.annotations.Tags;
import bus.uigen.ObjectEditor;
@Explanation("Uses Cartesian representation.")
//@Tags({"cartesian", "point"})
public class WACartesianPoint implements WAPoint {	
	protected int x, y;
	public WACartesianPoint(int theX, int theY) {
		x = theX;
		y = theY;
	}
	
	public int getX() { return x; }
	public int getY() { return y; } 	
	@Tags({"angle", "getter"})
	public double getAngle() { 
//		return Math.atan2(y, x); 
		return 0;
	}
	@Tags({"radius", "getter"})	
	public double getRadius() { 
		return Math.sqrt(x*x + y*y); 
//		return 0;
	}	
	@Override
	public void print (WAPoint aPoint, String aString) {
		System.out.println (aString + aPoint.getX() + " " + aPoint.getY());
	}
	@Override
	public WAPoint translate (int anXDelta, int aYDelta, WAPoint aPoint) {
		return new WACartesianPoint(aPoint.getX() + anXDelta, aPoint.getY() + aYDelta);
	}
	
}
