package gradingTools.comp999junit.assignment1.testcases.autoproxyreference;


//import org.junit.Test;
import grader.util.ProjectIntrospection;
import gradingTools.comp999junit.assignment1.testables.allcorrect.ACartesianPoint;
import gradingTools.comp999junit.assignment1.testables.allcorrect.Point;
import gradingTools.comp999junit.assignment1.testcases.directreference.ADirectPointProxy;
import gradingTools.comp999junit.assignment1.testcases.PointProxy;
import gradingTools.comp999junit.assignment1.testcases.directreference.ADirectPointProxy;
//import gradingTools.testables.comp999junit.assignment1.wrongangle.ACartesianPoint;
//import gradingTools.testables.comp999junit.assignment1.wrongangle.Point;
import util.annotations.MaxValue;

@MaxValue(6)
//@Explanation("Radius and Angle Correctly Computed")
//@Group(CartesianPointSuite.ANGLE_TESTS)
public class AnAutoPointProxy extends ADirectPointProxy implements PointProxy{	
	@Override
	protected Point instantiatePoint(int theX, int theY) {
		return (Point) ProjectIntrospection.createInstance(Point.class, new Object[] { theX, theY});

//		return (Point) ProjectIntrospection.createInstance(ACartesianPoint.class, new Object[] { theX, theY});
	}
}
