package grader.compilation;

import grader.basics.execution.RunningProject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ClassFilesCompiler {
	public RunningProject compile(File sourceFolder, File buildFolder, List<File> sourceFiles) throws IOException, IllegalStateException;


}
