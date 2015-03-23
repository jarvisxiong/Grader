package grader.trace.stepper;

import java.util.List;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class NavigationListCreated extends StepperInfo {
	List<String> onyens;







public NavigationListCreated(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, List<String> newVal,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		onyens = newVal;
		// TODO Auto-generated constructor stub
	}


public List<String> getOnyens() {
		return onyens;
	}



	public void setOnyens(List<String> onyens) {
		this.onyens = onyens;
	}
	
	public static NavigationListCreated newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, List<String> newVal,
			Object aFinder) {
		String aMessage = "Navigation list sorted:" + newVal;
		NavigationListCreated retVal = new NavigationListCreated(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, newVal, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
