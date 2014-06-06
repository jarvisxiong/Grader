package framework.execution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Semaphore;

import tools.TimedProcess;
import util.misc.Common;
import util.misc.ThreadSupport;
import util.trace.Tracer;
import wrappers.framework.project.ProjectWrapper;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import framework.utils.GradingEnvironment;
import grader.config.StaticConfigurationUtils;
import grader.execution.EntryPointNotFound;
import grader.execution.ExecutionSpecification;
import grader.execution.ExecutionSpecificationSelector;
import grader.execution.JavaMainClassFinderSelector;
import grader.execution.MainClassFinder;
import grader.execution.TagNotFound;
import grader.language.LanguageDependencyManager;
import grader.project.MultipleClassesWithTag;
import grader.project.NoClassWithTag;
import grader.project.file.ARootCodeFolder;
import grader.sakai.project.SakaiProject;
import grader.trace.execution.UserProcessExecutionFinished;
import grader.trace.execution.UserProcessExecutionStarted;
import grader.trace.execution.UserProcessExecutionTimedOut;

/**
 * This runs the program in a new process.
 */
public class ProcessRunner implements Runner {

	private Map<String, String> entryPoints;
	private File folder;
	Project project;

	public ProcessRunner(Project aProject) throws NotRunnableException {
		try {
			// entryPoint = getEntryPoint(aProject);
			// entryPoint =
			// JavaMainClassFinderSelector.getMainClassFinder().getEntryPoint(aProject);

			// ideally, should gather entry points only if they are required
			// but need the entry point to find execution folder
			// need to make this entry points so that we can execute distributed
			// programs
			entryPoints = LanguageDependencyManager.getMainClassFinder()
					.getEntryPoints(aProject);

			// throw an exception if no entry point)
			folder = aProject.getBuildFolder(entryPoints
					.get(MainClassFinder.MAIN_ENTRY_POINT));
			// entryPoint = folder + "\\" + entryPoint;
			project = aProject;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotRunnableException();
		}
	}

	// use it without project
	public ProcessRunner() throws NotRunnableException {

	}

	public Map<String, String> getEntryPoints() {
		if (entryPoints == null) {
			entryPoints = LanguageDependencyManager.getMainClassFinder()
					.getEntryPoints(project);

		}
		return entryPoints;
	}

	public File getFolder() {
		return getFolder(getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT));
	}

	public File getFolder(String aMainClass) {
		if (folder == null) {
			try {
				folder = project.getBuildFolder(aMainClass);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return folder;
	}

	// use it without project
	public ProcessRunner(File aFolder) throws NotRunnableException {
		folder = aFolder;

	}

	// /**
	// * This figures out what class is the "entry point", or, what class has
	// * main(args)
	// *
	// * @param project
	// * The project to run
	// * @return The class canonical name. i.e. "foo.bar.SomeClass"
	// * @throws NotRunnableException
	// */
	// private String getEntryPoint(Project project) throws NotRunnableException
	// {
	// if (project.getClassesManager().isEmpty())
	// throw new NotRunnableException();
	//
	// ClassesManager manager = project.getClassesManager().get();
	// for (ClassDescription description : manager.getClassDescriptions()) {
	// try {
	// description.getJavaClass().getMethod("main", String[].class);
	// return description.getJavaClass().getCanonicalName();
	// } catch (NoSuchMethodException e) {
	// // Move along
	// }
	// }
	// throw new NotRunnableException();
	// }

	/**
	 * This runs the project with no arguments
	 * 
	 * @param input
	 *            The input to use as standard in for the process
	 * @return A RunningProject object which you can use for synchronization and
	 *         acquiring output
	 * @throws NotRunnableException
	 */
	@Override
	public RunningProject run(String input) throws NotRunnableException {
		return run(input, -1);
	}

	/**
	 * This runs the project with no arguments with a timeout
	 * 
	 * @param input
	 *            The input to use as standard in for the process
	 * @param timeout
	 *            The timeout, in seconds. Set to -1 for no timeout
	 * @return A RunningProject object which you can use for synchronization and
	 *         acquiring output
	 * @throws NotRunnableException
	 */
	@Override
	public RunningProject run(String input, int timeout)
			throws NotRunnableException {
		return run(input, new String[] {}, timeout);
	}

	/**
	 * This runs the project providing input and arguments
	 * 
	 * @param input
	 *            The input to use as standard in for the process
	 * @param args
	 *            The arguments to pass in
	 * @param timeout
	 *            The timeout, in seconds. Set to -1 for no timeout
	 * @return A RunningProject object which you can use for synchronization and
	 *         acquiring output
	 * @throws NotRunnableException
	 */
	@Override
	public RunningProject run(String input, String[] args, int timeout)
			throws NotRunnableException {
		// String[] command =
		// StaticConfigurationUtils.getExecutionCommand(folder,
		// entryPoints.get(0));
		// return run(command, input, args, timeout);
		return run(getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT),
				input, args, timeout);

	}

	public String classWithEntryTagTarget(String anEntryTag) {
		if (anEntryTag == null)
			return "";
		if (project instanceof ProjectWrapper) {
			grader.project.Project graderProject = ((ProjectWrapper) project)
					.getProject();
			grader.project.ClassDescription aClassDescription = graderProject
					.getClassesManager()
					.tagToUniqueClassDescription(anEntryTag);
			return aClassDescription.getClassName();
			// if (aClassDescriptions.size() == 0) {
			// throw NoClassWithTag.newCase(this, anEntryTag);
			// } else if (aClassDescriptions.size() > 0) {
			// throw MultipleClassesWithTag.newCase(this, anEntryTag);
			// }
			// for (grader.project.ClassDescription
			// aClassDescription:aClassDescriptions) {
			// return aClassDescription.getClassName();
			//
			// }
		}
		return null; // this should never be executed
	}

	// make them global variables so while waiting someone can query them,
	// probbaly breaks least privelege
	// these are needed to allow asynchronous start of new processes
	Map<String, TimedProcess> nameToProcess = new HashMap();
	List<String> startedProcesses = new ArrayList();
	List<String> pendingProcesses = new ArrayList();
	List<String> receivedTags = new ArrayList();
	int timeout = 0;
	ExecutionSpecification executionSpecification;
	Map<String, String> processToInput;
	String processTeam;
	List<String> processes;
	RunningProject runner;
	List<String> processesWithStartTags;
	ExecutionSpecification anExecutionSpecification;

	public RunningProject run(String aProcessTeam,
			Map<String, String> aProcessToInput, int aTimeout)
			throws NotRunnableException {
		anExecutionSpecification = ExecutionSpecificationSelector
				.getExecutionSpecification();
		processTeam = aProcessTeam;
		processToInput = aProcessToInput;
		timeout = aTimeout;

		processes = anExecutionSpecification.getProcesses(aProcessTeam);

		runner = new RunningProject(project);
		for (String aProcess : processes) {
			List<String> startTags = anExecutionSpecification
					.getStartTags(aProcess);
			if (startTags == null || startTags.isEmpty()) {
				processesWithStartTags.add(aProcess);
				pendingProcesses.add(aProcess);
				continue;
			}
			runTeamProcess(aProcess);

			// List<String> basicCommand =
			// StaticConfigurationUtils.getBasicCommand(aProcess);
			// String anEntryPoint;
			// if (StaticConfigurationUtils.hasEntryPoint(basicCommand)); {
			// anEntryPoint = anExecutionSpecification.getEntryPoint(aProcess);
			// if (anEntryPoint == null)
			// throw EntryPointNotFound.newCase(this);
			// }
			// if (anEntryPoint != null && !anEntryPoint.isEmpty()) {
			// getFolder(anEntryPoint);
			// }
			// String anEntryTag = null;
			// if (StaticConfigurationUtils.hasEntryTag(basicCommand))
			// anEntryTag = anExecutionSpecification.getEntrytag(aProcess);
			// // if (anEntryTag != null ) {
			// // getFolder(anEntryTag);
			// // }
			// String aClassWithEntryTag = null;
			// if (anEntryTag != null) {
			// aClassWithEntryTag = classWithEntryTagTarget(anEntryTag);
			// if (aClassWithEntryTag == null)
			// throw TagNotFound.newCase(anEntryTag, this);
			// }
			// if (aClassWithEntryTag != null && folder == null) {
			//
			// getFolder(aClassWithEntryTag);
			// }
			// String[] anArgs =
			// anExecutionSpecification.getArgs(aProcess).toArray(new
			// String[0]);
			// int aSleepTime = anExecutionSpecification.getSleepTime(aProcess);
			// String[] command = StaticConfigurationUtils.getExecutionCommand(
			// aProcess,
			// folder,
			// anEntryPoint,
			// aClassWithEntryTag,
			// anArgs);
			// TimedProcess aTimedProcess = run(runner, command,
			// aProcessToInput.get(aProcess), anArgs, aTimeout, aProcess,
			// false); // do not wait for process to finish
			// nameToProcess.put(aProcess, aTimedProcess);
			// ThreadSupport.sleep(aSleepTime);
			// // some processes may be added dynamically on firing of events,
			// will support them later
			//

		}
		waitForDynamicProcesses();
		waitForStartedProcesses();
		// for (String
		// aTerminatingProcess:anExecutionSpecification.getTerminatingProcesses(aProcessTeam))
		// {
		// TimedProcess aTimedProcess = nameToProcess.get(aTerminatingProcess);
		// try {
		// aTimedProcess.wait(aTimeout);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		return runner;
		// String[] command =
		// StaticConfigurationUtils.getExecutionCommand(folder,
		// entryPoints.get(0));
		// return run(command, input, args, timeout);
		// return run (entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT), input,
		// args, timeout);

	}

	void runTeamProcess(String aProcess) {
		List<String> basicCommand = StaticConfigurationUtils
				.getBasicCommand(aProcess);
		String anEntryPoint;
		if (StaticConfigurationUtils.hasEntryPoint(basicCommand))
			;
		{
			anEntryPoint = anExecutionSpecification.getEntryPoint(aProcess);
			if (anEntryPoint == null)
				throw EntryPointNotFound.newCase(this);
		}
		if (anEntryPoint != null && !anEntryPoint.isEmpty()) {
			getFolder(anEntryPoint);
		}
		String anEntryTag = null;
		if (StaticConfigurationUtils.hasEntryTag(basicCommand))
			anEntryTag = anExecutionSpecification.getEntrytag(aProcess);
		// if (anEntryTag != null ) {
		// getFolder(anEntryTag);
		// }
		String aClassWithEntryTag = null;
		if (anEntryTag != null) {
			aClassWithEntryTag = classWithEntryTagTarget(anEntryTag);
			if (aClassWithEntryTag == null)
				throw TagNotFound.newCase(anEntryTag, this);
		}
		if (aClassWithEntryTag != null && folder == null) {

			getFolder(aClassWithEntryTag);
		}
		String[] anArgs = anExecutionSpecification.getArgs(aProcess).toArray(
				new String[0]);
		int aSleepTime = anExecutionSpecification.getSleepTime(aProcess);
		String[] command = StaticConfigurationUtils.getExecutionCommand(
				aProcess, folder, anEntryPoint, aClassWithEntryTag, anArgs);
		TimedProcess aTimedProcess = run(runner, command,
				processToInput.get(aProcess), anArgs, timeout, aProcess, false); // do
																					// not
																					// wait
																					// for
																					// process
																					// to
																					// finish
		nameToProcess.put(aProcess, aTimedProcess);
		ThreadSupport.sleep(aSleepTime);
		// some processes may be added dynamically on firing of events, will
		// support them later
	}

	synchronized void waitForDynamicProcesses() {
		while (pendingProcesses != null && !pendingProcesses.isEmpty())
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	// to be called by callback that creates a dynamic process
	synchronized void notifyDynamicProcessCreation() {
		this.notify();
	}

	void waitForStartedProcesses() {
		for (String aTerminatingProcess : anExecutionSpecification
				.getTerminatingProcesses(processTeam)) {
			TimedProcess aTimedProcess = nameToProcess.get(aTerminatingProcess);
			try {
				aTimedProcess.wait(timeout);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	void terminateTeam() {
		Set<String> aProcesses = nameToProcess.keySet();
		for (String aProcess : aProcesses) {
			TimedProcess timedProcess = nameToProcess.get(aProcess);
			timedProcess.getProcess().destroy();
		}
		terminateRunner();
		// try {
		// // Wait for the output to finish
		// outputSemaphore.acquire();
		// errorSemaphore.acquire();
		// runner.end();
		// } catch (Exception e) {
		// e.printStackTrace();
		// Tracer.error(e.getMessage());
		// runner.error();
		// runner.end();
		// }
	}

	void terminateRunner() {
		try {
			// Wait for the output to finish
			outputSemaphore.acquire();
			errorSemaphore.acquire();
			runner.end();
		} catch (Exception e) {
			e.printStackTrace();
			Tracer.error(e.getMessage());
			runner.error();
			runner.end();
		}
	}

	@Override
	public RunningProject run(String anEntryPoint, String input, String[] args,
			int timeout) throws NotRunnableException {
		String[] command = StaticConfigurationUtils.getExecutionCommand(folder,
				anEntryPoint);
		return run(command, input, args, timeout);

	}

	public RunningProject run(String[] command, String input, String[] args,
			int timeout) throws NotRunnableException {
		RunningProject retVal = new RunningProject(project);

		TimedProcess process = run(retVal, command, input, args, timeout,
				MainClassFinder.MAIN_ENTRY_POINT, true);
		return retVal;
	}

	final Semaphore outputSemaphore = new Semaphore(1);
	final Semaphore errorSemaphore = new Semaphore(1);

	@Override
	public TimedProcess run(RunningProject runner, String[] command,
			String input, String[] args, int timeout, String aProcessName,
			boolean wait) throws NotRunnableException {
		// final RunningProject runner = new RunningProject(project);
		if (project != null && project instanceof ProjectWrapper) {
			SakaiProject sakaiProject = ((ProjectWrapper) project).getProject();
			sakaiProject.setCurrentInput(input);
			sakaiProject.setCurrentArgs(args);
		}
		TimedProcess process = null;
		try {
			runner.start();

			// // Prepare to run the process
			String classPath = GradingEnvironment.get().getClasspath();
			// // ProcessBuilder builder = new ProcessBuilder("java", "-cp",
			// GradingEnvironment.get()
			// // .getClasspath(), entryPoint);
			// String[] command =
			// StaticConfigurationUtils.getExecutionCommand(entryPoint);
			ProcessBuilder builder;
			if (command.length == 0) { // this part should not execute, onlyif
										// command is null

				// Prepare to run the process
				// ProcessBuilder builder = new ProcessBuilder("java", "-cp",
				// GradingEnvironment.get().getClasspath(), entryPoint);
				builder = new ProcessBuilder("java", "-cp", GradingEnvironment
						.get().getClasspath(), getEntryPoints().get(
						MainClassFinder.MAIN_ENTRY_POINT));
				System.out.println("Running process: java -cp \"" + classPath
						+ "\" "
						+ entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT));
			} else {
				builder = new ProcessBuilder(command);
				System.out.println("Running command:"
						+ Common.toString(command, " "));
			}
			// ProcessBuilder builder = new ProcessBuilder("java", "-cp",
			// classPath, entryPoint);
			builder.directory(folder);
			// System.out.println("Running process: java -cp \""
			// + GradingEnvironment.get().getClasspath() + "\" " + entryPoint);
			// System.out.println("Running process: java -cp \""
			// + classPath + "\" " + entryPoint);
			if (folder != null)
				System.out.println("Running in folder: "
						+ folder.getAbsolutePath());

			// Start the process
			// TimedProcess process = new TimedProcess(builder, timeout);
			process = new TimedProcess(builder, timeout);

			Process processObj = process.start();
			if (folder != null)
				UserProcessExecutionStarted.newCase(
						folder.getAbsolutePath(),
						(entryPoints != null) ? entryPoints
								.get(MainClassFinder.MAIN_ENTRY_POINT) : null,
						classPath, this);

			// Print output to the console
			// InputStream processOut = process.getInputStream();
			// final Scanner scanner = new Scanner(processOut);
			// final Semaphore outputSemaphore = new Semaphore(1);
			// outputSemaphore.acquire();
			//
			// new Thread(new Runnable() {
			// @Override
			// public void run() {
			// while (scanner.hasNextLine()) {
			// String line = scanner.nextLine();
			// System.out.println(line);
			// runner.appendOutput(line + "\n");
			// }
			// scanner.close();
			// outputSemaphore.release();
			// }
			// }).start();

			// Print output to the console
			// InputStream processOut = process.getInputStream();
			// final Scanner scanner = new Scanner(processOut);
			// making this a global variable
			// final Semaphore outputSemaphore = new Semaphore(1);
			Runnable outRunnable = new ARunnerOutputStreamProcessor(
					process.getInputStream(), runner, outputSemaphore,
					aProcessName);

			Thread outThread = new Thread(outRunnable);
			outThread.setName("Out Stream Runnable");
			outThread.start();
			// outputSemaphore.acquire();

			// new Thread(new Runnable() {
			// @Override
			// public void run() {
			// while (scanner.hasNextLine()) {
			// String line = scanner.nextLine();
			// System.out.println(line);
			// runner.appendOutput(line + "\n");
			// }
			// scanner.close();
			// outputSemaphore.release();
			// }
			// }).start();

			// // Print error output to the console
			// InputStream processErrorOut = process.getErrorStream();
			// final Scanner errorScanner = new Scanner(processErrorOut);
			// final Semaphore errorSemaphore = new Semaphore(1);
			// errorSemaphore.acquire();
			//
			// new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			// while (errorScanner.hasNextLine()) {
			// String line = errorScanner.nextLine();
			// System.err.println(line);
			// runner.appendErrorOutput(line + "\n");
			// }
			// errorScanner.close();
			// errorSemaphore.release();
			// }
			// }).start();
			// ;

			// Print error output to the console
			// InputStream processErrorOut = process.getErrorStream();
			// final Scanner errorScanner = new Scanner(processErrorOut);

			// making it a global variable
			// final Semaphore errorSemaphore = new Semaphore(1);

			// errorSemaphore.acquire();
			Runnable errorRunnable = new ARunnerErrorStreamProcessor(
					process.getErrorStream(), runner, errorSemaphore,
					aProcessName);
			Thread errorThread = new Thread(errorRunnable);
			errorThread.setName("Error Stream Runnable");
			errorThread.start();
			// (new Thread (new AnErrorStreamProcessor(process,
			// runner)).start();

			// new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			// while (errorScanner.hasNextLine()) {
			// String line = errorScanner.nextLine();
			// System.err.println(line);
			// runner.appendErrorOutput(line + "\n");
			// }
			// errorScanner.close();
			// errorSemaphore.release();
			// }
			// }).start();
			// ;

			// Write to the process
			// This can be done after the process is started, so one can create
			// a coordinator of various processes
			// using the output of one process to influence the input of another
			OutputStreamWriter processIn = new OutputStreamWriter(
					process.getOutputStream());
			processIn.write(input);
			processIn.flush();
			processIn.close();

			if (wait) {

				// Wait for it to finish
				try {
					process.waitFor();
					UserProcessExecutionFinished.newCase(
							folder.getAbsolutePath(),
							entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT),
							classPath, this);
				} catch (Exception e) {
					outputSemaphore.release();
					errorSemaphore.release();
					UserProcessExecutionTimedOut.newCase(
							folder.getAbsolutePath(),
							entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT),
							classPath, this);

					System.out
							.println("*** Timed out waiting for process to finish ***");
					// avoiding hanging processes
					// processIn.flush();
					// processIn.close();
					// process.getProcess().destroy();
				}
				// }

				processObj.destroy();
				// if (wait) {
				// Wait for the output to finish
				outputSemaphore.acquire();
				errorSemaphore.acquire();
				runner.end();
			}
			// }

		} catch (Exception e) {
			e.printStackTrace();
			Tracer.error(e.getMessage());
			runner.error();
			runner.end();
		}
		// return runner;
		return process;
	}
}
