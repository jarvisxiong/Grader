package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.CSVDeSerializable;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class StudentHistoryVisited extends TabVisited  {



public StudentHistoryVisited(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aTab,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aTab, aFinder);
	}

	
	public static StudentHistoryVisited newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aTab,
			Object aFinder) {
		String aMessage = "Student History Visited for Student:" + aProjectStepper.getOnyen();
		StudentHistoryVisited retVal = new StudentHistoryVisited(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  aTab, aFinder);
		retVal.announce();		
		return retVal;
	}



	
	

}
