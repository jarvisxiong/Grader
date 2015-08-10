package demoAndTest.interpretedSpec.java;

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
public class AJavaMixedArithmeticInterpretedInputGraderDemoerAndTester extends AJavaMixedArithmeticGraderDemoerAndTester {
	
	//	 String[] args ;
	public static final  String INTERP_TEST_DIR = "Test Data/Test 110Interp";
	public static final  String INTERP_CORRECT_DIR = "Test Data/Correct 110Interp";
//	public final String COURSE_NO = "Comp411";
	public static final String INTERP_COURSE_NO = "Comp110Interp";



	public AJavaMixedArithmeticInterpretedInputGraderDemoerAndTester(String[] anArgs) {
		super(anArgs);
	}
	@Override
	protected String testDir() {
		return INTERP_TEST_DIR;
	}
	@Override
	protected String correctDir() {
		return INTERP_CORRECT_DIR;
	}
	@Override
	protected String courseNo() {
		return INTERP_COURSE_NO;
	}
	
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		GraderDemoerAndTester aDemoerAndTester = new AJavaMixedArithmeticInterpretedInputGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(AJavaMixedArithmeticInterpretedInputGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
