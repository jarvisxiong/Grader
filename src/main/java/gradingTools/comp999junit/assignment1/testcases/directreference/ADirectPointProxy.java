package gradingTools.comp999junit.assignment1.testcases.directreference;


//import org.junit.Test;
import grader.basics.junit.BasicJUnitUtils;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiCartesianPoint;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPoint;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxy;

import org.junit.Test;

import util.annotations.MaxValue;
//import gradingTools.comp999junit.assignment1.allcorrect.ACartesianPoint;
//import gradingTools.comp999junit.assignment1.allcorrect.Point;
//import gradingTools.testables.comp999junit.assignment1.wrongangle.ACartesianPoint;
//import gradingTools.testables.comp999junit.assignment1.wrongangle.Point;

@MaxValue(6)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.ANGLE_TESTS)
public class ADirectPointProxy implements MultiPointProxy{
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
	protected double fractionComplete = 0.0;	
	public  ADirectPointProxy() {
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
			e.printStackTrace();
			BasicJUnitUtils.assertTrue(e,fractionComplete);
		}
	}	
//	static Class[] emptyClassArray = {};
//	static Object[] emptyObjectArray = {};
//	Class aCartesianPointClass;
	MultiPoint point;

	
	boolean checkStructure() {
		return checkStructure;
	}
	// make this public
	protected MultiPoint instantiatePoint(int theX, int theY) {
		return new MultiCartesianPoint(theX, theY);
	}
	
	@Override
	public void createCartesianPoint(int theX, int theY) throws Exception {
//		aCartesianPointClass = Class.forName("grader.junit.ACartesianPoint", true, ClassLoaderFactory.getCurrentClassLoader());
//		 point = new ACartesianPoint(theX, theY);
//		 point = new ACartesianPoint(theX, theY);
		 point = instantiatePoint(theX, theY);

		if (checkStructure) {
			fractionComplete += CORRECT_CLASS;;
		
			fractionComplete += CORRECT_CONSTRUCTOR;
		
			fractionComplete += CORRECT_INSTANTIATION;	
		}
		System.out.println ("Successfully Created Cartesian Point:" + point + " with parameters, x: " + theX + " y " + theY);
	}
	
	
	@Override
	public double getRadius() throws Exception {
		double retVal = point.getRadius();
		if (checkStructure())
		   fractionComplete += CORRECT_RADIUS_METHOD;
		
		return retVal;

	}
	@Override
	public double getAngle() throws Exception {
		double retVal = point.getAngle();
		
		if (checkStructure())
			fractionComplete += CORRECT_ANGLE_METHOD;		
		return retVal;

	}
//	@Override
//	public void print(String aString, Point aPoint) {
//		point.print(aString, aPoint);
//		
//	}
	@Override
	public void print() {
		point.print();
		
	}
	@Override
	public MultiPoint getPoint() {
		return point;
	}

	@Override
	public MultiPoint translate(MultiPoint aPoint, int anXDelta, int aYDelta) {
		// TODO Auto-generated method stub
		return point.translate(aPoint, anXDelta, aYDelta);
	}


}
