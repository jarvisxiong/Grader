package gradingTools.comp999junit.assignment1.testcases.multi;

import org.junit.Assert;

public abstract class MultiAbstractPointMainTest extends MultiAbstractPointTest {
	
	protected abstract  String runMain (String[] anArgs, String... anInput) ;
	protected  void testMain(int theX, int theY, double aCorrectRadius,
			double aCorrectAngle) {
		double fractionComplete = 0.0;
//		String anInput = theX + "\n" + theY;
//		String anOutput = runMain(new String[]{}, anInput);
		String anOutput = runMain(new String[]{}, "" + theX, "" + theY);
//		String anOutput2 = runMain(new String[]{}, "" + theX, "" + theY);

		
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
