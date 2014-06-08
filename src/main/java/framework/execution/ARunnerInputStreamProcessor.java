package framework.execution;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ARunnerInputStreamProcessor implements RunnerInputStreamProcessor{
	protected OutputStream input;
	protected RunningProject runner;
	protected Semaphore semaphore; //not sure what we can do with this but symmetry with error and out
	protected String processName;
	protected Boolean onlyProcess;
	OutputStreamWriter inputWriter;
	public ARunnerInputStreamProcessor(OutputStream anInput, RunningProject aRunner,  String aProcessName, /*Semaphore aSemaphore,*/ Boolean anOnlyProcess) {
		input = anInput;
		 inputWriter = new OutputStreamWriter(
					anInput);
		runner = aRunner;
//		semaphore = aSemaphore;
		semaphore = new Semaphore(1);
		processName = aProcessName;
		onlyProcess = anOnlyProcess;
	}
	
	public void nextInput(String anInput) {
		try {
			inputWriter.write(anInput);
			inputWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void terminateInput() {
		try {
			inputWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public OutputStream getInput() {
		return input;
	}
	public RunningProject getRunner() {
		return runner;
	}
	public Semaphore getSemaphore() {
		return semaphore;
	}
	public String getProcessName() {
		return processName;
	}
	public Boolean getOnlyProcess() {
		return onlyProcess;
	}
	public OutputStreamWriter getInputWriter() {
		return inputWriter;
	}
	
	


}
