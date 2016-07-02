package gradingTools.comp999junit.assignment1.testcases;

import framework.grading.testing.TestCaseResult;
import framework.project.BasicProject;
import framework.project.CurrentProjectHolder;
import framework.utils.BasicGradingEnvironment;
import grader.junit.GradableJUnitSuite;
import grader.junit.JUnitUtils;
import grader.project.flexible.AFlexibleProject;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleFortyFiveDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleMinusNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleZeroDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointPrintTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointRadiusTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleSuite;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxyFactory;
import gradingTools.comp999junit.assignment1.testcases.reflection.AReflectivePointMainTest;
import gradingTools.comp999junit.assignment1.testcases.testcases.TestsTestSuite;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import bus.uigen.ObjectEditor;

//@RunWith(Suite.class)
@RunWith(Suite.class)
@Suite.SuiteClasses({
   PointAngleSuite.class,
//   APointAngleZeroDegreeTest.class,
//   APointAngleNinetyDegreeTest.class,
//   APointAngleFortyFiveDegreeTest.class,
//   APointAngleMinusNinetyDegreeTest.class,
   PointRadiusTest.class,
   PointMainTest.class,
   PointPrintTest.class,
   PointTranslationTest.class,
   TestsTestSuite.class
   
})
public class CartesianPointSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	public static void main (String[] args) {
		try {
//		BasicGradingEnvironment.get().setLoadClasses(false);
//		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "wrongangle"));
//		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "allcorrect"));
//		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "aecredit"));
		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "secredit"));
//		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "missingcode"));



//		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "allcorrect"));
		GradableJUnitSuite aGradable = JUnitUtils.toGradableTree(CartesianPointSuite.class);
		TestCaseResult aTestCaseResult = aGradable.test();
		System.out.println(aGradable.getText());
		ObjectEditor.treeEdit(aGradable);
		Result aResult = JUnitCore.runClasses(CartesianPointSuite.class);
		for (Failure failure : aResult.getFailures()) {
	         System.out.println(failure.toString());
	      }
	    System.out.println(aResult.wasSuccessful());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		System.exit(0); // proxy makes the main hang around?
	}

}

