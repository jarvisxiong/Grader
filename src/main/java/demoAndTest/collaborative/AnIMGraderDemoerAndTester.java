package demoAndTest.collaborative;

import util.trace.Tracer;
import demoAndTest.GraderDemoerAndTester;
import demoAndTest.incrementalInput.distributed.ADistributedMixedArithmeticIncrementalInputGraderDemoerAndTester;
//import bus.uigen.pipe.DemoerAndTester;
/*
 * 

 */
public class AnIMGraderDemoerAndTester extends ADistributedMixedArithmeticIncrementalInputGraderDemoerAndTester {
	
	//	 String[] args ;
	public final  String COLAB_TEST_DIR = "Test Data/Test 790ColabInc";
	public final  String COLAB_CORRECT_DIR = "Test Data/Correct 790ColabInc";
//	public final String COURSE_NO = "Comp411";
	public final String COLAB_COURSE_NO = "Comp790ColabInc";



	public AnIMGraderDemoerAndTester(String[] anArgs) {
		super(anArgs);
	}
	@Override
	protected String testDir() {
		return COLAB_TEST_DIR;
	}
	@Override
	protected String correctDir() {
		return COLAB_CORRECT_DIR;
	}
	@Override
	protected String courseNo() {
		return COLAB_COURSE_NO;
	}
	protected String assignmentNo() {
		return "Example1";
	}
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		GraderDemoerAndTester aDemoerAndTester = new AnIMGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(AnIMGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
