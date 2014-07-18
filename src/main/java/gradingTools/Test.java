package gradingTools;

import tools.DirectoryUtils;
import util.trace.Tracer;
/*
 * This is a test not of the student programs but of the grader on Java non distributed programs
 */
public class Test {
	public static void main (String[] args) {
		DemoerAndTester.setAutoProceed(true);
		DemoerAndTester.setGeneratingCorrectDir(false);
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		DemoerAndTester.main(args);
		
	}

}
