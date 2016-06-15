package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

import java.util.List;

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
		String aMessage = "Navigation list configured:" + newVal;
		NavigationListConfigured retVal = new NavigationListConfigured(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, newVal, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
