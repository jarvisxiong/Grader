package grader.c;

import java.io.IOException;

import util.misc.Common;
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
        String[] command = {compilerPath, windowsName};

        Common.exec(command);
//       WordOpenedFile.newCase(aFileName, this);

    }
	
	public static void main (String[] args) {
		ACCompilerInvoker compiler = new ACCompilerInvoker();
		compiler.compileFile("Test Data/Test C/Assignment1/All, Correct (acorrect)/Submission attachment(s)/program1/Program1/src/Simple.c");
    }
	

}
