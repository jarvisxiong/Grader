package grader.requirements.interpreter.checkers;

import java.io.File;
import java.io.IOException;

import tools.DirectoryUtils;
import util.misc.Common;
import grader.requirements.interpreter.specification.CSVRequirementsSpecification;
import grader.trace.CSVSerializable;

public class ADiffChecker implements InterpretedChecker{
	protected String diffTool;
	
	String TEMP_DIR = "tmp";
	
	public ADiffChecker() {
		File dir = new File(TEMP_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
	}

//	@Override
//	public boolean isExpandFiles() {
//		return false;
//	}
	public int getNumArgs() {
		return 3;
	}	
	public File maybeCreateFile(String aFileName) {
		File aFile = new File(aFileName);
		if (!aFile.exists()) {
			try {
				aFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aFile;
	}
	@Override
	public InterpretedCheckerResult check(String[] anArgs) {
		try {
			String anOptions = anArgs[0];
			String anArg1Text = anArgs[1];
			String anArg2Text = anArgs[2];
			String anArg1FileName =  TEMP_DIR + "/" + "diffFile1.txt";
			String anArg2FileName = TEMP_DIR + "/" + "diffFile2.txt";
			String aResultFileName = TEMP_DIR + "/" + "diffresult.txt";
			File anArg1 = maybeCreateFile(anArg1FileName);
			File anArg2 = maybeCreateFile(anArg2FileName);
			maybeCreateFile(aResultFileName);

			
			DirectoryUtils.diff(anArgs[0], anArg1,
					anArg2, aResultFileName);
			String aNotes = Common.toText(aResultFileName).toString();
			boolean aResult = aNotes.isEmpty();
			return new ACheckerResult(aNotes, aResult);

		} catch (Exception e) {
			e.printStackTrace();
			return new ACheckerResult(e.getMessage(), false);
		}

	}


	

}
