package framework.execution;

import tools.TimedProcess;

/**
 * The interface for different runners to use
 */
public interface Runner {
	

    public RunningProject run(String input) throws NotRunnableException;

    public RunningProject run(String input, int timeout) throws NotRunnableException;
    
    public RunningProject run(OutputBasedInputGenerator anOutputBasedInputGenerator, String input, int timeout) throws NotRunnableException;


    public RunningProject run(String input, String[] args, int timeout) throws NotRunnableException;

	RunningProject run(OutputBasedInputGenerator anOutputBasedInputGenerator, String[] command, String input,
			String[] args, int timeout) throws NotRunnableException;

	RunningProject run(OutputBasedInputGenerator aDynamicInputProvider, String anEntryPoint, String input,
			String[] args, int timeout) throws NotRunnableException;

	TimedProcess run(RunningProject aRunner, OutputBasedInputGenerator anOutputBasedInputGenerator, String[] command, String input,
			String[] args, int timeout, String aProcess, boolean wait) throws NotRunnableException;

	RunningProject run(OutputBasedInputGenerator aDynamicInputProvider,
			String input,
			String[] args, int timeout) throws NotRunnableException;

}
