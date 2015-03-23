package grader.trace.stepper;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class AutoAutoGradeSet extends StepperInfo {
	boolean autoAutoGrade;
public boolean isAutoAutoGrade() {
		return autoAutoGrade;
	}



	public void setAutoAutoGrade(boolean autoAutoGrade) {
		this.autoAutoGrade = autoAutoGrade;
	}



public AutoAutoGradeSet(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, boolean newVal,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		autoAutoGrade = newVal;
		// TODO Auto-generated constructor stub
	}


	
	public static AutoAutoGradeSet newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, boolean newVal,
			Object aFinder) {
		String aMessage = "Proceed when done changed to:" + newVal;
		AutoAutoGradeSet retVal = new AutoAutoGradeSet(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, newVal, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
