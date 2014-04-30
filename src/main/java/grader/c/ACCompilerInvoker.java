package grader.c;

import java.io.IOException;

import util.misc.Common;
import framework.execution.ProcessRunner;
import framework.execution.Runner;
import framework.utils.GradingEnvironment;
import grader.documents.AWordDocumentDisplayer;
import grader.documents.DocumentDisplayer;
import grader.settings.GraderSettingsManagerSelector;
import grader.trace.file.open.WordOpenedFile;

public class ACCompilerInvoker {
	
	String compilerPath;
	public ACCompilerInvoker() {
		setCompilerPath();
		
	}
	
	void setCompilerPath() {
//		 compilerPath = GraderSettingsManagerSelector.getGraderSettingsManager().getCCompilerPath();
		compilerPath = "cl";
	}
	
	public void compileFile(String aFileName) {
        String windowsName = Common.toWindowsFileName(aFileName);

//        String[] command = {compilerPath, windowsName};
//        ProcessBuilder builder = new ProcessBuilder(compilerPath);
//        try {
//			builder.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        String[] args = {};
        String[] command = {compilerPath, windowsName};
//        String[] command = {"Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/src/Simple.exe"};
        Runner processRunner = new ProcessRunner();
        processRunner.run(command, "", args, 3000);
//		Common.exec(command);

//        Common.exec(command);
//       WordOpenedFile.newCase(aFileName, this);

    }
	
	public static void main (String[] args) {
        
		ACCompilerInvoker compiler = new ACCompilerInvoker();
		compiler.compileFile("Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/src/Simple.c");
    }
	

}
