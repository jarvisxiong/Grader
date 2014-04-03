package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class UserProblemChange extends GraderSettingsInfo {
	
	String problem;
	
	
	public UserProblemChange(String aMessage, String aProblem, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		problem = aProblem;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + problem;
	}
	
	
	public static UserProblemChange newCase(String aProblem, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Problem Changed:" + aProblem;
		UserProblemChange retVal = new UserProblemChange(aMessage, aProblem,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
