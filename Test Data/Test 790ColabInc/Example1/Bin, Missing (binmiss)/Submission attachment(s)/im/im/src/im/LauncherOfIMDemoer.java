package im;

import echo.modular.LauncherOfModularEchoTester;
import trace.echo.modular.ModularEchoTraceChecker;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;

public class LauncherOfIMDemoer extends LauncherOfModularEchoTester{
	public static DemoerAndTester createDemoer(boolean anInteractive) {
		return new AnIMDemoerAndTester(anInteractive);
	}
	public static DemoerAndTester createDemoer() {
		return new AnIMDemoerAndTester();
	}
	public static void main(String args[]) {
		DemoerAndTester demoer = createDemoer(true);
		MainClassListLauncher aLauncher = demoer.createAndDisplayLauncher();
//		demoer.generateCorrectTranscripts();
		demoer.executeAll();
//		demoer.loadTraceables(null);
//		aLauncher.executeAll();
		
		
//		MainClassListLauncher aLauncher = demo();
//		animatingTest(aLauncher);
	}

}
