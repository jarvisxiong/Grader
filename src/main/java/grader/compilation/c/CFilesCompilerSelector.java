package grader.compilation.c;

import grader.compilation.ClassFilesCompiler;

public class CFilesCompilerSelector {
	static ClassFilesCompiler classFilesCompiler ;

	public static ClassFilesCompiler getClassFilesCompiler() {
		if (classFilesCompiler ==  null)
			classFilesCompiler =  new ACFilesCompiler();
		return classFilesCompiler;
	}

	public static void setClassFilesCompiler(ClassFilesCompiler classFilesCompiler) {
		CFilesCompilerSelector.classFilesCompiler = classFilesCompiler;
	}

}
