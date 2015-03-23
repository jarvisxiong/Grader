package echo.monolithic;

import trace.echo.modular.ModularEchoTraceChecker;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;

public class LauncherOfMonolithicEchoDemoer {
	public static DemoerAndTester createDemoer(boolean anInteractive) {
		return new AMonolithicEchoDemoerAndTester(anInteractive);
	}
	public static void main(String args[]) {
		DemoerAndTester aDemoer = createDemoer(true);
		aDemoer.demo();
		aDemoer.terminate();
//		aDemoer.terminate();
//		aDemoer.executeAll();

	}

}
