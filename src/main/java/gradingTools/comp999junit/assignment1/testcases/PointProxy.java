package gradingTools.comp999junit.assignment1.testcases;

import gradingTools.comp999junit.assignment1.testables.allcorrect.ACorrectPoint;
import gradingTools.comp999junit.assignment1.testables.wrongangle.WrongPoint;

public interface PointProxy {

	void createCartesianPoint(int theX, int theY) throws Exception;
//	public void test(int theX, int theY, double aCorrectRadius,
//			double aCorrectAngle);

	double getRadius() throws Exception;

	double getAngle() throws Exception;
	void print (String aString, Point aPoint);
	Point getPoint();
	Point translate(Point aPoint, int anXDelta, int aYDelta); 

}
