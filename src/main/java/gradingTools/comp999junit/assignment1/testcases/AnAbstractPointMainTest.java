package gradingTools.comp999junit.assignment1.testcases;

import grader.junit.JUnitUtils;

import org.junit.Assert;

public abstract class AnAbstractPointMainTest extends AnAbstractPointTest {
	
	protected abstract  String runMain (String anInput, String[] anArgs) ;
	protected  void testMain(int theX, int theY, double aCorrectRadius,
			double aCorrectAngle) {
		double fractionComplete = 0.0;
		String anInput = theX + "\n" + theY;
		String anOutput = runMain(anInput, new String[]{});
		String[] anOutputLines = anOutput.split("\n");
		if (anOutputLines.length != 2) {
			Assert.assertTrue("Expecting exactly two output lines:" + fractionComplete, false);
		}
		fractionComplete += 0.1;
		try {
			Double aComputedRadius = Double.parseDouble (anOutputLines[0]);
			Double aComputedAngle = Double.parseDouble(anOutputLines[1]);
			checkComputations(aComputedAngle, aComputedRadius, aCorrectAngle, aCorrectRadius);		


		} catch (Exception e) {
			Assert.assertTrue("One or more output does not parse as a double:" + fractionComplete, false);
		}
		
	}
	@Override
	protected void checkComputations(double aComputedAngle,
			double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertAngle(aComputedAngle, aCorrectAngle);
		assertRadius(aComputedRadius, aCorrectRadius);
		// TODO Auto-generated method stub
		
	}
	
}
