package gradingTools.comp999junit.assignment1.testcases.reflection;


//import org.junit.Test;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPoint;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

import util.annotations.MaxValue;
import util.introspect.ClassLoaderFactory;

@MaxValue(6)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.ANGLE_TESTS)
public class AReflectivePointProxy implements MultiPointProxy{
	// weights
	public static final double CORRECT_CLASS = 0.2;
	public static final double CORRECT_CONSTRUCTOR = 0.2;
	public static final double CORRECT_INSTANTIATION = 0.2;
	public static final double CORRECT_ANGLE_METHOD = 0.2;
	public static final double CORRECT_RADIUS_METHOD = 0.2;
//	public static final double ANGLE_0 = 0.1;
//	public static final double ANGLE_90 = 0.1;
//	public static final double ANGLE_MINUS_90 = 0.1;
//	public static final double ANGLE_180 = 0.1;
//	public static final double ANGLE_MINUS_180 = 0.1;
//	int testNumber = 0;
	protected boolean checkStructure;
	protected Method radiusMethod;
	protected Method angleMethod;
	protected double fractionComplete = 0.0;	
	public  AReflectivePointProxy() {
		checkStructure = true;
	}
//	public  AReflectivePointStructureTest(boolean aCheckStructure) {
//		checkStructure = aCheckStructure;
//	}
	@Test
	public void test() {
		try {
		createCartesianPoint(0, 0);
		getRadius();
		getAngle();
		} catch (Exception e) {
			BasicJUnitUtils.assertTrue(e,fractionComplete);
		}
	}	
	protected static Class[] emptyClassArray = {};
	protected static Object[] emptyObjectArray = {};
	protected Class aCartesianPointClass;
	protected Object point;

	
	protected boolean checkStructure() {
		return checkStructure;
	}
	
	public Class getCartesianPointClass() throws ClassNotFoundException  {
		return Class.forName("gradingTools.testables.comp999junit.assignment1.wrongangle.ACartesianPoint", true, ClassLoaderFactory.getCurrentClassLoader());
//		return Class.forName("gradingTools.testables.comp999junit.assignment1.wrongangle.ACartesianPoint", true, ClassLoaderFactory.getCurrentClassLoader());

	}
	@Override
	public void createCartesianPoint(int theX, int theY) throws Exception {
//		aCartesianPointClass = Class.forName("grader.junit.ACartesianPoint", true, ClassLoaderFactory.getCurrentClassLoader());
		aCartesianPointClass = getCartesianPointClass();

		if (aCartesianPointClass != null && checkStructure())
			fractionComplete += CORRECT_CLASS;;
		Constructor aConstructor = aCartesianPointClass.getConstructor(new Class[]{Integer.TYPE, Integer.TYPE});
		if (checkStructure())
			fractionComplete += CORRECT_CONSTRUCTOR;
		point = aConstructor.newInstance(new Object[] {theX, theY});
		if (checkStructure())
			fractionComplete += CORRECT_INSTANTIATION;	
		System.out.println ("Successfully Created Cartesian Point:" + point + " with parameters, x: " + theX + " y " + theY);
	}
	
	public Method findRadiusMethod() throws Exception {
		return aCartesianPointClass.getMethod("getRadius", emptyClassArray);
	}
	protected Method getOrFindRadiusMethod() throws Exception {
		if (radiusMethod == null ) {
			radiusMethod = findRadiusMethod();
		}
		return radiusMethod;
	}
	protected Method getOrFindAngleMethod() throws Exception {
		if (angleMethod == null ) {
			angleMethod = findAngleMethod();
		}
		return angleMethod;
	}
	public Method findAngleMethod() throws Exception {
		return aCartesianPointClass.getMethod("getAngle", emptyClassArray);
	}
	@Override
	public double getRadius() throws Exception {
		Method aGetRadius = getOrFindRadiusMethod();
		if (checkStructure())
		   fractionComplete += CORRECT_RADIUS_METHOD;
		Double retVal =  (Double) aGetRadius.invoke(point, emptyObjectArray);
		
//		fractionComplete += 0.1;
		return retVal;

	}
	@Override
	public double getAngle() throws Exception {
		Method aGetAngle = getOrFindAngleMethod();
		if (checkStructure())
			fractionComplete += CORRECT_ANGLE_METHOD;
		Double retVal =  (Double) aGetAngle.invoke(point, emptyObjectArray);
		fractionComplete += 0.1;
		return retVal;

	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public MultiPoint getPoint() {
		return null;
	}
	@Override
	public MultiPoint translate(MultiPoint aPoint, int anXDelta, int aYDelta) {
		// TODO Auto-generated method stub
		return null;
	}


}
