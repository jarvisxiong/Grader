package framework.execution;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import tools.TimedProcess;

public abstract class ARunnerErrorOrOutStreamProcessor implements Runnable {
	protected Scanner scanner ;
	protected InputStream errorOrOut;
	protected RunningProject runner;
	protected Semaphore semaphore;
	public ARunnerErrorOrOutStreamProcessor(InputStream aProcessErrorOrOut, RunningProject aRunner, Semaphore aSemaphore) {
		// Print error output to the console
		errorOrOut = aProcessErrorOrOut;
		scanner = new Scanner(errorOrOut);
		runner = aRunner;
		semaphore = aSemaphore;
	}
		

			@Override
			public void run() {
				try {
				semaphore.acquire();
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					System.err.println(line);
//					runner.appendErrorOutput(line + "\n");
					append(line + "\n");
				}
				scanner.close();
				semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		abstract void append(String s);
	}
