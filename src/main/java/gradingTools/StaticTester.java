package gradingTools;

import tools.DirectoryUtils;
import util.trace.Tracer;
/*
 * This is a test not of the student programs but of the grader on Java non distributed programs
 */
public class StaticTester {
	public static void main (String[] args) {
		StaticDemoerAndTester.setAutoProceed(true);
		StaticDemoerAndTester.setGeneratingCorrectDir(false);
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		StaticDemoerAndTester.main(args);
		
	}

}
