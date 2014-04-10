package grader.project.source;

public class TACommentsExtractorSelector {
	static TACommentsExtractor taCommentExtractor = new ATACommentsExtractor();

	public static TACommentsExtractor getTACommentExtractor() {
		return taCommentExtractor;
	}
	public static void setTACommentExtractor(TACommentsExtractor newVal) {
		taCommentExtractor = newVal;
	}	

}
