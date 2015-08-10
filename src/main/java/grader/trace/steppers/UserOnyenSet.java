package grader.trace.steppers;

import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class UserOnyenSet extends TraceableInfo {
	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	String onyen;
	


	public UserOnyenSet(String aMessage, SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, String anOnyen, Object aFinder) {
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

	
	public static UserOnyenSet newCase(SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, String anOnyen, Object aFinder) {
		String aMessage = "Stepper onyen manually changed to:" + anOnyen;
		UserOnyenSet retVal = new UserOnyenSet(aMessage, aSakaiProjectDatabase, aProjectStepper, anOnyen, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
