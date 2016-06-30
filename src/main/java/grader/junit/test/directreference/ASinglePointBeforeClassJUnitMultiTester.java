package grader.junit.test.directreference;


import gradingTools.comp999junit.assignment1.testables.allcorrect.ACCartesianPoint;
import gradingTools.comp999junit.assignment1.testables.allcorrect.ACPoint;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ASinglePointBeforeClassJUnitMultiTester {	
	static ACPoint point;
	@BeforeClass
	public static void createPoint() {
		System.out.println("Testing 10, 10");
		point = new ACCartesianPoint(10, 10);
	}
	
	@Test
	public void testAngle() {
		double computedAngle = point.getAngle();	
		Assert.assertTrue(computedAngle == Math.PI/4);

	}
	@Test
	public void testRadius() {
		double computedRadius = point.getRadius();
		Assert.assertTrue(computedRadius == Math.sqrt(200));
		
	}
	

}
