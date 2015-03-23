package framework.execution;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public interface RunnerErrorOrOutStreamProcessor extends Runnable, RunnerStreamProcessor{
	 void processLine(String s);


	public Scanner getScanner();


	public InputStream getErrorOrOut();


//	

}
