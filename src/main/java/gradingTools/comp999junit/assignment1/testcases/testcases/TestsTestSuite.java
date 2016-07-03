package gradingTools.comp999junit.assignment1.testcases.testcases;

import grader.basics.junit.GradableJUnitSuite;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProject;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.settings.BasicGradingEnvironment;
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

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import util.annotations.MaxValue;
import bus.uigen.ObjectEditor;
@MaxValue(10)
@RunWith(Suite.class)
@Suite.SuiteClasses({
	AngleTestTest.class,   
})
public class TestsTestSuite {
//	public static final String ANGLE_TESTS = "Angle Tests";

	

}

