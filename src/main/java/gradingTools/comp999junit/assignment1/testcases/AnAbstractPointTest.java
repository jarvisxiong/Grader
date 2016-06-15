package gradingTools.comp999junit.assignment1.testcases;

import grader.junit.JUnitUtils;

import org.junit.Assert;

public abstract class AnAbstractPointTest {
	public static final String ANGLE_TESTS = "Angle Tests";

	int testNumber = 0;
	boolean checkStructure;
	double fractionComplete = 0.0;
//	PointProxy pointProxy = new AReflectivePointProxy();
	PointProxy pointProxy = PointProxyFactory.getPointProxy();

	protected void assertAngle(double aComputed, double aCorrect) {
		Assert.assertTrue("computedAngle " + aComputed + " != correctAngle " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);

	}
	protected void assertRadius(double aComputed, double aCorrect) {
		Assert.assertTrue("computedRadius " + aComputed + " != correctRadius " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);
	}
	protected abstract void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) ;
	public void test(int theX, int theY, double aCorrectRadius,
			double aCorrectAngle) {
		testNumber++;
		try {
			pointProxy.createCartesianPoint(theX, theY);
			double aComputedRadius =  pointProxy.getRadius();
			double aComputedAngle = pointProxy.getAngle();
			checkComputations(aComputedAngle, aComputedRadius, aCorrectAngle, aCorrectRadius);		

		} catch (Exception e) {
			JUnitUtils.assertTrue(e, fractionComplete);
		}
	}
}
