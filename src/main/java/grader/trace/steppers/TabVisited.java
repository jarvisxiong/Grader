package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.CSVDeSerializable;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class TabVisited extends SerializableStepperInfo  {
	String tab;



public TabVisited(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aTab,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		tab = aTab;
		// TODO Auto-generated constructor stub
	}

public String getTab() {
	return tab;
}



public void setTab(String tab) {
	this.tab = tab;
}

	
	public static TabVisited newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aTab,
			Object aFinder) {
		String aMessage = "Tab Changed for Student:" + aProjectStepper.getOnyen() + " to:" + aTab ;
		TabVisited retVal = new TabVisited(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  aTab, aFinder);
		retVal.announce();		
		return retVal;
	}



	
	

}
