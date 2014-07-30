package echo.monolithic;

import trace.echo.EchoTraceChecker;
import trace.echo.modular.ModularEchoTraceChecker;
import trace.echo.modular.EchoTracerSetter;
import util.misc.ThreadSupport;
import util.trace.console.ConsoleTraceSetter;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;

public class LauncherOfMonolithicEchoCorrectLogGenerator extends LauncherOfMonolithicEchoDemoer{
	public static DemoerAndTester createDemoer() {
		return new AMonolithicEchoDemoerAndTester(false);
	}
	public static void main(String args[]) {
		ConsoleTraceSetter.traceConsole(); // needed for comparing outputs
		DemoerAndTester aDemoer = createDemoer();
		
		Boolean retVal = aDemoer.executeLoadAndTest(true, false);

//		Boolean retVal = aDemoer.executeLoadAndTest(false, true);

		System.out.println("Test result:" + retVal);
//		MainClassListLauncher aLauncher = aDemoer.createAndDisplayLauncher();
//		aDemoer.generateCorrectTranscripts();
//		aDemoer.executeAll();
//		aDemoer.waitForInteractionTermination();
//		try {
//			aLauncher.waitForAll();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		aDemoer.loadTraceables();
////		aLauncher.terminateAll();
//
////		System.out.println(EchoTraceChecker.check(aDemoer.getGlobalTraceableList()));
//		System.out.println(EchoTraceChecker.checkEchoer(aDemoer.getGlobalTraceableList()));
//
//		System.out.println(ModularEchoTraceChecker.checkModularEchoer(aDemoer.getGlobalTraceableList()));

		aDemoer.terminate();
	}

}
