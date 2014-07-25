package framework.execution;

import tools.TimedProcess;

/**
 * The interface for different runners to use
 */
public interface Runner {
	

    public RunningProject run(String input) throws NotRunnableException;

    public RunningProject run(String input, int timeout) throws NotRunnableException;

    public RunningProject run(String input, String[] args, int timeout) throws NotRunnableException;

	RunningProject run(String[] command, String input, String[] args,
			int timeout, OutputBasedInputGenerator anOutputBasedInputGenerator) throws NotRunnableException;

	RunningProject run(String anEntryPoint, String input, String[] args,
			int timeout, OutputBasedInputGenerator aDynamicInputProvider) throws NotRunnableException;

	TimedProcess run(RunningProject aRunner, String[] command, String input, String[] args,
			int timeout, String aProcess, boolean wait, OutputBasedInputGenerator anOutputBasedInputGenerator) throws NotRunnableException;

	RunningProject run(String input,
			String[] args,
			int timeout, OutputBasedInputGenerator aDynamicInputProvider) throws NotRunnableException;

}
