package gradingTools.comp999junit.assignment1.testcases;


//import org.junit.Test;
import gradingTools.comp999junit.assignment1.testcases.reflection.ReflectiveCartesianPointSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Explanation;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;
import util.introspect.ClassLoaderFactory;
@MaxValue(10)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.POLAR_COORDINATES)
@Group(AnAbstractPointTest.ANGLE_TESTS)
public class APointAngleFortyFiveDegreeTest extends APointAngleTest {

	
	public  APointAngleFortyFiveDegreeTest() {
		checkStructure = false;	
	}
	@Test
	public void test() {
		test(10, 10, 14.142, Math.PI/4); // 45 degree angle		
	}
	
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertAngle(aComputedAngle, aCorrectAngle);
//		assertRadius(aComputedRadius, aCorrectRadius);
	}	

}
