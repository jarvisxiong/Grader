package im.ot;

import im.LauncherOfIMTester;
import echo.modular.LauncherOfModularEchoTester;
import trace.echo.modular.ModularEchoTraceChecker;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;

public class LauncherOfOTDemoer extends LauncherOfIMTester{
	public static DemoerAndTester createDemoer(boolean anInteractive) {
		return new AnOTDemoerAndTester();
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
