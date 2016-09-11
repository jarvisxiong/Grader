package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;


public class IllegalImportOrCallTestCase extends CheckStyleTestCase {
	 public IllegalImportOrCallTestCase() {
	        super(null, "Illegal import or call test case");
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)illegalTypeImported(.*) | (.*)illegalMethodCall(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		return "Illegal type or call";
	}
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
	    	return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	    	
	}

}

