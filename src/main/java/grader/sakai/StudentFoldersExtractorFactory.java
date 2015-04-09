package grader.sakai;

public class StudentFoldersExtractorFactory {
	static StudentFoldersExtractor singleton;
	public static StudentFoldersExtractor getSingleton() {
		if (singleton == null) {
			singleton = new ASakaiStudentFoldersExtractor();		
		}
		return singleton;
	}

}
