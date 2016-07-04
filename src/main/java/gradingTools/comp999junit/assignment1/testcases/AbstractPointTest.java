package gradingTools.comp999junit.assignment1.testcases;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProjectIntrospection;

import org.junit.Assert;

public abstract class AbstractPointTest {

	public static final String ANGLE_TESTS = "Angle Tests";
	boolean checkStructure;

	Point point;
	double fractionComplete = 0.0;

	protected void assertAngle(double aComputed, double aCorrect) {
		Assert.assertTrue("computedAngle " + aComputed + " != correctAngle " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);

	}
	protected void assertRadius(double aComputed, double aCorrect) {
		Assert.assertTrue("computedRadius " + aComputed + " != correctRadius " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);
	}
	protected abstract void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) ;
	public void createPoint(int theX, int theY) {
		point = (Point) BasicProjectIntrospection.createInstance(
				Point.class, new Object[] { theX, theY});
		Assert.assertTrue("A Cartesian Point class not found", point != null);
		
	}
	public void test(int theX, int theY, double aCorrectRadius,
			double aCorrectAngle) {
		try {
//			point = (ECreditPoint) BasicProjectIntrospection.createInstance(
//					ECreditPoint.class, new Object[] { theX, theY});
			createPoint(theX, theY);
			double aComputedRadius =  point.getRadius();
			double aComputedAngle = point.getAngle();
			checkComputations(aComputedAngle, aComputedRadius, aCorrectAngle, aCorrectRadius);		

		} catch (Exception e) {
			BasicJUnitUtils.assertTrue(e, fractionComplete);
		}
	}
}
