package grader.compilation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ClassFilesCompiler {
	public void compile(File sourceFolder, File buildFolder, List<File> sourceFiles) throws IOException, IllegalStateException;


}
