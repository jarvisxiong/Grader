package demoAndTest.colabIncrementalInput;

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
import demoAndTest.multiparadigm.distributed.ADistributedMixedArithmeticGraderDemoerAndTester;
import demoAndTest.multiparadigm.java.AJavaMixedArithmeticGraderDemoerAndTester;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.pipe.DemoerAndTester;
import tools.DirectoryUtils;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.ThreadSupport;
import util.trace.Tracer;
/*
 * 

 */
public class ADistributedMixedArithmeticColabIncrementalInputGraderDemoerAndTester extends ADistributedMixedArithmeticGraderDemoerAndTester {
	
	//	 String[] args ;
	public static final  String COLAB_INC_TEST_DIR = "Test Data/Test 790ColabInc";
	public final  String COLAB_INC_CORRECT_DIR = "Test Data/Correct 790ColabInc";
//	public final String COURSE_NO = "Comp411";
	public static final String COLAB_INC_COURSE_NO = "Comp790ColabInc";



	public ADistributedMixedArithmeticColabIncrementalInputGraderDemoerAndTester(String[] anArgs) {
		super(anArgs);
	}
	@Override
	protected String testDir() {
		return COLAB_INC_TEST_DIR;
	}
	@Override
	protected String correctDir() {
		return COLAB_INC_CORRECT_DIR;
	}
	@Override
	protected String courseNo() {
		return COLAB_INC_COURSE_NO;
	}
	
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		GraderDemoerAndTester aDemoerAndTester = new ADistributedMixedArithmeticColabIncrementalInputGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(ADistributedMixedArithmeticColabIncrementalInputGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
