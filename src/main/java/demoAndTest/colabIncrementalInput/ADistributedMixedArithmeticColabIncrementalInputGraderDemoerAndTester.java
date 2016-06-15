package demoAndTest.colabIncrementalInput;

import util.trace.Tracer;
import demoAndTest.GraderDemoerAndTester;
import demoAndTest.multiparadigm.distributed.ADistributedMixedArithmeticGraderDemoerAndTester;
//import bus.uigen.pipe.DemoerAndTester;
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
