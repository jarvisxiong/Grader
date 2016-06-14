package framework.project;

import framework.execution.BasicRunningProject;
import framework.execution.NotRunnableException;
import framework.execution.RunningProject;

import org.joda.time.DateTime;

import scala.Option;
import util.pipe.InputGenerator;
import util.trace.TraceableLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Based on {@link grader.project.Project}
 */
public interface Project {
	public static final String SOURCE = "src";
	public static final String BINARY_0 = "classes";

	public static final String BINARY = "bin";
    public static final String BINARY_2 = "out";
    public static final String BINARY_3 = "build"; // net beans

    /**
     * Attempts to start the project in the same process
     */
    public BasicRunningProject start(String input) throws NotRunnableException;

    /**
     * Attempts to launch the project in a new process
     */
    public BasicRunningProject launch(String input) throws NotRunnableException;

    /**
     * Attempts to start the project in the same process
     */
    public BasicRunningProject start(String input, int timeout) throws NotRunnableException;

    /**
     * Attempts to launch the project in a new process
     */
    public BasicRunningProject launch(InputGenerator anOutputBasedInputGenerator, String input, int timeout) throws NotRunnableException;

    public BasicRunningProject launch(String input, int timeout) throws NotRunnableException;

    public BasicRunningProject launchInteractive() throws NotRunnableException;

    /**
     * @return The {@link ClassesManager} for this project. This can be used to look at the source code.
     */
    public Option<ClassesManager> getClassesManager();

    /**
     * @return The source code folder
     */
    public File getSourceFolder();

    /**
     * @return The bin/out/target folder
     */
    public File getBuildFolder(String preferredClass) throws FileNotFoundException;

    /**
     * When the project is run in the same JVM then it should log all tracer bus events. This returns that log
     * @return The traceable log of events.
     */
    public TraceableLog getTraceableLog();

    BasicRunningProject launch(
			InputGenerator anOutputBasedInputGenerator,
			Map<String, String> aProcessToInput, int timeout)
			throws NotRunnableException;

	BasicRunningProject launch(String input, String[] anArgs, int timeout)
			throws NotRunnableException;

	BasicRunningProject launchInteractive(String[] args) throws NotRunnableException;
}
