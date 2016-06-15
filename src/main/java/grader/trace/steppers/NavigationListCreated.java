package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

import java.util.List;

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
