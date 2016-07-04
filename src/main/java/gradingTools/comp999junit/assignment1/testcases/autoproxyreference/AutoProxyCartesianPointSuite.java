package gradingTools.comp999junit.assignment1.testcases.autoproxyreference;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.GradableJUnitSuite;
import grader.basics.project.BasicProject;
import grader.basics.project.CurrentProjectHolder;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointAngleSuite;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointPrintTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointProxyFactory;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointRadiusTest;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiPointTranslationTest;
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
		GradableJUnitSuite aGradable = BasicJUnitUtils.toGradableTree(AutoProxyCartesianPointSuite.class);
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

