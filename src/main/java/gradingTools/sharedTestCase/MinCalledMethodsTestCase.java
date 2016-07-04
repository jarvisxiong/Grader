package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;


public class MinCalledMethodsTestCase extends CheckStyleTestCase {
	int minimum;
	 public MinCalledMethodsTestCase(int aMinimum) {
	        super(null, "Min called method test case");
	        minimum = aMinimum;
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)minCalledMethods(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Number of called methods less than" + minimum;
	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
	    	return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	    	
	}

}

