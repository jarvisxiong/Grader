package grader.project.source;

public class ATACommentsExtractor implements TACommentsExtractor{
	public static final String TA_STRING = "TA:";
	public static final int EXPECTED_MAX_COMMENTS_LENGTH = 256;
	
	public String extractTAComments(String allSource) {
		String[] lines = allSource.split("\n");
		StringBuffer retVal =  new StringBuffer(EXPECTED_MAX_COMMENTS_LENGTH );
		for (String line:lines) {
			
			if (line.contains(TA_STRING)) {
				if (retVal.length() != 0)
					retVal.append("\n");
				retVal.append(line);
			}
		}
			
		return retVal.toString();
	}
	
	public static String extractDigitString (String s, int start) {
		int end = start;
		for (; end < s.length(); end++)
			if (Character.isDigit(s.charAt(end))) continue;
			else break;
		return s.substring(start, end);			
	}
	
	public static double extractScore (String s, int start) {
		return Double.parseDouble(extractDigitString(s, start));
	}
	
	public static double extractScore (String s) {
		return Double.parseDouble(extractDigitString(s, 0));
	}
	
	public static double extractPositiveScore (String s) {
		String[] positiveStrings = s.split("\\+");
		double retVal = 0;
		for (int i = 1; i <positiveStrings.length; i++) {
//		for (String positiveString:positiveStrings) {
//			String positiveString = positiveStrings[i];
			retVal += extractScore(positiveStrings[i]);
		}
		return retVal;	
	}
	
	public static double extractNegativeScore (String s) {
		String[] negativeStrings = s.split("-");
		double retVal = 0;
//		for (String positiveString:positiveStrings) {
//			retVal -= extractScore(positiveString);
//		}
		for (int i = 1; i <negativeStrings.length; i++) {
//			for (String positiveString:positiveStrings) {
//				String positiveString = positiveStrings[i];
				retVal -= extractScore(negativeStrings[i]);
			}
		return retVal;	
	}
	@Override
	public double extractTAPoints(String aTAComments) {
		return extractPositiveScore(aTAComments) + extractNegativeScore(aTAComments);		
		
	}
	
	public static void main (String[] args) {
		String source = "public class foo\n \\\\acomment\n\\\\TA: a TA comment +5 \n TA: another TA comment -3";
		TACommentsExtractor extractor = new ATACommentsExtractor();
		String taComments = extractor.extractTAComments(source);
		double taScore = extractor.extractTAPoints(taComments);
		System.out.println(taComments);
		System.out.println(taScore);
//		System.out.println((new ATACommentsExtractor()).extractTAComments(source));
	}

}
