package grader.trace.stepper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.spreadsheet.csv.ASakaiCSVFeatureGradeManager;
import grader.trace.CSVDeSerializable;
import grader.trace.SerializableGraderInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProjectStepAborted extends SerializableStepperInfo  {


public ProjectStepAborted(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		// TODO Auto-generated constructor stub
	}







	
	public static ProjectStepAborted newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Object aFinder) {
		String aMessage = "Project Step Aborted for Student :" + aProjectStepper.getOnyen();
		ProjectStepAborted retVal = new ProjectStepAborted(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		retVal.announce();		
		return retVal;
	}



	
	

}
