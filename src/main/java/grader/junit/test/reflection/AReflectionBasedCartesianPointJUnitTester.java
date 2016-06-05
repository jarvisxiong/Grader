package grader.junit.test.reflection;


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
//@Group(CartesianPointSuite.POLAR_COORDINATES)
public class AReflectionBasedCartesianPointJUnitTester {
	// weights
	public static final double CORRECT_CLASS = 0.1;
	public static final double CORRECT_CONSTRUCTOR = 0.1;
	public static final double CORRECT_INSTANTIATION = 0.1;
	public static final double CORRECT_ANGLE_METHOD = 0.1;
	public static final double CORRECT_RADIUS_METHOD = 0.1;
	public static final double ANGLE_0 = 0.1;
//	public static final double ANGLE_90 = 0.1;
//	public static final double ANGLE_MINUS_90 = 0.1;
//	public static final double ANGLE_180 = 0.1;
//	public static final double ANGLE_MINUS_180 = 0.1;
	int testNumber = 0;
	boolean checkStructure;
	double fractionComplete = 0.0;	
	@Test
	public void test() {
		test(10, 10, 14.142, Math.PI); // 45 degree angle
		test(10, 0, 10.0, 0); // 0 degree angle
		test(0, 10, 10.0, Math.PI / 2); // 90 degree angle
		test(10, 10, 10.0, Math.PI / 4); // 90 degree angle
		test(0, -10, 10.0, -Math.PI / 2); // -90 degree angle
	}	
	static Class[] emptyClassArray = {};
	static Object[] emptyObjectArray = {};
	Class aCartesianPointClass;
	Object point;

	static void assertTrue (Exception e, double aFractionComplete) {
		Assert.assertTrue(e.getClass().getName() + e.getMessage() + ":" + aFractionComplete, false);
	}
	boolean checkStructure() {
		return checkStructure;
	}
	protected void createCartesianPoint(int theX, int theY) throws Exception {
		aCartesianPointClass = Class.forName("grader.junit.ACartesianPoint", true, ClassLoaderFactory.getCurrentClassLoader());
		if (checkStructure())
			fractionComplete += CORRECT_CLASS;;
		Constructor aConstructor = aCartesianPointClass.getConstructor(new Class[]{Integer.TYPE, Integer.TYPE});
		if (checkStructure())
			fractionComplete += CORRECT_CONSTRUCTOR;
		point = aConstructor.newInstance(new Object[] {theX, theY});
		if (checkStructure())
			fractionComplete += CORRECT_INSTANTIATION;	
		System.out.println ("Successfully Created Cartesian Point:" + point + " with parameters, x: " + theX + " y " + theY);
	}

	protected double getRadius() throws Exception {
		Method aGetRadius = aCartesianPointClass.getMethod("getRadius", emptyClassArray);
		fractionComplete += 0.1;
		Double retVal =  (Double) aGetRadius.invoke(point, emptyObjectArray);
		fractionComplete += 0.1;
		return retVal;

	}
	protected double getAngle() throws Exception {
		Method aGetRadius = aCartesianPointClass.getMethod("getAngle", emptyClassArray);
		fractionComplete += 0.1;
		Double retVal =  (Double) aGetRadius.invoke(point, emptyObjectArray);
		fractionComplete += 0.1;
		return retVal;

	}
	protected void assertAngle(double aComputed, double aCorrect) {
		Assert.assertTrue("computedAngle " + aComputed + "!= correctAngle " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);

	}
	protected void assertRadius(double aComputed, double aCorrect) {
		Assert.assertTrue("computedRadius " + aComputed + "!= correctAngle " + aCorrect + " :" + fractionComplete, Math.abs(aComputed - aCorrect) < 0.1);

	}
	protected void checkComputations (double aComputedAngle, double aComputedRadius, double aCorrectAngle, double aCorrectRadius) {
		assertRadius(aComputedRadius, aCorrectRadius);
		assertAngle(aComputedAngle, aCorrectAngle);
//		Assert.assertTrue("computedAngle != correctAngle:" + fractionComplete, aComputedAngle == aCorrectAngle);
//		Assert.assertTrue("computedRadius != correctRadius:" + fractionComplete, aComputedRadius == aCorrectRadius);	
	}
//	protected void checkRadius (double aComputedRadius, double aCorrectRadius) {
////		Assert.assertTrue("computedAngle != correctAngle:" + fractionComplete, aComputedAngle == aCorrectAngle);
//		Assert.assertTrue("computedRadius != correctRadius:" + fractionComplete, aComputedRadius == aCorrectRadius);	
//	}
//	protected void checkAngle (double aComputedRadius, double aCorrectRadius) {
//		Assert.assertTrue("computedAngle != correctAngle:" + fractionComplete, aComputedRadius == aCorrectRadius);
////		Assert.assertTrue("computedRadius != correctRadius:" + fractionComplete, aComputedRadius == aCorrectRadius);	
//	}
	public void test(int theX, int theY, double aCorrectRadius,
			double aCorrectAngle) {
		testNumber++;
		try {
//		Class aCartesianPointClass = Class.forName("grader.junit.ACartesianPoint", true, ClassLoaderFactory.getCurrentClassLoader());
//		fractionComplete += 0.1;
//		Constructor aConstructor = aCartesianPointClass.getConstructor(new Class[]{Integer.TYPE, Integer.TYPE});
//		fractionComplete += 0.1;
//		Method aGetRadius = aCartesianPointClass.getMethod("getRadius", emptyClassArray);
//		fractionComplete += 0.1;
//		Method aGetAngle = aCartesianPointClass.getMethod("getAngle", emptyClassArray);
//		Object point = aConstructor.newInstance(new Object[] {theX, theY});
//		fractionComplete += 0.1;
//
////		Point point = new ACartesianPoint(theX, theY);
//		Double computedRadius =  (Double) aGetRadius.invoke(point, emptyObjectArray);
//		Double computedAngle = (Double) aGetAngle.invoke(point, emptyObjectArray);
			createCartesianPoint(theX, theY);
			double aComputedRadius =  getRadius();
			double aComputedAngle = getAngle();
			checkComputations(aComputedAngle, aComputedRadius, aCorrectAngle, aCorrectRadius);
			
//		Assert.assertTrue(
//				"computedRadius != correctRadius && computedAngle != correctAngle:0.0", 
//				computedRadius == theCorrectRadius || computedAngle == theCorrectAngle);
//		Assert.assertTrue("computedAngle != correctAngle:0.5", computedAngle == theCorrectAngle);
//		Assert.assertTrue("computedRadius != correctRadius:0.5", computedRadius == theCorrectRadius);	
//		} catch (ClassNotFoundException e) {
//			assertTrue(e, fractionComplete);
//		} catch (NoSuchMethodException e) {
//			assertTrue(e, fractionComplete);
//
//		} catch (SecurityException e) {
//			assertTrue(e, fractionComplete);
//		} catch (InstantiationException e) {
//			assertTrue(e, fractionComplete);
//		} catch (IllegalAccessException e) {
//			assertTrue(e, fractionComplete);
//
//		} catch (IllegalArgumentException e) {
//			assertTrue(e, fractionComplete);
//
//		} catch (InvocationTargetException e) {
//			assertTrue(e, fractionComplete);
		} catch (Exception e) {
			assertTrue(e, fractionComplete);
		}
	}

}
