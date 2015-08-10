package demoAndTest.incrementalInput.java;

import grader.assignment.GradingFeature;
import grader.navigation.NavigationKind;
import grader.navigation.filter.GradingStatus;
import grader.project.source.ATACommentsExtractor;
import grader.steppers.ComplexProjectStepper;
import grader.steppers.OverviewProjectStepper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import demoAndTest.GraderDemoerAndTester;
import demoAndTest.basic.AJavaPalindromeBasedGraderDemoerAndTester;
import demoAndTest.multiparadigm.C.ACMixedArithmeticGraderDemoerAndTester;
import demoAndTest.multiparadigm.java.AJavaMixedArithmeticGraderDemoerAndTester;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
//import bus.uigen.pipe.DemoerAndTester;
import tools.DirectoryUtils;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.ThreadSupport;
import util.trace.Tracer;
/*
 * 

 */
public class AJavaMixedArithmeticIncrementalInputGraderDemoerAndTester extends AJavaMixedArithmeticGraderDemoerAndTester {
	
	//	 String[] args ;
	public final  String INC_TEST_DIR = "Test Data/Test 110Inc";
	public final  String INC_CORRECT_DIR = "Test Data/Correct 110Inc";
//	public final String COURSE_NO = "Comp411";
	public final String INC_COURSE_NO = "Comp110Inc";



	public AJavaMixedArithmeticIncrementalInputGraderDemoerAndTester(String[] anArgs) {
		super(anArgs);
	}
	@Override
	protected String testDir() {
		return INC_TEST_DIR;
	}
	@Override
	protected String correctDir() {
		return INC_CORRECT_DIR;
	}
	@Override
	protected String courseNo() {
		return INC_COURSE_NO;
	}
	
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		GraderDemoerAndTester aDemoerAndTester = new AJavaMixedArithmeticIncrementalInputGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(AJavaMixedArithmeticIncrementalInputGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
