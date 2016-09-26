package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;

public class OldClassHasAtLeastOneInterfaceTestCase extends CheckStyleTestCase {
	public static final String WARNING_NAME = "classHasAtLeastOneInterface";
	public static final double DEFAULT_PENALTY_PER_MISTAKE = 0.2;
	protected double penaltyPerMistake = DEFAULT_PENALTY_PER_MISTAKE;

	public OldClassHasAtLeastOneInterfaceTestCase(String aMessage) {
		super(null, aMessage);
	}
	public OldClassHasAtLeastOneInterfaceTestCase() {
		super(null, "Class has at least one interface test case");
		
	}
	public OldClassHasAtLeastOneInterfaceTestCase(double aPenaltyPerMistake) {
		super(null, "Class has at least one interface test case");
		penaltyPerMistake = aPenaltyPerMistake;

	}
	@Override
	public String regexLineFilter() {
		// TODO Auto-generated method stub
		return ".*" + WARNING_NAME + ".*";
	}

	@Override
	public String failMessageSpecifier(List<String> aFailedLines) {
		return "Missing interface found in following lines:\n" + beautify(aFailedLines);
	}
	
//	String beautify (String aCheckstyleString) {
//		return aCheckstyleString.substring(aCheckstyleString.indexOf(WARNING_NAME)) + "\n";
//	}
//	String beautify (List<String> aList) {
//		StringBuffer sb = new StringBuffer();
//		for (String aString: aList) {
//			String beautifiedString = beautify(aString);
//			sb.append(beautifiedString);
//			
//		}
//		return sb.toString();
//	}
	@Override
	 protected  String warningName(){
	    	return WARNING_NAME;
	    }
	
	protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
		if (aMatchedLines.size() == 0) {
			return pass();
		}
		double aPenalty = aMatchedLines.size() * penaltyPerMistake;
        double aScore  = Math.max(0, 1 - aPenalty);
        return partialPass(aScore, beautify(aMatchedLines));
    	
    }
	 

}
