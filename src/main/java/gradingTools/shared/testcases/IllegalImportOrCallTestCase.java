package gradingTools.shared.testcases;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;


public class IllegalImportOrCallTestCase extends CheckStyleTestCase {
	 public IllegalImportOrCallTestCase() {
	        super(null, "Illegal import or call test case");
	    }
    
	 @Override
	public String regexLineFilter() {
		return "((.*)illegalTypeImported(.*))|((.*)illegalMethodCall(.*))";
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
	 public static void main (String[] args) {		 
		 System.out.println(
				 "illegalMethodCall: (Assignment4.java:31) called disallowed method main--String.split	Assignment4.java" 	
				  .matches("((.*)illegalTypeImported(.*))|((.*)illegalMethodCall(.*))"));
		 System.out.println(
				 "illegalTypeImported: (Assignment4.java:10) Used disallowed class javax.swing.* 	Assignment4.java	/Assignment4Final/src/main	"
	
				  .matches("((.*)illegalTypeImported(.*))|((.*)illegalMethodCall(.*))"));

	 }
}

