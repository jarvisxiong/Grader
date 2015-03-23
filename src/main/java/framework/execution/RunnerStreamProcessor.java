package framework.execution;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public interface RunnerStreamProcessor {
	

	public RunningProject getRunner() ;


	public Semaphore getSemaphore() ;


	public String getProcessName() ;

	public Boolean getOnlyProcess() ;

}
