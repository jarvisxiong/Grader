package gradingTools;

import tools.DirectoryUtils;
import util.trace.Tracer;
/*
 * This is a test not of the student programs but of the grader on C non distributed programs
 */
public class CTest {
	public static void main (String[] args) {
		CDemoerAndTester.setAutoProceed(true);
		CDemoerAndTester.setGeneratingCorrectDir(false);
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		CDemoerAndTester.main(args);
		
	}

}
