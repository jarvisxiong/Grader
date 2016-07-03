package grader.execution;

import grader.basics.execution.NotRunnableException;
import grader.project.flexible.FlexibleProject;
import grader.sakai.project.SakaiProject;
import grader.trace.execution.UserThreadExecutionFinished;
import grader.trace.execution.UserThreadExecutionStarted;
import grader.trace.overall_transcript.OverallTranscriptSaved;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import util.misc.TeePrintStream;

public class AReflectionBasedProjectRunner implements Runnable {
    public static final String DEFAULT_OUTPUT_FILE_NAME = "output.txt";

    String projectName;
    String mainClassName;
    String[][] mainArgs;
    FlexibleProject project;
    String[] outputFiles;
    String[] inputFiles;
    Method mainMethod;
    Class mainClass;
    boolean appendedToTranscript;


    public AReflectionBasedProjectRunner(String aMainClassName, String[][] aMainArgs, FlexibleProject aProject, String[] anInputFiles, String[] anOutputFiles, Class aMainClass, Method aMainMethod) {
        projectName = aProject.getProjectFolderName();
        mainClassName = aMainClassName;
        mainArgs = aMainArgs;
        project = aProject;
        outputFiles = anOutputFiles;
        inputFiles = anInputFiles;
        mainMethod = aMainMethod;
        mainClass = aMainClass;
    }
    
    public static boolean setCurrentDirectory(String directory_name)
    {
        boolean result = false;  // Boolean indicating whether directory was set
        File    directory;       // Desired current working directory

        directory = new File(directory_name).getAbsoluteFile();
        if (directory.exists() || directory.mkdirs())
        {
            result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
        }

        return result;
    }
    
    public static String getCurrentDirectory() {
		return  System.getProperty("user.dir");
	}

    public void run() {
        try {

            InputStream stdin = null;
            PrintStream stdout = null;
            PrintStream originalOut = System.out;
            if (inputFiles.length == 0) {
                inputFiles = new String[1];
            }

            if (outputFiles.length == 0) {
                outputFiles = new String[]{project.getOutputFileName()};
                appendedToTranscript = true;
            }
            if (mainArgs.length == 0) {
                mainArgs = new String[1][];
            }

            for (int i = 0; i < inputFiles.length; i++) {

                String inputFile = inputFiles[i];
                String outputFile = outputFiles[i];
                Object[] args = {mainArgs[i]};
                if (inputFile != null) {
                    stdin = new FileInputStream(inputFile);
                }
                if (outputFile != null) {
                    stdout = new TeePrintStream(new FileOutputStream(outputFile), originalOut);
                }
                if (stdin != null) {
                    System.setIn(stdin);
                }
                if (stdout != null) {
                    System.setOut(stdout);
                }
//                boolean changedWorkingDirectory = false;
//            	String previousDir = System.getProperty("user.dir");
//                System.out.println("Current dir using System:" + previousDir);
                try {
                	UserThreadExecutionStarted.newCase(projectName, mainClassName, project, mainArgs, outputFiles, inputFiles, mainMethod, mainClass, this);
//                    String projectFolder = project.getProjectFolderName();
//                    String binaryFolder  = project.getBinaryProjectFolderName();
//                    System.out.println("Binary folder:" + binaryFolder + " ProjectFolder:" + projectFolder);
//                    String workingDirectory = projectFolder;
//                    File aWorkingDirectoryFile = new File(projectFolder);
//                   	File currentDirectory = new File (".");
//                	File[] files = currentDirectory.listFiles();
//                    if (aWorkingDirectoryFile.exists()) { // if zip then this is not the case
////                    	System.setProperty("user.dir", projectFolder);
//                    	setCurrentDirectory(projectFolder);
//                    	changedWorkingDirectory = true;
//                    	System.out.println("New working directory: " + System.getProperty("user.dir", projectFolder));
//                    	 currentDirectory = new File (".").getAbsoluteFile();
//                    	files = currentDirectory.listFiles();
//                    	File arthur = new File ("arthur.jpg").getAbsoluteFile();
//                    	if (arthur.exists()) {
//                    		System.out.println(" found arthur");
//                    	}
//                    }
                	setCurrentDirectory(project.getProjectFolderName());
                	mainMethod.invoke(mainClass, args);
//                	if (changedWorkingDirectory) {
//                		System.setProperty("user.dir", previousDir);
//                	}
                	
                	UserThreadExecutionFinished.newCase(projectName, mainClassName, project, mainArgs, outputFiles, inputFiles, mainMethod, mainClass, this);

                    if (outputFile != null) {
                        stdout.close();
                    }
                    if (inputFile != null) {
                        stdin.close();
                    }
                } catch (Exception e) {
                	String message = "Could not successfully run:" + projectName + "with input file:" + inputFile;
                    System.out.println(message);
                    NotRunnableException traceable = new NotRunnableException(message, this);
                    traceable.announce();
//                    System.out.println("Could not successfully run:" + projectName + "with input file:" + inputFile);

                    e.printStackTrace();
                } finally {
//                	String previousDir = System.getProperty("user.dir");
//                	if (changedWorkingDirectory) {
//            		System.setProperty("user.dir", previousDir);
//            	}

                }
                project.setHasBeenRun(true);

                System.out.println("terminated main method");
                if (appendedToTranscript)
                	OverallTranscriptSaved.newCase(null, null,   (SakaiProject) project, project.getOutputFileName(), "???", this);
            }

        } catch (Exception e) {
            System.out.println("Could not run:" + projectName);
            project.setCanBeRun(false);
            e.printStackTrace();
        }
    }

}
