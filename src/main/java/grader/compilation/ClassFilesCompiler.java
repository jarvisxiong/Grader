package grader.compilation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import framework.execution.BasicRunningProject;

public interface ClassFilesCompiler {
	public BasicRunningProject compile(File sourceFolder, File buildFolder, List<File> sourceFiles) throws IOException, IllegalStateException;


}
