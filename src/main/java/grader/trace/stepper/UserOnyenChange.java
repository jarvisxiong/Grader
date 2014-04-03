package grader.trace.stepper;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class UserOnyenChange extends TraceableInfo {
	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	String onyen;
	


	public UserOnyenChange(String aMessage, SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, String anOnyen, Object aFinder) {
		super(aMessage, aFinder);
		sakaiProjectDatabase = aSakaiProjectDatabase;
		overviewProjectStepper = aProjectStepper;
		onyen = anOnyen;
	}
	
	

	public SakaiProjectDatabase getSakaiProjectDatabase() {
		return sakaiProjectDatabase;
	}

	public OverviewProjectStepper getOverviewProjectStepper() {
		return overviewProjectStepper;
	}
	
	public String getOnyen() {
		return onyen;
	}

	public void setOnyen(String onyen) {
		this.onyen = onyen;
	}

	
	public static UserOnyenChange newCase(SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, String anOnyen, Object aFinder) {
		String aMessage = "Stepper onyen manually changed to:" + anOnyen;
		UserOnyenChange retVal = new UserOnyenChange(aMessage, aSakaiProjectDatabase, aProjectStepper, anOnyen, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
