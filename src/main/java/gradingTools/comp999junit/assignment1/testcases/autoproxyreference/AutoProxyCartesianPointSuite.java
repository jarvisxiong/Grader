package gradingTools.comp999junit.assignment1.testcases.autoproxyreference;

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
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointTranslationTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleSuite;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxyFactory;
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
   AReflectivePointMainTest.class,
   AnAutoPointProxy.class,
   MultiPointAngleSuite.class,
//   APointAngleZeroDegreeTest.class,
//   APointAngleNinetyDegreeTest.class,
//   APointAngleFortyFiveDegreeTest.class,
//   APointAngleMinusNinetyDegreeTest.class,
   MultiPointRadiusTest.class,
   MultiPointPrintTest.class,
   MultiPointTranslationTest.class
   
})
public class AutoProxyCartesianPointSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	public static void main (String[] args) {
		try {
		MultiPointProxyFactory.setPointProxy(new AnAutoPointProxy());
//		BasicGradingEnvironment.get().setLoadClasses(false);
		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "wrongangle"));
//		CurrentProjectHolder.setProject(new BasicProject(null, new File("."), null, "allcorrect"));
		GradableJUnitSuite aGradable = JUnitUtils.toGradableTree(AutoProxyCartesianPointSuite.class);
		aGradable.testAll();
		System.out.println(aGradable.getText());
		ObjectEditor.treeEdit(aGradable);
		Result aResult = JUnitCore.runClasses(AutoProxyCartesianPointSuite.class);
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

