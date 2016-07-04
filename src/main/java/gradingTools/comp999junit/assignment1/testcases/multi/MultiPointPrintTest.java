package gradingTools.comp999junit.assignment1.testcases.multi;

import grader.basics.execution.BasicProjectExecution;
import grader.basics.junit.BasicJUnitUtils;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.MaxValue;
@MaxValue(2)
public class MultiPointPrintTest extends MultiAbstractPointTest{

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
			BasicProjectExecution.redirectOutput();
//			pointProxy.print(aHeader, pointProxy.getPoint());
			pointProxy.print();
			String anOutput = BasicProjectExecution.restoreOutputAndGetRedirectedOutput();
			Assert.assertTrue(anOutput + " does not contain prints of " + anX + " " + aY, 
					anOutput.contains(" " + anX) &&
					anOutput.contains(" " + aY));
			

		} catch (Exception e) {
			BasicJUnitUtils.assertTrue(e, fractionComplete);
			e.printStackTrace();
		}
	}

}
