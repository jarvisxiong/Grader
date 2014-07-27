package framework.execution;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import tools.TimedProcess;

public class ARunnerOutputStreamProcessor extends ARunnerErrorOrOutStreamProcessor implements Runnable {
//	protected Scanner scanner ;
//	protected InputStream out;
//	protected RunningProject runner;
	public ARunnerOutputStreamProcessor(InputStream aProcessErrorOut, RunningProject aRunner, /*Semaphore aSemaphore,*/ String aProcessName, Boolean anOnlyProcess) {
		super(aProcessErrorOut, aRunner, /*aSemaphore,*/ aProcessName, anOnlyProcess);
	}

@Override
public void processLine(String s) {
	System.out.println("Process line:" + s);
	runner.appendCumulativeOutput(s + "\n"); // append cumulative output
//	if (processName != null) {
//		System.out.println(s);

//		runner.appendErrorOutput(processName, s + "\n");
		runner.appendProcessOutput(processName, s + "\n"); // append this process output
//	}
	
}
		

//			@Override
//			public void run() {
//				while (scanner.hasNextLine()) {
//					String line = scanner.nextLine();
//					System.err.println(line);
//					runner.appendErrorOutput(line + "\n");
//				}
//				scanner.close();
//			}
		
	}
