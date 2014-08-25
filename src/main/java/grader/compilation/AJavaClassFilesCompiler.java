package grader.compilation;

import framework.execution.RunningProject;
import framework.utils.GradingEnvironment;
import grader.trace.compilation.SourceFileCompiled;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import util.trace.javac.CompilerNotFound;

public class AJavaClassFilesCompiler implements ClassFilesCompiler{
	/**
	 * Given an ArrayList of .javaFiles, returns whether the .java file needs to
	 * be compiled or recompiled
	 * 
	 * @param javaFiles
	 *            ArrayList of .java files
	 * @throws IOException
	 */
	public RunningProject compile(File sourceFolder, File buildFolder, List<File> sourceFiles) throws IOException, IllegalStateException {

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler != null) {
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

			List<String> optionList = new ArrayList<String>();
			// set the output directory for the compiler
			String buildFolderPath = buildFolder.getCanonicalPath();
//			optionList.addAll(Arrays.asList("-d", buildFolderPath));
			optionList.addAll(Arrays.asList("-d", buildFolderPath, "-cp", GradingEnvironment
					.get().getClasspath()));
			System.out.println(buildFolderPath);

			Iterable<? extends JavaFileObject> compilationUnits = fileManager
					.getJavaFileObjectsFromFiles(sourceFiles);
			compiler.getTask(null, fileManager, null, optionList, null, compilationUnits).call();
			for (File javaFile:sourceFiles) {
				SourceFileCompiled.newCase(javaFile.getAbsolutePath(), this);
				
			}
		} else {
//			throw new RuntimeException("Compiler not accessible");
			throw CompilerNotFound.newCase(this);
		}
		return null;
	}

}
