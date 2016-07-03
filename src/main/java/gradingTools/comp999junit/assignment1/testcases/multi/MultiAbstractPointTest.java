package gradingTools.comp999junit.assignment1.testcases.multi;

import grader.basics.junit.BasicJUnitUtils;

import org.junit.Assert;

public abstract class MultiAbstractPointTest {
	public static final String ANGLE_TESTS = "Angle Tests";

	int testNumber = 0;
	boolean checkStructure;
	double fractionComplete = 0.0;
//	PointProxy pointProxy = new AReflectivePointProxy();
	MultiPointProxy pointProxy = MultiPointProxyFactory.getPointProxy();
//	protected abstract String runMain (String anInput, String[] anArgs) ;
//	protected  void testMain(int theX, int theY, double aCorrectRadius,
//			double aCorrectAngle) {
//		double fractionComplete = 0.0;
//		String anInput = theX + "\n" + theY;
//		String anOutput = runMain(anInput, new String[]{});
//		String[] anOutputLines = anOutput.split("\n");
//		if (anOutputLines.length != 2) {
//			Assert.assertTrue("Expecting exactly two output lines:" + fractionComplete, false);
//		}
//		fractionComplete += 0.1;
//		try {
//			Double aComputedRadius = Double.parseDouble (anOutputLines[0]);
//			Double aComputedAngle = Double.parseDouble(anOutputLines[1]);
//			checkComputations(aComputedAngle, aComputedRadius, aCorrectAngle, aCorrectRadius);		
//
//
//		} catch (Exception e) {
//			Assert.assertTrue("One or more output does not parse as a double:" + fractionComplete, false);
//		}
//		
//	}
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
			BasicJUnitUtils.assertTrue(e, fractionComplete);
		}
	}
}
