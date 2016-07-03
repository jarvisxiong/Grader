//package gradingTools.comp999junit.assignment1.testables.aecredit;

import grader.junit.JUnitUtils;
import grader.util.BasicProjectIntrospection;
import gradingTools.comp999junit.assignment1.testables.allcorrect.ACPoint;

import org.junit.Assert;

public abstract class ECAbstractPointTest {

	public static final String ANGLE_TESTS = "Angle Tests";
	boolean checkStructure;

	ECPoint point;
	double fractionComplete = 0.0;

	protected void assertAngle(double aComputed, double aCorrect) {
		Assert.assertTrue("computedAngle " + aComputed + " != correctAngle " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);

	}
	protected void assertRadius(double aComputed, double aCorrect) {
		Assert.assertTrue("computedRadius " + aComputed + " != correctRadius " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);
	}
	protected abstract void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) ;
	public void createPoint(int theX, int theY) {
		point = (ECPoint) BasicProjectIntrospection.createInstance(
				ECPoint.class, new Object[] { theX, theY});
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
			JUnitUtils.assertTrue(e, fractionComplete);
		}
	}
}
