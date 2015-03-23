package demoAndTest.incrementalInput.java;

import demoAndTest.GraderDemoerAndTester;
import tools.DirectoryUtils;
import util.trace.Tracer;
/*
 * This is a test not of the student programs but of the grader on Java non distributed programs
 */
public class AJavaMixedArithmeticIncrementalInputGraderTester {
	public static void main (String[] anArgs) {
		GraderDemoerAndTester demoerAndTester = new AJavaMixedArithmeticIncrementalInputGraderDemoerAndTester(anArgs);

		demoerAndTester.setAutoProceed(true);
		demoerAndTester.setGeneratingCorrectDir(false);
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		demoerAndTester.demoAndTest();

		
	}

}
