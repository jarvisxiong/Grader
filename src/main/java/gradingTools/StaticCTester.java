package gradingTools;

import tools.DirectoryUtils;
import util.trace.Tracer;
/*
 * This is a test not of the student programs but of the grader on C non distributed programs
 */
public class StaticCTester {
	public static void main (String[] args) {
		StaticCDemoerAndTester.setAutoProceed(true);
		StaticCDemoerAndTester.setGeneratingCorrectDir(false);
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		StaticCDemoerAndTester.main(args);
		
	}

}
