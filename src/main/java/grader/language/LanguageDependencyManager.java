package grader.language;

import grader.compilation.ClassFilesCompiler;
import grader.compilation.JavaClassFilesCompilerSelector;
import grader.compilation.c.CFilesCompilerSelector;
import grader.project.ExecutableFinderSelector;
import grader.project.JavaMainClassFinderSelector;
import grader.project.MainClassFinder;

import java.util.HashMap;
import java.util.Map;

public class LanguageDependencyManager {
	static  String sourceFileSuffix = ".java";
	 static Map<String, String> languageToSourceFileSuffix = new HashMap<>();
	 static Map<String, String> languageToBinaryFileSuffix = new HashMap<>();
	 static Map<String, MainClassFinder> languageToMainClassFinder = new HashMap();
	 static Map<String, ClassFilesCompiler> languageToCompiler = new HashMap();

	 static String language;
		
		public static String JAVA_LANGUAGE = "Java";
		public static String C_LANGUAGE = "C";

	
	public static  String binaryFileSuffix = ".class";


	public static void setSourceFileSuffix(String sourceFileSuffix) {
		LanguageDependencyManager.sourceFileSuffix = sourceFileSuffix;
	}


	public static void setLanguageToSourceFileSuffix(
			Map<String, String> languageToSourceFileSuffix) {
		LanguageDependencyManager.languageToSourceFileSuffix = languageToSourceFileSuffix;
	}


	



	public static void setLanguage(String language) {
		LanguageDependencyManager.language = language;
	sourceFileSuffix = languageToSourceFileSuffix.get(getLanguage());
	binaryFileSuffix = languageToBinaryFileSuffix.get(getLanguage());
	}


	


	public static void setBinaryFileSuffix(String binaryFileSuffix) {
		LanguageDependencyManager.binaryFileSuffix = binaryFileSuffix;
	}


	public static String getSourceFileSuffix() {
		return sourceFileSuffix;
	}


	public static String getLanguage() {
		return language;
	}


	public static String getBinaryFileSuffix() {
		return binaryFileSuffix;
	}
	public static MainClassFinder getMainClassFinder() {
		return languageToMainClassFinder.get(getLanguage());
	}
	
	public static ClassFilesCompiler getSourceFilesCompiler() {
		return languageToCompiler.get(getLanguage());
	}
	public static boolean isJava() {
		return getLanguage() == JAVA_LANGUAGE;
	}
	static {
		languageToSourceFileSuffix.put(JAVA_LANGUAGE, ".java");
		languageToBinaryFileSuffix.put(JAVA_LANGUAGE, ".class");
		languageToSourceFileSuffix.put(C_LANGUAGE, ".c");
		languageToBinaryFileSuffix.put(C_LANGUAGE, ".obj");
		
		languageToMainClassFinder.put(JAVA_LANGUAGE, JavaMainClassFinderSelector.getMainClassFinder());
		languageToMainClassFinder.put(C_LANGUAGE, ExecutableFinderSelector.getMainClassFinder());
		
		languageToCompiler.put(JAVA_LANGUAGE, JavaClassFilesCompilerSelector.getClassFilesCompiler() );
		languageToCompiler.put(C_LANGUAGE, CFilesCompilerSelector.getClassFilesCompiler());

		
	}
	

}
