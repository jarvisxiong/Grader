package im;

import util.trace.console.ConsoleTraceSetter;
import bus.uigen.models.DemoerAndTester;

public class LauncherOfIMTester extends LauncherOfIMDemoer {
	public static void main(String args[]) {
		// needed for Echo Tracing
		ConsoleTraceSetter.traceConsole();	
		DemoerAndTester aDemoer = createDemoer(true);
//		Boolean retVal = aDemoer.executeLoadAndTest(true, false);
		Boolean retVal = aDemoer.executeLoadAndTest(false, true);

		System.out.println("Test result:" + retVal);
		aDemoer.terminate();
	}

}
