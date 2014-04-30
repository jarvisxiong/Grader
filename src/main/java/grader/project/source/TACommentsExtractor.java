package grader.project.source;

public interface TACommentsExtractor {
	
	public String extractTAComments(String allSource);

	double extractTAPoints(String aTAComments);
	
	

}
