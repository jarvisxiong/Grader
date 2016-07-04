package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;


public class MinDeclaredMethodsTestCase extends CheckStyleTestCase {
	int minimum;
	 public MinDeclaredMethodsTestCase(int aMinimum) {
	        super(null, "Min declared method test case");
	        minimum = aMinimum;
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)minDeclaredMethods(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Number of declared methods less than" + minimum;
	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
	    	return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	    	
	}

}

