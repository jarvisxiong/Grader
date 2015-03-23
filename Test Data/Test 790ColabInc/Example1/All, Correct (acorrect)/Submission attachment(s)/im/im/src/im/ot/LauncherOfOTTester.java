package im.ot;

import util.trace.console.ConsoleTraceSetter;
import bus.uigen.models.DemoerAndTester;

public class LauncherOfOTTester extends LauncherOfOTDemoer {
	public static void main(String args[]) {
//		ConsoleTraceSetter.traceConsole();	
		DemoerAndTester aDemoer = createDemoer(false);
		// correct transcripts
//		Boolean retVal = aDemoer.executeLoadAndTest(true, false);
		// test transcripts
		Boolean retVal = aDemoer.executeLoadAndTest(false, true);

		System.out.println("Test result:" + retVal);
		aDemoer.terminate();
	}

}
