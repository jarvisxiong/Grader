package demoAndTest.colabInput;

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
import bus.uigen.pipes.DemoerAndTester;
import tools.DirectoryUtils;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.ThreadSupport;
import util.trace.Tracer;
/*
 * 

 */
public class ADistributedMixedArithmeticColabInputGraderDemoerAndTester extends ADistributedMixedArithmeticGraderDemoerAndTester {
	
	//	 String[] args ;
	public final  String MULTI_TEST_DIR = "Test Data/Test 790Colab";
	public final  String MULTI_CORRECT_DIR = "Test Data/Correct 790Colab";
//	public final String COURSE_NO = "Comp411";
	public final String MULTI_COURSE_NO = "Comp790Colab";



	public ADistributedMixedArithmeticColabInputGraderDemoerAndTester(String[] anArgs) {
		super(anArgs);
	}
	@Override
	protected String testDir() {
		return MULTI_TEST_DIR;
	}
	@Override
	protected String correctDir() {
		return MULTI_CORRECT_DIR;
	}
	@Override
	protected String courseNo() {
		return MULTI_COURSE_NO;
	}
	
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		GraderDemoerAndTester aDemoerAndTester = new ADistributedMixedArithmeticColabInputGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(ADistributedMixedArithmeticColabInputGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
