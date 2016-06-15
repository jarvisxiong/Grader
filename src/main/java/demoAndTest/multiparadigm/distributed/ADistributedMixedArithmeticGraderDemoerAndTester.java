package demoAndTest.multiparadigm.distributed;

import util.trace.Tracer;
import demoAndTest.GraderDemoerAndTester;
import demoAndTest.multiparadigm.C.ACMixedArithmeticGraderDemoerAndTester;
//import bus.uigen.pipe.DemoerAndTester;
/*
 * This is different from DemoerAndTester as it uses Assignment 1 rather than Assignment 3
 * The steps and grading criteria are hardwired
 * I suppose the test and correct directories could be made as parameters
 */
public class ADistributedMixedArithmeticGraderDemoerAndTester extends ACMixedArithmeticGraderDemoerAndTester {
	
	//	 String[] args ;
//	public final  String TEST_DIR = "Test Data/Test Java";
//	public final  String CORRECT_DIR = "Test Data/Correct C";
//	public final String COURSE_NO = "Comp411";
	public static final  String TEST_DIR = "Test Data/Test 734";
	public static final  String CORRECT_DIR = "Test Data/Correct 734";
	public static final String COURSE_NO = "Comp734";
	public static final int LONG_PAUSE_SECONDS = 160; // distributed, takes along time



	public ADistributedMixedArithmeticGraderDemoerAndTester(String[] anArgs) {
		super(anArgs);
	}
	@Override
	protected String testDir() {
		return TEST_DIR;
	}
	@Override
	protected String correctDir() {
		return CORRECT_DIR;
	}
	@Override
	protected String courseNo() {
		return COURSE_NO;
	}
	@Override
	protected String assignmentNo() {
		return "Assignment1";
	}
	@Override
	protected int getLongAutoPauseSeconds() {
		return LONG_PAUSE_SECONDS;
	}
	
	
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		GraderDemoerAndTester aDemoerAndTester = new ADistributedMixedArithmeticGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(ADistributedMixedArithmeticGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
