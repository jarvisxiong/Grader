package grader.sakai;

public class GradeSpreadsheetExtractorFactory {
	static GradeSpreadsheetExtractor singleton;
	public static GradeSpreadsheetExtractor getSingleton() {
		if (singleton == null) {
			singleton = new ASakaiGradeSpreadsheetExtractor();			
		}
		return singleton;
	}
}
