package grader.compilation.c;

import grader.compilation.ClassFilesCompiler;

public class CFilesCompilerSelector {
	static ClassFilesCompiler classFilesCompiler = new ACFilesCompiler();

	public static ClassFilesCompiler getClassFilesCompiler() {
		return classFilesCompiler;
	}

	public static void setClassFilesCompiler(ClassFilesCompiler classFilesCompiler) {
		CFilesCompilerSelector.classFilesCompiler = classFilesCompiler;
	}

}
