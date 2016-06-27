package gradingTools.comp999junit.assignment1.testables.ecredit;

import framework.project.BasicProject;
import framework.project.CurrentProjectHolder;
import framework.utils.BasicGradingEnvironment;
import grader.junit.GradableJUnitSuite;
import grader.junit.JUnitUtils;
import grader.project.flexible.AFlexibleProject;
import gradingTools.comp999junit.assignment1.testcases.APointAngleFortyFiveDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleMinusNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleNinetyDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointAngleZeroDegreeTest;
import gradingTools.comp999junit.assignment1.testcases.APointPrintTest;
import gradingTools.comp999junit.assignment1.testcases.APointRadiusTest;
import gradingTools.comp999junit.assignment1.testcases.PointAngleSuite;
import gradingTools.comp999junit.assignment1.testcases.PointProxyFactory;
import gradingTools.comp999junit.assignment1.testcases.reflection.AReflectivePointMainTest;

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
   ECreditPointAngleSuite.class,
//   APointAngleZeroDegreeTest.class,
//   APointAngleNinetyDegreeTest.class,
//   APointAngleFortyFiveDegreeTest.class,
//   APointAngleMinusNinetyDegreeTest.class,
   ECreditPointRadiusTest.class,
   
})
public class ECreditCartesianPointSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	public static void main (String[] args) {
		try {
//		BasicGradingEnvironment.get().setLoadClasses(false);
		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "wrongangle"));
//		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "allcorrect"));
		GradableJUnitSuite aGradable = JUnitUtils.toGradableTree(ECreditCartesianPointSuite.class);
		aGradable.testAll();
		System.out.println(aGradable.getText());
		ObjectEditor.treeEdit(aGradable);
		Result aResult = JUnitCore.runClasses(ECreditCartesianPointSuite.class);
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

