package gradingTools.comp999junit.assignment1.testcases.multi;

import gradingTools.comp999junit.assignment1.testables.allcorrect.ACPoint;

public interface MultiPointProxy {

	void createCartesianPoint(int theX, int theY) throws Exception;
//	public void test(int theX, int theY, double aCorrectRadius,
//			double aCorrectAngle);

	double getRadius() throws Exception;

	double getAngle() throws Exception;
//	void print (String aString, Point aPoint);
	void print ();

	MultiPoint getPoint();
	MultiPoint translate(MultiPoint aPoint, int anXDelta, int aYDelta); 

}
