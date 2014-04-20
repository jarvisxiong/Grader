package grader.trace.stepper;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.CSVDeSerializable;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProblemHistoryVisited extends TabVisited  {



public ProblemHistoryVisited(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aTab,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aTab, aFinder);
	}

	
	public static ProblemHistoryVisited newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aTab,
			Object aFinder) {
		String aMessage = "Problem History Visited for Student:" + aProjectStepper.getOnyen();
		ProblemHistoryVisited retVal = new ProblemHistoryVisited(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  aTab, aFinder);
		retVal.announce();		
		return retVal;
	}



	
	

}
