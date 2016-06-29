package gradingTools.comp999junit.assignment1.testables.allcorrect;

import util.annotations.Explanation;
import util.annotations.Tags;
import bus.uigen.ObjectEditor;
@Explanation("Uses Cartesian representation.")
@Tags({"cartesian", "point"})
public class ACorrectCartesianPoint implements ACorrectPoint {	
	protected int x, y;
	public ACorrectCartesianPoint(int theX, int theY) {
		x = theX;
		y = theY;
	}
	public ACorrectCartesianPoint(double theRadius, double theAngle) {
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
	public void print (String aString, ACorrectPoint aPoint) {
		System.out.println (aString + aPoint.getX() + " " + aPoint.getY());
	}
	@Override
	public ACorrectPoint translate (ACorrectPoint aPoint, int anXDelta, int aYDelta) {
		return new ACorrectCartesianPoint (aPoint.getX() + anXDelta, aPoint.getY() + aYDelta);
	}
	public static void main(String args[]) {
		ACorrectPoint point =  new ACorrectCartesianPoint (50, 100);
		ObjectEditor.edit(point);
		point = new ACorrectCartesianPoint(100, Math.PI/4);
		ObjectEditor.edit(point);
//		
	}
}
