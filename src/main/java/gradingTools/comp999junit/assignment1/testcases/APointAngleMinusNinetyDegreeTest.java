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

@MaxValue(2)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.POLAR_COORDINATES)
@Group(AnAbstractPointTest.ANGLE_TESTS)
public class APointAngleMinusNinetyDegreeTest extends APointAngleTest {
	
	public  APointAngleMinusNinetyDegreeTest() {
		checkStructure = false;
	}
	@Test
	public void test() {	
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
	}
	

}
