package grader.sakai;

public class AssignmentFolderExtractorFactory {
	static AssignmentFolderExtractor singleton;
	public static AssignmentFolderExtractor getSingleton() {
		if (singleton == null) {
			singleton = new ASakaiAssignmentFolderExtractor();			
		}
		return singleton;
	}
}
