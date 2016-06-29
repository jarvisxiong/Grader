package gradingTools.comp999junit.assignment1.testcases;

import grader.junit.JUnitUtils;
import grader.util.ProjectExecution;
import grader.util.BasicProjectIntrospection;

import org.junit.Assert;
import org.junit.Test;

public class APointTranslationTest extends AnAbstractPointTest{

	@Override
	protected void checkComputations(double aComputedAngle,
			double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		// TODO Auto-generated method stub
		
	}
	// all or nothing
	@Test
	public void test() {
		try {
			
			pointProxy.createCartesianPoint(5, 10);
			Point retVal = pointProxy.translate(pointProxy.getPoint(), 5, 10);
			int actualX = retVal.getX();
			int actualY = retVal.getY();
			Assert.assertTrue(
					"X property " +  actualX +  " is not equal to 10 or " +						
					"Y property " + actualY + " is not equal to 20 ",					
					actualX == 10 && actualY == 20) ;
		} catch (Exception e) {
			JUnitUtils.assertTrue(e, fractionComplete);
		}
	}

}
