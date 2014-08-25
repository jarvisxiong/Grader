package echo.modular;

import trace.echo.EchoTraceChecker;
import trace.echo.modular.ModularEchoTraceChecker;
import trace.echo.modular.EchoTracerSetter;
import util.misc.ThreadSupport;
import util.trace.console.ConsoleTraceSetter;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;

public class LauncherOfModularCorrectLogGenerator {
	public static DemoerAndTester createDemoer(boolean anInteractive) {
		return new AModularEchoDemoerAndTester(anInteractive);
	}
	public static void main(String args[]) {
		// make sure that AnEchoerComposerAndLauncher has echoing of tracer
		ConsoleTraceSetter.traceConsole();	// needed for Echo Tracer
		DemoerAndTester aDemoer = createDemoer(false);
		// correct
		Boolean retVal = aDemoer.executeLoadAndTest(true, false);
		// test
//		Boolean retVal = aDemoer.executeLoadAndTest(false, true);
		System.out.println("Test result:" + retVal);
		aDemoer.terminate();
	}

}
