package framework.project;

import framework.execution.*;
import grader.project.AProject;
import grader.trace.project.BinaryFolderMade;
import grader.trace.project.BinaryFolderNotFound;
import grader.trace.project.ProjectFolderNotFound;
import grader.trace.project.SourceFolderAssumed;
import grader.trace.project.SourceFolderNotFound;
import scala.Option;
import tools.DirectoryUtils;
import util.trace.TraceableLog;
import util.trace.TraceableLogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

/**
 * A "standard" project. That is, an IDE-based java project.
 */
public class StandardProject implements Project {

    private File directory;
    private File sourceFolder;
    private Option<ClassesManager> classesManager;
    private TraceableLog traceableLog;
    boolean noSrc;

    /**
     * Basic constructor
     *
     * @param aDirectory The location of the project
     * @param name      The name of the project, such as "Assignment1"
     * @throws FileNotFoundException
     */
//    public StandardProject(File directory, String name) throws FileNotFoundException {
//        // Find the folder. We could be there or it could be in a different folder
//    	if (directory == null) return;
//        Option<File> src = DirectoryUtils.locateFolder(directory, "src");
//        if (src.isEmpty()) {
//          throw new FileNotFoundException("No src folder");
////
////        	noSrc = true;
////        	sourceFolder = directory;
////        	this.directory = sourceFolder;
//        } else {
//        sourceFolder = src.get();
//        this.directory = src.get().getParentFile();
//        }
//
//        try {
//            File sourceFolder = new File(this.directory, "src");
//            File buildFolder = getBuildFolder("main." + name);
//            classesManager = Option.apply((ClassesManager) new ProjectClassesManager(buildFolder, sourceFolder));
//        } catch (Exception e) {
//            classesManager = Option.empty();
//        }
//
//        // Create the traceable log
//        traceableLog = TraceableLogFactory.getTraceableLog();
//
//    }
    
    // rewriting Josh's code
    public StandardProject(File aDirectory, String name) throws FileNotFoundException {
        // Find the folder. We could be there or it could be in a different folder
    	if (aDirectory == null) return;
    	directory = aDirectory;
        Option<File> src = DirectoryUtils.locateFolder(aDirectory, "src");
        if (src.isEmpty()) {
        	System.out.println(SourceFolderNotFound.newCase(aDirectory.getAbsolutePath(), this).getMessage());

        	Set<File> sourceFiles = DirectoryUtils.getSourceFiles(aDirectory);
        	if (sourceFiles.size() != 0) {
        		File aSourceFile = sourceFiles.iterator().next();
        		sourceFolder = aSourceFile.getParentFile(); // assuming no packages!
                this.directory = sourceFolder.getParentFile();
       		 SourceFolderAssumed.newCase(sourceFolder.getAbsolutePath(), this);


        	} else {
            	System.out.println(ProjectFolderNotFound.newCase(aDirectory.getAbsolutePath(), this).getMessage());

        	}
//          throw new FileNotFoundException("No src folder");

        	noSrc = true;
//        	sourceFolder = aDirectory;
//        	this.directory = sourceFolder;
        } else {
        sourceFolder = src.get();
        this.directory = src.get().getParentFile();
        }
        

        try {
//            File sourceFolder = new File(this.directory, "src");
            File buildFolder = getBuildFolder("main." + name);
            if (AProject.isMakeClassDescriptions())
            classesManager = Option.apply((ClassesManager) new ProjectClassesManager(buildFolder, sourceFolder));
        } catch (Exception e) {
            classesManager = Option.empty();
        }

        // Create the traceable log
        traceableLog = TraceableLogFactory.getTraceableLog();

    }

    /**
     * This figures out where the build folder is, taking into account variations due to IDE
     *
     * @param preferredClass The name of the class that has the main method, such as "main.Assignment1"
     * @return The build folder
     * @throws FileNotFoundException
     */
    public File getBuildFolder(String preferredClass) throws FileNotFoundException {
        Option<File> out = DirectoryUtils.locateFolder(directory, "out");
        Option<File> bin = DirectoryUtils.locateFolder(directory, "bin");

        // If there is no 'out' or 'bin' folder then give up
        if (out.isEmpty() && bin.isEmpty()) {
        	if (noSrc)
        		return sourceFolder; 
//            throw new FileNotFoundException();
        	BinaryFolderNotFound.newCase(directory.getAbsolutePath(), this);
        	File retVal = new File(directory, "bin");
        	retVal.mkdirs();
        	BinaryFolderMade.newCase(retVal.getAbsolutePath(), this);
        	return retVal.getAbsoluteFile();
        	
        } else {
            // There can be more folders under it, so look around some more
            // But first check the class name to see what we are looking for
            File dir = null;
            if (out.isDefined())
                dir = out.get();
            if (bin.isDefined())
                dir = bin.get();

            if (preferredClass.contains(".")) {
                Option<File> packageDir = DirectoryUtils.locateFolder(dir, preferredClass.split("\\.")[0]);
                if (packageDir.isDefined())
                    return packageDir.get().getParentFile();
                else
                    return dir;
            } else
                return dir;
        }
    }

    @Override
    public TraceableLog getTraceableLog() {
        return traceableLog;
    }

    @Override
    public RunningProject start(String input) throws NotRunnableException {
        return new ReflectionRunner(this).run(input);
    }

    @Override
    public RunningProject launch(String input) throws NotRunnableException {
        return new ProcessRunner(this).run(input);
    }

    @Override
    public RunningProject start(String input, int timeout) throws NotRunnableException {
        return new ReflectionRunner(this).run(input, timeout);
    }

    @Override
    public RunningProject launch(String input, int timeout) throws NotRunnableException {
        return new ProcessRunner(this).run(input, timeout);
    }

    @Override
    public RunningProject launchInteractive() throws NotRunnableException {
        return new InteractiveConsoleProcessRunner(this).run("");
    }

    @Override
    public Option<ClassesManager> getClassesManager() {
        return classesManager;
    }

    @Override
    public File getSourceFolder() {
        return sourceFolder;
    }
}
