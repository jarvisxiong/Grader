package framework.execution;

import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import framework.utils.GradingEnvironment;
import grader.config.StaticConfigurationUtils;
import grader.execution.JavaMainClassFinderSelector;
import grader.execution.MainClassFinder;
import grader.language.LanguageDependencyManager;
import grader.project.file.ARootCodeFolder;
import grader.trace.execution.MainClassFound;
import grader.trace.execution.MainClassNotFound;
import tools.TimedProcess;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * This runs the program in a new process.
 */
public class InteractiveConsoleProcessRunner implements Runner {

    private Map<String, String> entryPoints;
    private File folder;
    Project project;

    public InteractiveConsoleProcessRunner(Project aProject) throws NotRunnableException {
        try {
//            entryPoint = getEntryPoint(aProject);
//            entryPoint = JavaMainClassFinderSelector.getMainClassFinder().getEntryPoint(aProject);
            entryPoints = LanguageDependencyManager.getMainClassFinder().getEntryPoints(aProject);

            folder = aProject.getBuildFolder(entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT));
            project = aProject;
            MainClassFound.newCase(entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT), project.getSourceFolder().getName(), this);
        } catch (Exception e) {
        	MainClassNotFound.newCase(entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT), project.getSourceFolder().getName(), this);
            throw new NotRunnableException();
        }
    }

//    /**
//     * This figures out what class is the "entry point", or, what class has main(args)
//     * @param project The project to run
//     * @return The class canonical name. i.e. "foo.bar.SomeClass"
//     * @throws framework.execution.NotRunnableException
//     * @see grader.project.AMainClassFinder which repeats this code (sigh)
//     */
//    private String getEntryPoint(Project project) throws NotRunnableException {
//        if (project.getClassesManager().isEmpty())
//            throw new NotRunnableException();
//
//        ClassesManager manager = project.getClassesManager().get();
//        for (ClassDescription description : manager.getClassDescriptions()) {
//            try {
//                description.getJavaClass().getMethod("main", String[].class);
//                return description.getJavaClass().getCanonicalName();
//            } catch (NoSuchMethodException e) {
//            }
//        }
//        throw new NotRunnableException();
//    }

    /**
     * This runs the project with no arguments
     * @param input The input is ignored.
     * @return A RunningProject object which you can use for synchronization and acquiring output
     * @throws framework.execution.NotRunnableException
     */
    @Override
    public RunningProject run(String input) throws NotRunnableException {
        return run(input, -1);
    }

    /**
     * This runs the project with no arguments with a timeout
     * @param input The input is ignored.
     * @param timeout The timeout, in seconds. Set to -1 for no timeout
     * @return A RunningProject object which you can use for synchronization and acquiring output
     * @throws framework.execution.NotRunnableException
     */
    @Override
    public RunningProject run(String input, int timeout) throws NotRunnableException {
        return run(input, new String[]{}, timeout);
    }

    /**
     * This runs the project providing input and arguments
     * @param input The input string is ignored. Instead the console input is used.
     * @param args The arguments to pass in
     * @param timeout The timeout, in seconds. Set to -1 for no timeout
     * @return A RunningProject object which you can use for synchronization and acquiring output
     * @throws framework.execution.NotRunnableException
     */
    @Override
	public RunningProject run(OutputBasedInputGenerator aDynamicInputProvider, String anEntryPoint, String input,
			String[] args, int timeout) throws NotRunnableException {
    	String[] command = StaticConfigurationUtils.getExecutionCommand(folder, anEntryPoint);
    	return run(null, command, input, args, timeout);
	}
    @Override
    public RunningProject run(String input, String[] args, int timeout) throws NotRunnableException {
    	return run (null, entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT), input, args, timeout);
//    	String[] command = StaticConfigurationUtils.getExecutionCommand(folder, entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT));
//    	return run(command, input, args, timeout);
    	

//        final RunningProject runner = new RunningProject(project);
//
//        try {
////            runner.start();
//        	
//        	String[] command = StaticConfigurationUtils.getExecutionCommand(entryPoint);
//        	ProcessBuilder builder;
//        	if (command.length == 0)
//
//            // Prepare to run the process
////            ProcessBuilder builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoint);
//             builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoint);
//        	else
//        		builder = new ProcessBuilder(command);
//
//        	builder.directory(folder);
//
//            // Start the process
//            final TimedProcess process = new TimedProcess(builder, timeout);
//            process.start();
//
//            // Print output to the console
//            InputStreamReader isr = new InputStreamReader(process.getInputStream());
//            final BufferedReader br = new BufferedReader(isr);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        String line = null;
//                        while ((line = br.readLine()) != null)
//                            System.out.println(line);
//                    } catch (IOException e) {
//                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                    }
//                }
//            }).start();
//
//            // Feed console input to the process
//            OutputStreamWriter osw = new OutputStreamWriter(process.getOutputStream());
//            final BufferedWriter bw = new BufferedWriter(osw);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    boolean loop = true;
//                    Scanner scanner = new Scanner(System.in);
//                    while (loop) {
//                        try {
//                            process.getProcess().exitValue();
//                            loop = false;
//                        } catch (IllegalThreadStateException e) {
//
//                            try {
//                                if (scanner.hasNextLine()) {
//                                    bw.write(scanner.nextLine());
//                                    bw.newLine();
//                                    bw.flush();
//                                }
//                                Thread.sleep(50);
//                            } catch (Exception e1) {
//                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                            }
//                        }
//                    }
//                }
//            }).start();
//
//        } catch (Exception e) {
////            runner.error();
////            runner.end();
//        }
//        return runner;
    }

	@Override
	public RunningProject run(OutputBasedInputGenerator anOutputBasedInputGenerator, String[] command, String input,
			String[] args, int timeout) throws NotRunnableException {
		 final RunningProject runner = new RunningProject(project, anOutputBasedInputGenerator,  input);

	        try {
//	            runner.start();
	        	
	        	ProcessBuilder builder;
	        	if (command.length == 0)

	            // Prepare to run the process
//	            ProcessBuilder builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoint);
	             builder = new ProcessBuilder("java", "-cp", GradingEnvironment.get().getClasspath(), entryPoints.get(MainClassFinder.MAIN_ENTRY_POINT));
	        	else
	        		builder = new ProcessBuilder(command);

	        	builder.directory(folder);

	            // Start the process
	            final TimedProcess process = new TimedProcess(builder, timeout);
	            process.start();

	            // Print output to the console
	            InputStreamReader isr = new InputStreamReader(process.getInputStream());
	            final BufferedReader br = new BufferedReader(isr);
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    try {
	                        String line = null;
	                        while ((line = br.readLine()) != null)
	                            System.out.println(line);
	                    } catch (IOException e) {
	                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	                    }
	                }
	            }).start();

	            // Feed console input to the process
	            OutputStreamWriter osw = new OutputStreamWriter(process.getOutputStream());
	            final BufferedWriter bw = new BufferedWriter(osw);
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    boolean loop = true;
	                    Scanner scanner = new Scanner(System.in);
	                    while (loop) {
	                        try {
	                            process.getProcess().exitValue();
	                            loop = false;
	                        } catch (IllegalThreadStateException e) {

	                            try {
	                                if (scanner.hasNextLine()) {
	                                    bw.write(scanner.nextLine());
	                                    bw.newLine();
	                                    bw.flush();
	                                }
	                                Thread.sleep(50);
	                            } catch (Exception e1) {
	                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	                            }
	                        }
	                    }
	                }
	            }).start();

	        } catch (Exception e) {
//	            runner.error();
//	            runner.end();
	        }
	        return runner;
	}

	@Override
	public TimedProcess run(RunningProject aRunner, OutputBasedInputGenerator anOutputBasedInputGenerator,
			String[] command, String input, String[] args, int timeout, String aProcess, boolean wait)
			throws NotRunnableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RunningProject run(OutputBasedInputGenerator aDynamicInputProvider, String input, String[] args,
			int timeout)
			throws NotRunnableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RunningProject run(
			OutputBasedInputGenerator anOutputBasedInputGenerator,
			String input, int timeout) throws NotRunnableException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
