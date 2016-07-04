package gradingTools.comp999junit.assignment1.testables.missingcode;

import util.annotations.Explanation;
@Explanation("Uses Cartesian representation.")
//@Tags({"cartesian", "point"})
public class MCCartesianPoint
	{	
	protected int x, y;
	public MCCartesianPoint(int theX, int theY) {
		x = theX;
		y = theY;
	}
	
	public int getX() { return x; }
	public int getY() { return y; } 	
	public double getAngle() { 
//		return Math.atan2(y, x); 
		return 0;
	}
	public double getRadius() { 
		return Math.sqrt(x*x + y*y); 
//		return 0;
	}	
//	@Override
//	public void print (WAPoint aPoint, String aString) {
//		System.out.println (aString + aPoint.getX() + " " + aPoint.getY());
//	}
//	public void print () {
//		System.out.println ("X: " + x + " Y:" + y);
//	}
	// cannot make argumenyt WAPoint!
	// changed the name from translate to move, changed the order of parameters
	public MCCartesianPoint move (int anXDelta, int aYDelta, MCCartesianPoint aPoint) {
		return new MCCartesianPoint(aPoint.getX() + anXDelta, aPoint.getY() + aYDelta);
	}
	
}
