package gradingTools.comp999junit.assignment1.testcases.autoproxyreference;


//import org.junit.Test;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp999junit.assignment1.testcases.directreference.ADirectPointProxy;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPoint;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxy;
//import gradingTools.testables.comp999junit.assignment1.wrongangle.ACartesianPoint;
//import gradingTools.testables.comp999junit.assignment1.wrongangle.Point;
import util.annotations.MaxValue;

@MaxValue(6)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.ANGLE_TESTS)
public class AnAutoPointProxy extends ADirectPointProxy implements MultiPointProxy{	
	@Override
	protected MultiPoint instantiatePoint(int theX, int theY) {
		return (MultiPoint) BasicProjectIntrospection.createInstance(MultiPoint.class, new Object[] { theX, theY});

//		return (Point) ProjectIntrospection.createInstance(ACartesianPoint.class, new Object[] { theX, theY});
	}
}
