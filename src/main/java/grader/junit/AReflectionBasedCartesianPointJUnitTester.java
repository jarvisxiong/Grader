package grader.junit;


//import org.junit.Test;
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
@IsExtra(true)
@IsRestriction(false)
@MaxValue(15)
@Explanation("Radius and Angle Correctly Computed")
@Group("Polar Computation")
public class AReflectionBasedCartesianPointJUnitTester {	
	@Test
	public void test() {
//		test(10, 0, 10.0, 0); // 0 degree angle
		test(10, 0, 5.0, 5.0); // 0 degree angle

		test(0, 10, 10.0, Math.PI / 2); // 90 degree angle
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
		test(-10, 0, 10.0, Math.PI); // 180 degree angle
	}	
	static Class[] emptyClassArray = {};
	static Object[] emptyObjectArray = {};

	static void assertTrue (Exception e, double aFractionComplete) {
		Assert.assertTrue(e.getClass().getName() + e.getMessage() + ":" + aFractionComplete, false);
	}
	public void test(int theX, int theY, double theCorrectRadius,
			double theCorrectAngle) {
		double fractionComplete = 0.0;

		try {
		Class aCartesianPointClass = Class.forName("grader.junit.ACartesianPoint", true, ClassLoaderFactory.getCurrentClassLoader());
		fractionComplete += 0.1;
		Constructor aConstructor = aCartesianPointClass.getConstructor(new Class[]{Integer.TYPE, Integer.TYPE});
		fractionComplete += 0.1;
		Method aGetRadius = aCartesianPointClass.getMethod("getRadius", emptyClassArray);
		fractionComplete += 0.1;
		Method aGetAngle = aCartesianPointClass.getMethod("getAngle", emptyClassArray);
		Object point = aConstructor.newInstance(new Object[] {theX, theY});
		fractionComplete += 0.1;

//		Point point = new ACartesianPoint(theX, theY);
		Double computedRadius =  (Double) aGetRadius.invoke(point, emptyObjectArray);
		Double computedAngle = (Double) aGetAngle.invoke(point, emptyObjectArray);
		Assert.assertTrue(
				"computedRadius != correctRadius && computedAngle != correctAngle:0.0", 
				computedRadius == theCorrectRadius || computedAngle == theCorrectAngle);
		Assert.assertTrue("computedAngle != correctAngle:0.5", computedAngle == theCorrectAngle);
		Assert.assertTrue("computedRadius != correctRadius:0.5", computedRadius == theCorrectRadius);	
		} catch (ClassNotFoundException e) {
			assertTrue(e, fractionComplete);
		} catch (NoSuchMethodException e) {
			assertTrue(e, fractionComplete);

		} catch (SecurityException e) {
			assertTrue(e, fractionComplete);
		} catch (InstantiationException e) {
			assertTrue(e, fractionComplete);
		} catch (IllegalAccessException e) {
			assertTrue(e, fractionComplete);

		} catch (IllegalArgumentException e) {
			assertTrue(e, fractionComplete);

		} catch (InvocationTargetException e) {
			assertTrue(e, fractionComplete);
		} 
	}

}
