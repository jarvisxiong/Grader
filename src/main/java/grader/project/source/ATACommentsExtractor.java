package grader.project.source;

public class ATACommentsExtractor implements TACommentsExtractor{
	public static final String TA_STRING = "TA:";
	public static final int EXPECTED_MAX_COMMENTS_LENGTH = 256;
	
	public String extractTAComments(String allSource) {
		String[] lines = allSource.split("\n");
		StringBuffer retVal =  new StringBuffer(EXPECTED_MAX_COMMENTS_LENGTH );
		for (String line:lines) {
			if (retVal.length() != 0)
				retVal.append("\n");
			if (line.contains(TA_STRING))
				retVal.append(line);
		}
			
		return retVal.toString();
	}
	
	public static void main (String[] args) {
		String source = "public class foo\n \\\\acomment\n\\\\TA: a TA comment\n TA: another TA comment";
		System.out.println((new ATACommentsExtractor()).extractTAComments(source));
	}

}
