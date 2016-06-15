package grader.trace.settings;

import grader.settings.GraderSettingsModel;

public class ProblemUserChange extends GraderSettingsInfo {
	
	String problem;
	
	
	public ProblemUserChange(String aMessage, String aProblem, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
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
	
	
	public static ProblemUserChange newCase(String aProblem, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "Problem Changed:" + aProblem;
		ProblemUserChange retVal = new ProblemUserChange(aMessage, aProblem,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
