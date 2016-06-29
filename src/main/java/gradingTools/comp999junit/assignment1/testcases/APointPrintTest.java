package gradingTools.comp999junit.assignment1.testcases;

import grader.junit.JUnitUtils;
import grader.util.ProjectExecution;
import grader.util.BasicProjectIntrospection;

import org.junit.Assert;
import org.junit.Test;

public class APointPrintTest extends AnAbstractPointTest{

	@Override
	protected void checkComputations(double aComputedAngle,
			double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		// TODO Auto-generated method stub
		
	}
	@Test
	// all or nothing for radius
	public void test() {
		try {
			int anX = 5;
			int aY = 10;
			pointProxy.createCartesianPoint(anX, aY);
			ProjectExecution.redirectOutput();
			String aHeader = "The point is:";
			pointProxy.print(aHeader, pointProxy.getPoint());
			String anOutput = ProjectExecution.restoreOutputAndGetRedirectedOutput();
			String anExpectedOutput =  aHeader + anX + " " + aY + "\n";
			Assert.assertTrue(anOutput + " is not equal to: " + anExpectedOutput, anExpectedOutput.equals(anOutput));

		} catch (Exception e) {
			JUnitUtils.assertTrue(e, fractionComplete);
			e.printStackTrace();
		}
	}

}
