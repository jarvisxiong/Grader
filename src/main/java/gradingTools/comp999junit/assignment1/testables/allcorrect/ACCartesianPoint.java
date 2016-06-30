package gradingTools.comp999junit.assignment1.testables.allcorrect;

import util.annotations.Explanation;
import util.annotations.Tags;
import bus.uigen.ObjectEditor;
@Explanation("Uses Cartesian representation.")
//@Tags({"cartesian", "point"})
public class ACCartesianPoint implements ACPoint {	
	protected int x, y;
	public ACCartesianPoint(int theX, int theY) {
		x = theX;
		y = theY;
	}
	public ACCartesianPoint(double theRadius, double theAngle) {
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
//	public void print (String aString, ACPoint aPoint) {
//		System.out.println (aString + aPoint.getX() + " " + aPoint.getY());
//	}
	public void print () {
		System.out.println ("X: " + x + " Y:" + y);
	}
	@Override
	public ACPoint translate (ACPoint aPoint, int anXDelta, int aYDelta) {
		return new ACCartesianPoint (aPoint.getX() + anXDelta, aPoint.getY() + aYDelta);
	}
	public static void main(String args[]) {
		ACPoint point =  new ACCartesianPoint (50, 100);
		ObjectEditor.edit(point);
		point = new ACCartesianPoint(100, Math.PI/4);
		ObjectEditor.edit(point);
//		
	}
}
