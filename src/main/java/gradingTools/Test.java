package gradingTools;

import tools.DirectoryUtils;
import util.trace.Tracer;

public class Test {
	public static void main (String[] args) {
		DemoerAndTester.setAutoProceed(true);
		DemoerAndTester.setGeneratingCorrectDir(false);
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		DemoerAndTester.main(args);
		
	}

}
