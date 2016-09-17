package gradingTools.shared.testcases;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.sakai.project.SakaiProject;

import java.util.List;


public class CheckstyleClassInstantiatedTestCase extends CheckStyleTestCase {
	 protected String instantiatedType;
//	 protected String typeTag;
//	 protected String typeName;
	 public CheckstyleClassInstantiatedTestCase(String aType, String anInstantiatedType) {
	        super(aType, aType + "!" + anInstantiatedType);
	        typeTag = aType;
	        instantiatedType = anInstantiatedType;
	        
	  }
	
	
	@Override
	public String regexLineFilter() {
		
//		return "(.*)Signature(.*)" + method + "(.*)" + type + "(.*)";
		return "(.*)" + instantiatedType + "(.*)should be instantiated by(.*)" + typeName + "(.*)";
//		expectedClassInstantiations: (ScannerBean.java:1) Type @Call//EC should be instantiated by grail.ScannerBean 	ScannerBean.java	/Assignment3-Semion/src/grail	line 1	Checkstyle Problem

	}
	 public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {

		 int i = 0;
		 TestCaseResult aResult = super.test(project, autoGrade);
	     return aResult;

	        
	        
	        
	 }



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Class matching " + instantiatedType + " not instantiated in " + typeName;
	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
		 return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
//		 if (aResult.getPercentage() != 1.0) {
//			 if (aProject.getEntryPoints() == null || aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT) == null)
//				 return aResult;
//			 String aMainClassUsed = aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT);
//			 if (aMainClassUsed.contains("main.") || aMainClassUsed.contains("Main.") ) {
//				 return partialPass(0.5, aResult.getNotes() + " but main package defined or main package has wrong case");
//			 }
//		 }
//		 return aResult;
	    	
	}

}

