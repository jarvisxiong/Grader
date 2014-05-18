package grader.compilation.c;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.misc.Common;
import framework.execution.ProcessRunner;
import framework.execution.Runner;
import framework.utils.GradingEnvironment;
import grader.compilation.ClassFilesCompiler;
import grader.documents.AWordDocumentDisplayer;
import grader.documents.DocumentDisplayer;
import grader.language.LanguageDependencyManager;
import grader.project.file.ARootCodeFolder;
import grader.settings.GraderSettingsManagerSelector;
import grader.trace.file.open.WordOpenedFile;

public class ACFilesCompiler implements ClassFilesCompiler {
	
	public static final String OBJECT_SUFFIX = ".obj";
	public static final String EXECUTABLE_SUFFIX = ".exe";
	public static final String COMPILER_COMMAND = "cl";
	public static final String OBJECT_OPTION = "-o";
	public static final String EXECUTABLE_OPTION = "/link/out:";

	
	String compilerPath;
	public ACFilesCompiler() {
		setCompilerPath();
		
	}
	
	void setCompilerPath() {
//		 compilerPath = GraderSettingsManagerSelector.getGraderSettingsManager().getCCompilerPath();
		compilerPath = COMPILER_COMMAND;
	}
	
	public void compileFile(String aFileName, String workingDirectory) {
        String windowsName = Common.toWindowsFileName(aFileName);
        int extensionIndex = aFileName.indexOf(LanguageDependencyManager.getSourceFileSuffix());
        if (extensionIndex < 1)
        	return;
        String baseName = aFileName.substring(0, extensionIndex);
        String shortBaseName = Common.absoluteNameToLocalName(baseName);
        String shortObjName = shortBaseName + OBJECT_SUFFIX;
        String shortExecName = shortBaseName + EXECUTABLE_SUFFIX;
//        String fullObjName = workingDirectory + "/" + shortObjName;
//        String fullExecName = workingDirectory + "/" + shortExecName;
        String fullObjName = "bin" + "\\" + shortObjName;
        String fullExecName = "bin" + "\\" + shortExecName;
//        String windowsObjName = Common.toWindowsFileName(fullObjName);
//        String windowsExecName = Common.toWindowsFileName(fullExecName);

//        String[] command = {compilerPath, windowsName};
//        ProcessBuilder builder = new ProcessBuilder(compilerPath);
//        try {
//			builder.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        String[] args = {};
//        String[] command = {compilerPath, windowsName, "-o",  fullObjName , EXECUTABLE_OPTION  + fullExecName};
        String[] command = {compilerPath, windowsName, "-o",  shortObjName , EXECUTABLE_OPTION  + shortExecName};

//        String[] command = {compilerPath, windowsName};

//        String[] command = {"Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/src/Simple.exe"};
        Runner processRunner = new ProcessRunner(new File(workingDirectory));
        processRunner.run(command, "", args, 3000);
//		Common.exec(command);

//        Common.exec(command);
//       WordOpenedFile.newCase(aFileName, this);

    }
	
	String quote(String s) {
		return "\"" + s + "\"" ;
	}
	
	public static void main (String[] args) {
        
		ACFilesCompiler compiler = new ACFilesCompiler();
//		compiler.compileFile("Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/src/Simple.c",
//				"Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/bin");
//		compiler.compileFile("src/Simple.c",
//				"Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/bin");
		compiler.compileFile("..\\src\\Simple.c",
		"Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/bin");
//		compiler.compileFile("src/Simple.c",
//				"Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1");
    }

	@Override
	public void compile(File sourceFolder, File buildFolder, List<File> sourceFiles)
			throws IOException, IllegalStateException {
		List<String> commandList= new ArrayList(sourceFiles.size() + 1);
		commandList.add(compilerPath);
		boolean separateSrcBin = !sourceFolder.equals(buildFolder);
		for (File sourceFile:sourceFiles) {
			String shortName = sourceFile.getName();
			if (separateSrcBin) {
				commandList.add("../src/" + shortName);
			} else {
				commandList.add(shortName);
			}			
		}
//		 String[] command = {compilerPath, windowsName, "-o",  shortObjName , EXECUTABLE_OPTION  + shortExecName};

//       String[] command = {compilerPath, windowsName};

//       String[] command = {"Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/src/Simple.exe"};
       Runner processRunner = new ProcessRunner(buildFolder);
       String[] args = {};
       String[] command =  commandList.toArray(args);
       processRunner.run(command, "", args, 3000);
		
//		int extensionIndex = aFileName.indexOf(AJavaRootCodeFolder.getSourceFileSuffix());
//        if (extensionIndex < 1)
//        	return;
//        String baseName = aFileName.substring(0, extensionIndex);
//		String sourceShort
//		if (sourceFolder == buildFolder) {
//			
//		}
		
	}
	

}
