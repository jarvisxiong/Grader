package gradingTools;

import tools.DirectoryUtils;
import util.trace.Tracer;

public class CTest {
	public static void main (String[] args) {
		CDemoerAndTester.setAutoProceed(true);
		CDemoerAndTester.setGeneratingCorrectDir(false);
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		CDemoerAndTester.main(args);
		
	}

}
