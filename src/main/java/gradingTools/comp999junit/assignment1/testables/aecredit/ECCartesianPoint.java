package gradingTools.comp999junit.assignment1.testables.aecredit;

import util.annotations.Explanation;
import util.annotations.Tags;
@Explanation("Uses Cartesian representation.")
@Tags({"cartesian", "point"})
public class ECCartesianPoint implements ECPoint {	
	protected int x, y;
	public ECCartesianPoint(int theX, int theY) {
		x = theX;
		y = theY;
	}
	public ECCartesianPoint(double theRadius, double theAngle) {
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
//	public void print (String aString, ECPoint aPoint) {
//		System.out.println (aString + aPoint.getX() + " " + aPoint.getY());
//	}
	@Override
	public void print () {
		System.out.println ("(" + x + "," + y + ")");
	}
	public ECPoint translate (ECPoint aPoint, int anXDelta, int aYDelta) {
		return new ECCartesianPoint (aPoint.getX() + anXDelta, aPoint.getY() + aYDelta);
	}
	
}
