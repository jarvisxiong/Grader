package gradingTools.comp999junit.assignment1.testables.secredit;

import util.annotations.Explanation;
import util.annotations.Tags;
import bus.uigen.ObjectEditor;
@Explanation("Uses Cartesian representation.")
@Tags({"cartesian", "point"})
public class SECreditCartesianPoint implements SEPoint {	
	protected int x, y;
	public SECreditCartesianPoint(int theX, int theY) {
		x = theX;
		y = theY;
	}
	public SECreditCartesianPoint(double theRadius, double theAngle) {
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
	@Override
	public void print (String aString, SEPoint aPoint) {
		System.out.println (aString + aPoint.getX() + " " + aPoint.getY());
	}
	public static void main(String args[]) {
		SEPoint point =  new SECreditCartesianPoint (50, 100);
		ObjectEditor.edit(point);
		point = new SECreditCartesianPoint(100, Math.PI/4);
		ObjectEditor.edit(point);
//		
	}
}
