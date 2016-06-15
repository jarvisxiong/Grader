package grader.trace.settings;

import grader.settings.GraderSettingsModel;

public class EndOnyenUserChange extends GraderSettingsInfo {
	
	String endOnyen;
	
	
	public EndOnyenUserChange(String aMessage, String aEndOnyen, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		endOnyen = aEndOnyen;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public String getEndOnyen() {
		return endOnyen;
	}
	public void setEndOnyen(String endOnyen) {
		this.endOnyen = endOnyen;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + endOnyen;
	}
	
	
	public static EndOnyenUserChange newCase(String anEndOnyen, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "EndOnyen Changed:" + anEndOnyen;
		EndOnyenUserChange retVal = new EndOnyenUserChange( aMessage, anEndOnyen, aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
