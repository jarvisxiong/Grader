package gradingTools.comp999junit.assignment1.testables.secredit;

import util.annotations.Explanation;
import util.annotations.Tags;
@Explanation("Uses Cartesian representation.")
@Tags({"cartesian", "point"})
public class SECartesianPoint implements SEPoint {	
	protected int x, y;
	public SECartesianPoint(int theX, int theY) {
		x = theX;
		y = theY;
	}
	public SECartesianPoint(double theRadius, double theAngle) {
		x =  (int) (theRadius*Math.cos(theAngle));
		y = (int) (theRadius*Math.sin(theAngle));
	}
	public int getX() { return x; }
	public int getY() { return y; } 	
	public double getAngle() { 
		return Math.atan2(y, x); 
//		return 0;
	}
	@Tags({"radius", "getter"})	
	public double getRadius() { 
		return Math.sqrt(x*x + y*y); 
//		return 0;
	}
//	@Override
//	public void print (String aString, SEPoint aPoint) {
//		System.out.println (aString + aPoint.getX() + " " + aPoint.getY());
//	}
	public void print () {
		System.out.println ("[" + x + "," + y + "]");
	}
	public SEPoint translate (SEPoint aPoint, int anXDelta, int aYDelta) {
		return new SECartesianPoint (aPoint.getX() + anXDelta, aPoint.getY() + aYDelta);
	}
	
}
