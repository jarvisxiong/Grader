package grader.trace.stepper;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProceedWhenDoneChanged extends SerializableStepperInfo {
	boolean proceedWhenDone;
public boolean isProceedWhenDone() {
		return proceedWhenDone;
	}



	public void setProceedWhenDone(boolean proceedWhenDone) {
		this.proceedWhenDone = proceedWhenDone;
	}



public ProceedWhenDoneChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, boolean newVal,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		proceedWhenDone = newVal;
		// TODO Auto-generated constructor stub
	}


	
	public static ProceedWhenDoneChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, boolean newVal,
			Object aFinder) {
		String aMessage = "Proceed when done changed to:" + newVal;
		ProceedWhenDoneChanged retVal = new ProceedWhenDoneChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, newVal, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
