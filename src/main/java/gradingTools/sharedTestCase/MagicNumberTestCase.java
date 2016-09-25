package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;

public class MagicNumberTestCase extends CheckStyleTestCase {
	public MagicNumberTestCase(String aMessage) {
		super(null, aMessage);
	}
	public MagicNumberTestCase() {
		super(null, "magic number");
	}
	public static final String WARNING_NAME = "magic";
	@Override
	public String regexLineFilter() {
		// TODO Auto-generated method stub
		return ".*" + WARNING_NAME + ".*";
	}
	@Override
	 protected  String warningName(){
	    	return WARNING_NAME;
	    }

	@Override
	public String failMessageSpecifier(List<String> aFailedLines) {
		return "Magic number failed in:\n" + toLinesString(aFailedLines);
	}
//	protected String warningName() {
//		return WARNING_NAME;
//	}
//	protected String beautify (String aCheckstyleString) {
//		return aCheckstyleString.substring(aCheckstyleString.indexOf(warningName())) + "\n";
//	}
//	protected String beautify (List<String> aList) {
//		StringBuffer sb = new StringBuffer();
//		for (String aString: aList) {
//			String beautifiedString = beautify(aString);
//			sb.append(beautifiedString);
//			
//		}
//		return sb.toString();
//	}
	
	protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
		if (aMatchedLines.size() == 0) {
			return pass();
		}
		double score = Math.min(aMatchedLines.size(), 5) / 5.0;
        score = Math.max(0, 1 - score);
        return partialPass(score, beautify(aMatchedLines));
    	
    }
	 

}
