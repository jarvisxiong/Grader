package grader.trace.settings;

import grader.letter_grade.LetterGrade;
import grader.settings.GraderSettingsModel;

public class LetterGradeUserChange extends GraderSettingsInfo {
	
	LetterGrade letterGrade;
	
	
	public LetterGradeUserChange(String aMessage, LetterGrade aLetterGrade, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		letterGrade = aLetterGrade;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public LetterGrade getLetterGrade() {
		return letterGrade;
	}
	public void setLetterGrade(LetterGrade letterGrade) {
		this.letterGrade = letterGrade;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + letterGrade;
	}
	
	
	public static LetterGradeUserChange newCase(LetterGrade aLetterGrade, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "LetterGrade Changed:" + aLetterGrade;
		LetterGradeUserChange retVal = new LetterGradeUserChange(aMessage, aLetterGrade,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
