//package gradingTools.comp999junit.assignment1.testables.aecredit;

import grader.junit.JUnitUtils;
import grader.util.ProjectExecution;
import grader.util.BasicProjectIntrospection;

import org.junit.Assert;
import org.junit.Test;

public class ECPointPrintTest extends ECAbstractPointTest{

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
			createPoint(anX, aY);
			ProjectExecution.redirectOutput();
//			String aHeader = "The point is:";
//			point.print(aHeader, point);
//			String aHeader = "The point is:";
			point.print();
			String anOutput = ProjectExecution.restoreOutputAndGetRedirectedOutput();
			Assert.assertTrue(anOutput + " does not contain prints of " + anX + " " + aY, 
					anOutput.contains(" " + anX) &&
					anOutput.contains(" " + aY));
		} catch (Exception e) {
			JUnitUtils.assertTrue(e, fractionComplete);
		}
	}

}
