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
	public void test() {
		try {
			int anX = 5;
			int aY = 10;
			pointProxy.createCartesianPoint(anX, aY);
			ProjectExecution.redirectOutput();
//			pointProxy.print(aHeader, pointProxy.getPoint());
			pointProxy.print();
			String anOutput = ProjectExecution.restoreOutputAndGetRedirectedOutput();
			Assert.assertTrue(anOutput + " does not contain prints of " + anX + " " + aY, 
					anOutput.contains(" " + anX) &&
					anOutput.contains(" " + aY));
			

		} catch (Exception e) {
			JUnitUtils.assertTrue(e, fractionComplete);
			e.printStackTrace();
		}
	}

}
