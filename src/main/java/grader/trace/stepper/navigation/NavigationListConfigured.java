package grader.trace.stepper.navigation;

import java.util.List;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class NavigationListConfigured extends StepperInfo {
	List<String> onyens;




public List<String> getOnyens() {
		return onyens;
	}



	public void setOnyens(List<String> onyens) {
		this.onyens = onyens;
	}



public NavigationListConfigured(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, List<String> newVal,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		onyens = newVal;
		// TODO Auto-generated constructor stub
	}


	
	public static NavigationListConfigured newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, List<String> newVal,
			Object aFinder) {
		String aMessage = "Proceed when done changed to:" + newVal;
		NavigationListConfigured retVal = new NavigationListConfigured(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, newVal, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
