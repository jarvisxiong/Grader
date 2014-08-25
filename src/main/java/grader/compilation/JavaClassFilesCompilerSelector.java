package grader.compilation;

public class JavaClassFilesCompilerSelector {
	static ClassFilesCompiler classFilesCompiler = new AJavaClassFilesCompiler();

	public static ClassFilesCompiler getClassFilesCompiler() {
		return classFilesCompiler;
	}

	public static void setClassFilesCompiler(ClassFilesCompiler classFilesCompiler) {
		JavaClassFilesCompilerSelector.classFilesCompiler = classFilesCompiler;
	}

}
