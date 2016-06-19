package gradingTools.comp999junit.assignment1.testcases.reflection.tags;

import framework.project.BasicProject;
import framework.project.CurrentProjectHolder;
import framework.utils.BasicGradingEnvironment;
import grader.project.flexible.AFlexibleProject;
import gradingTools.comp999junit.assignment1.testcases.APointAngleFortyFiveDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleMinusNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleZeroDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointRadiusTest;
import gradingTools.comp999junit.assignment1.testcases.PointProxyFactory;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

//@RunWith(Suite.class)
@RunWith(Suite.class)
@Suite.SuiteClasses({
   ATaggingPointProxy.class,
   APointAngleZeroDegreeTest.class,
   APointAngleNinetyDegreeTest.class,
   APointAngleFortyFiveDegreeTest.class,
   APointAngleMinusNinetyDegreeTest.class,
   APointRadiusTest.class,
   
})
public class TaggingCartesianPointSuite {

	public static void main (String[] args) {
		BasicGradingEnvironment.get().setLoadClasses(true);

		PointProxyFactory.setPointProxy(new ATaggingPointProxy());
		try {
//			CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "allcorrect"));
			CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "wrongangle"));

		Result aResult = JUnitCore.runClasses(TaggingCartesianPointSuite.class);
		for (Failure failure : aResult.getFailures()) {
	         System.out.println(failure.toString());
	      }
	    System.out.println(aResult.wasSuccessful());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

