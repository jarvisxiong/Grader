package gradingTools.sharedTestCase;

import java.util.List;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;


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
		// TODO Auto-generated method stub
		return "Illegal type or call";
	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
	    	return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	    	
	}

}

