package grader.compilation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface ClassFilesCompiler {
	public void compile(File buildFolder, ArrayList<File> sourceFiles) throws IOException, IllegalStateException;


}
