package grader.trace.steppers;

import java.util.Date;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.GraderInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class StepperInfo extends GraderInfo {
	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	SakaiProject sakaiProject;
	public StepperInfo(String aMessage, 
			SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper,
			SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aFinder);
		sakaiProjectDatabase = aSakaiProjectDatabase;
		overviewProjectStepper = aProjectStepper;
		sakaiProject = aProject;
	}
		
	public SakaiProject getSakaiProject() {
		return sakaiProject;
	}


	public SakaiProjectDatabase getSakaiProjectDatabase() {
		return sakaiProjectDatabase;
	}

	public OverviewProjectStepper getOverviewProjectStepper() {
		return overviewProjectStepper;
	}
	
	

	

}
