package grader.compilation;

public class ClassFilesCompilerSelector {
	static ClassFilesCompiler classFilesCompiler = new AJavaClassFilesCompiler();

	public static ClassFilesCompiler getClassFilesCompiler() {
		return classFilesCompiler;
	}

	public static void setClassFilesCompiler(ClassFilesCompiler classFilesCompiler) {
		ClassFilesCompilerSelector.classFilesCompiler = classFilesCompiler;
	}

}
