package echo.modular;

import trace.echo.modular.ModularEchoTraceChecker;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;

public class LauncherOfModularEchoDemoer {
	public static DemoerAndTester createDemoer(boolean anInteractive) {
		return new AModularEchoDemoerAndTester(anInteractive);
	}
	public static void main(String args[]) {
		DemoerAndTester aDemoer = createDemoer(true);
		aDemoer.demo();
		aDemoer.terminate();

	}

}
