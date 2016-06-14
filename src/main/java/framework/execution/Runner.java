package framework.execution;

import tools.TimedProcess;
import util.pipe.InputGenerator;

/**
 * The interface for different runners to use
 */
public interface Runner {
	

    public BasicRunningProject run(String input) throws NotRunnableException;

    public BasicRunningProject run(String input, int timeout) throws NotRunnableException;
    
    public BasicRunningProject run(InputGenerator anOutputBasedInputGenerator, String input, int timeout) throws NotRunnableException;


    public BasicRunningProject run(String input, String[] args, int timeout) throws NotRunnableException;

    BasicRunningProject run(InputGenerator anOutputBasedInputGenerator, String[] command, String input,
			String[] args, int timeout) throws NotRunnableException;

    BasicRunningProject run(InputGenerator aDynamicInputProvider, String anEntryPoint, String input,
			String[] args, int timeout) throws NotRunnableException;

	TimedProcess run(BasicRunningProject aRunner, InputGenerator anOutputBasedInputGenerator, String[] command, String input,
			String[] args, int timeout, String aProcess, boolean wait) throws NotRunnableException;

	BasicRunningProject run(InputGenerator aDynamicInputProvider,
			String input,
			String[] args, int timeout) throws NotRunnableException;

//	BasicRunningProject runMainClass(Class aClass, String input, String[] args,
//			int timeout) throws NotRunnableException;

}
