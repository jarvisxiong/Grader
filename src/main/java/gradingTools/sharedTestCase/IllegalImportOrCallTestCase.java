package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;


public class IllegalImportOrCallTestCase extends CheckStyleTestCase {
	public static final String WARNING_NAME = "illegal";

	 public IllegalImportOrCallTestCase() {
	        super(null, "Illegal import or call test case");
	    }
    
	@Override
	public String regexLineFilter() {
		return "((.*)illegalTypeImported(.*))|((.*)illegalMethodCall(.*))";
	}

	@Override
	protected  String warningName(){
	   return WARNING_NAME;
	}

	@Override
	public String failMessageSpecifier(List<String> aFailedLines) {
		return "Illegal type or call in:\n" + beautify(aFailedLines);
	}
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
	    	return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	    	
	}
	 public static void main (String[] args) {		 
		 System.out.println(
				 "illegalMethodCall: (Assignment4.java:31) called disallowed method main--String.split	Assignment4.java" 	
				  .matches("((.*)illegalTypeImported(.*))|((.*)illegalMethodCall(.*))"));

	 }

}

