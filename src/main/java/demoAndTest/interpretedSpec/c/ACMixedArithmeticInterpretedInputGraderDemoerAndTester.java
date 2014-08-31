package demoAndTest.interpretedSpec.c;

import grader.assignment.GradingFeature;
import grader.navigation.NavigationKind;
import grader.navigation.filter.GradingStatus;
import grader.project.graded.ComplexProjectStepper;
import grader.project.graded.OverviewProjectStepper;
import grader.project.source.ATACommentsExtractor;

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
public class ACMixedArithmeticInterpretedInputGraderDemoerAndTester extends AJavaMixedArithmeticGraderDemoerAndTester {
	
	//	 String[] args ;
	public static final  String INTERP_TEST_DIR = "Test Data/Test CInterp";
	public static final  String INTERP_CORRECT_DIR = "Test Data/Correct CInterp";
//	public final String COURSE_NO = "Comp411";
	public static final String INTERP_COURSE_NO = "Comp411Interp";



	public ACMixedArithmeticInterpretedInputGraderDemoerAndTester(String[] anArgs) {
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
		GraderDemoerAndTester aDemoerAndTester = new ACMixedArithmeticInterpretedInputGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(ACMixedArithmeticInterpretedInputGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
