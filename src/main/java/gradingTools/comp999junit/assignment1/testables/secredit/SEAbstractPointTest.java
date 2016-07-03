package gradingTools.comp999junit.assignment1.testables.secredit;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProjectIntrospection;

import org.junit.Assert;

public abstract class SEAbstractPointTest {

	public static final String ANGLE_TESTS = "Angle Tests";
	boolean checkStructure;

	SEPoint point;
	double fractionComplete = 0.0;

	protected void assertAngle(double aComputed, double aCorrect) {
		Assert.assertTrue("computedAngle " + aComputed + " != correctAngle " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);

	}
	protected void assertRadius(double aComputed, double aCorrect) {
		Assert.assertTrue("computedRadius " + aComputed + " != correctRadius " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);
	}
	protected abstract void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) ;
	public void test(int theX, int theY, double aCorrectRadius,
			double aCorrectAngle) {
		try {
			point = (SEPoint) BasicProjectIntrospection.createInstance(SEPoint.class, new Object[] { theX, theY});
			double aComputedRadius =  point.getRadius();
			double aComputedAngle = point.getAngle();
			checkComputations(aComputedAngle, aComputedRadius, aCorrectAngle, aCorrectRadius);		

		} catch (Exception e) {
			BasicJUnitUtils.assertTrue(e, fractionComplete);
		}
	}
}
