package gradingTools.comp999junit.assignment1.testcases.reflection.tags;

import grader.basics.project.BasicProject;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.settings.BasicGradingEnvironment;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleFortyFiveDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleMinusNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleZeroDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxyFactory;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointRadiusTest;

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
   MultiPointAngleZeroDegreeTest.class,
   MultiPointAngleNinetyDegreeTest.class,
   MultiPointAngleFortyFiveDegreeTest.class,
   MultiPointAngleMinusNinetyDegreeTest.class,
   MultiPointRadiusTest.class
//   APointTranslationTest.class
   
})
public class TaggingCartesianPointSuite {

	public static void main (String[] args) {
		BasicGradingEnvironment.get().setLoadClasses(true);

		MultiPointProxyFactory.setPointProxy(new ATaggingPointProxy());
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

