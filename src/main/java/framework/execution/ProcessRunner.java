package framework.execution;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import tools.TimedProcess;
import util.misc.Common;
import util.trace.Tracer;
import wrappers.framework.project.ProjectWrapper;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import framework.utils.GradingEnvironment;
import grader.config.StaticConfigurationUtils;
import grader.project.JavaMainClassFinderSelector;
import grader.project.file.java.AJavaRootCodeFolder;
import grader.sakai.project.SakaiProject;
import grader.trace.execution.UserProcessExecutionFinished;
import grader.trace.execution.UserProcessExecutionStarted;
import grader.trace.execution.UserProcessExecutionTimedOut;

/**
 * This runs the program in a new process.
 */
public class ProcessRunner implements Runner {

	private String entryPoint;
	private File folder;
	Project project;

	public ProcessRunner(Project aProject) throws NotRunnableException {
		try {
//			entryPoint = getEntryPoint(aProject);
//			 entryPoint = JavaMainClassFinderSelector.getMainClassFinder().getEntryPoint(aProject);
	            entryPoint = AJavaRootCodeFolder.getMainClassFinder().getEntryPoint(aProject);
	            

			 folder = aProject.getBuildFolder(entryPoint);
//			 entryPoint = folder + "\\" + entryPoint;
			project = aProject;
		} catch (Exception e) {
			throw new NotRunnableException();
		}
	}
	// use it without project
	public ProcessRunner() throws NotRunnableException {
		
	}
	
	// use it without project
		public ProcessRunner(File aFolder) throws NotRunnableException {
			folder = aFolder;
			
		}

//	/**
//	 * This figures out what class is the "entry point", or, what class has
//	 * main(args)
//	 * 
//	 * @param project
//	 *            The project to run
//	 * @return The class canonical name. i.e. "foo.bar.SomeClass"
//	 * @throws NotRunnableException
//	 */
//	private String getEntryPoint(Project project) throws NotRunnableException {
//		if (project.getClassesManager().isEmpty())
//			throw new NotRunnableException();
//
//		ClassesManager manager = project.getClassesManager().get();
//		for (ClassDescription description : manager.getClassDescriptions()) {
//			try {
//				description.getJavaClass().getMethod("main", String[].class);
//				return description.getJavaClass().getCanonicalName();
//			} catch (NoSuchMethodException e) {
//				// Move along
//			}
//		}
//		throw new NotRunnableException();
//	}

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
	public RunningProject run(String input, int timeout) throws NotRunnableException {
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
	public RunningProject run(String input, String[] args, int timeout) throws NotRunnableException {
	  	String[] command = StaticConfigurationUtils.getExecutionCommand(folder, entryPoint);
	  	return run(command, input, args, timeout);
//
//		final RunningProject runner = new RunningProject(project);
//		if (project instanceof ProjectWrapper) {
//			SakaiProject sakaiProject = ((ProjectWrapper) project).getProject();
//			sakaiProject.setCurrentInput(input);
//			sakaiProject.setCurrentArgs(args);			
//		}
//
//		try {
//			runner.start();
//
//			// Prepare to run the process
//			String classPath = GradingEnvironment.get()
//					.getClasspath();
////			ProcessBuilder builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get()
////					.getClasspath(), entryPoint);
//		  	String[] command = StaticConfigurationUtils.getExecutionCommand(entryPoint);
//        	ProcessBuilder builder;
//        	if (command.length == 0)
//
//            // Prepare to run the process
////            ProcessBuilder builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoint);
//             builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoint);
//        	else
//        		builder = new ProcessBuilder(command);
////			ProcessBuilder builder = new ProcessBuilder("java", "-cp", classPath, entryPoint);
//			builder.directory(folder);
////			System.out.println("Running process: java -cp \""
////					+ GradingEnvironment.get().getClasspath() + "\" " + entryPoint);
//			System.out.println("Running process: java -cp \""
//					+ classPath + "\" " + entryPoint);
//			System.out.println("Running in folder: " + folder.getAbsolutePath());
//
//			// Start the process
//			TimedProcess process = new TimedProcess(builder, timeout);
//			Process processObj = process.start();
//			UserProcessExecutionStarted.newCase(folder.getAbsolutePath(), entryPoint, classPath, this);
//
//			// Print output to the console
//			InputStream processOut = process.getInputStream();
//			final Scanner scanner = new Scanner(processOut);
//			final Semaphore outputSemaphore = new Semaphore(1);
//			outputSemaphore.acquire();
//
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while (scanner.hasNextLine()) {
//						String line = scanner.nextLine();
//						System.out.println(line);
//						runner.appendOutput(line + "\n");
//					}
//					scanner.close();
//					outputSemaphore.release();
//				}
//			}).start();
//
//			// Print error output to the console
//			InputStream processErrorOut = process.getErrorStream();
//			final Scanner errorScanner = new Scanner(processErrorOut);
//			final Semaphore errorSemaphore = new Semaphore(1);
//			errorSemaphore.acquire();
//
//			new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					while (errorScanner.hasNextLine()) {
//						String line = errorScanner.nextLine();
//						System.err.println(line);
//						runner.appendErrorOutput(line + "\n");
//					}
//					errorScanner.close();
//					errorSemaphore.release();
//				}
//			}).start();
//			;
//
//			// Write to the process
//			OutputStreamWriter processIn = new OutputStreamWriter(process.getOutputStream());
//			processIn.write(input);
//			processIn.flush();
//			processIn.close();
//
//			// Wait for it to finish
//			try {
//				process.waitFor();
//				UserProcessExecutionFinished.newCase(folder.getAbsolutePath(), entryPoint, classPath, this);
//			} catch (Exception e) {
//				outputSemaphore.release();
//				errorSemaphore.release();
//				UserProcessExecutionTimedOut.newCase(folder.getAbsolutePath(), entryPoint, classPath, this);
//
//				System.out.println("*** Timed out waiting for process to finish ***");
//				// avoiding hanging processes 
////				processIn.flush();
////				processIn.close();
////				process.getProcess().destroy();
//			}
//			
//			processObj.destroy();
//
//			// Wait for the output to finish
//			outputSemaphore.acquire();
//			errorSemaphore.acquire();
//			runner.end();
//
//		} catch (Exception e) {
//			runner.error();
//			runner.end();
//		}
//		return runner;
	}
	@Override
	public RunningProject run(String[] command, String input, String[] args, int timeout) throws NotRunnableException {
		final RunningProject runner = new RunningProject(project);
		if (project != null && project instanceof ProjectWrapper) {
			SakaiProject sakaiProject = ((ProjectWrapper) project).getProject();
			sakaiProject.setCurrentInput(input);
			sakaiProject.setCurrentArgs(args);			
		}

		try {
			runner.start();

//			// Prepare to run the process
			String classPath = GradingEnvironment.get()
					.getClasspath();
////			ProcessBuilder builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get()
////					.getClasspath(), entryPoint);
//		  	String[] command = StaticConfigurationUtils.getExecutionCommand(entryPoint);
        	ProcessBuilder builder;
        	if (command.length == 0) {

            // Prepare to run the process
//            ProcessBuilder builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoint);
             builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoint);
         	System.out.println("Running process: java -cp \""
					+ classPath + "\" " + entryPoint);
        	}   	else {
        		builder = new ProcessBuilder(command);
        		System.out.println("Running command:" + Common.toString(command, " "));
        	}
//			ProcessBuilder builder = new ProcessBuilder("java", "-cp", classPath, entryPoint);
			builder.directory(folder);
//			System.out.println("Running process: java -cp \""
//					+ GradingEnvironment.get().getClasspath() + "\" " + entryPoint);
//			System.out.println("Running process: java -cp \""
//					+ classPath + "\" " + entryPoint);
			if (folder != null)
			System.out.println("Running in folder: " + folder.getAbsolutePath());

			// Start the process
			TimedProcess process = new TimedProcess(builder, timeout);
			Process processObj = process.start();
			if (folder != null)
			UserProcessExecutionStarted.newCase(folder.getAbsolutePath(), entryPoint, classPath, this);

			// Print output to the console
			InputStream processOut = process.getInputStream();
			final Scanner scanner = new Scanner(processOut);
			final Semaphore outputSemaphore = new Semaphore(1);
			outputSemaphore.acquire();

			new Thread(new Runnable() {
				@Override
				public void run() {
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						System.out.println(line);
						runner.appendOutput(line + "\n");
					}
					scanner.close();
					outputSemaphore.release();
				}
			}).start();

			// Print error output to the console
			InputStream processErrorOut = process.getErrorStream();
			final Scanner errorScanner = new Scanner(processErrorOut);
			final Semaphore errorSemaphore = new Semaphore(1);
			errorSemaphore.acquire();

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (errorScanner.hasNextLine()) {
						String line = errorScanner.nextLine();
						System.err.println(line);
						runner.appendErrorOutput(line + "\n");
					}
					errorScanner.close();
					errorSemaphore.release();
				}
			}).start();
			;

			// Write to the process
			OutputStreamWriter processIn = new OutputStreamWriter(process.getOutputStream());
			processIn.write(input);
			processIn.flush();
			processIn.close();

			// Wait for it to finish
			try {
				process.waitFor();
				UserProcessExecutionFinished.newCase(folder.getAbsolutePath(), entryPoint, classPath, this);
			} catch (Exception e) {
				outputSemaphore.release();
				errorSemaphore.release();
				UserProcessExecutionTimedOut.newCase(folder.getAbsolutePath(), entryPoint, classPath, this);

				System.out.println("*** Timed out waiting for process to finish ***");
				// avoiding hanging processes 
//				processIn.flush();
//				processIn.close();
//				process.getProcess().destroy();
			}
			
			processObj.destroy();

			// Wait for the output to finish
			outputSemaphore.acquire();
			errorSemaphore.acquire();
			runner.end();

		} catch (Exception e) {
			Tracer.error(e.getMessage());
			runner.error();
			runner.end();
		}
		return runner;
	}
}
