package gradingTools.comp999junit.assignment1.testcases;

import gradingTools.comp999junit.assignment1.testables.allcorrect.ACPoint;
import gradingTools.comp999junit.assignment1.testables.wrongangle.WAPoint;

public interface PointProxy {

	void createCartesianPoint(int theX, int theY) throws Exception;
//	public void test(int theX, int theY, double aCorrectRadius,
//			double aCorrectAngle);

	double getRadius() throws Exception;

	double getAngle() throws Exception;
//	void print (String aString, Point aPoint);
	void print ();

	Point getPoint();
	Point translate(Point aPoint, int anXDelta, int aYDelta); 

}
