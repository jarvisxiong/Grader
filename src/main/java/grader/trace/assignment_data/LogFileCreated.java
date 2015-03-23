package grader.trace.assignment_data;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.file.FileInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class LogFileCreated extends FileInfo {



public LogFileCreated(String aMessage,
			String anOvervewFileName,
			Object aFinder) {
		super(aMessage, anOvervewFileName, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static LogFileCreated newCase(
			String aFileName,
			Object aFinder) {
		String aMessage = "Onyen file created:" + aFileName;
		LogFileCreated retVal = new LogFileCreated(aMessage, aFileName, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
