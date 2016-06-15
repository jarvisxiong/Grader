package gradingTools.sharedTestCase;

import java.util.List;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.IntrospectionUtil;


public class ClassDefinedTestCase extends CheckStyleTestCase {
	 protected String descriptor;
	 public ClassDefinedTestCase(String aDescriptor) {
	        super(null, aDescriptor + " defined");
	        descriptor = aDescriptor;
	  }
	 protected boolean failOnMatch() {
	    	return false;
	   }
    
	@Override
	public String regexLineFilter() {
		return "(.*)Class matching " + descriptor + " defined(.*)";
	}
	 public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        TestCaseResult aResult = super.test(project, autoGrade);
//		 TestCaseResult aResult = fail("foo");

		 String aTag = descriptor;
		 if (descriptor.startsWith("@"))
			 aTag = descriptor.substring(1);
		 else 
			 return aResult;
	        if (aResult.getPercentage() == 1.0) {
	        	return aResult;
	        }
	        
	        	List<ClassDescription> aClasses = IntrospectionUtil.findClassesByTag(project, aTag);
	        	if (aClasses.size() == 1) {
	        		return pass();
	        	}
	        	if (aClasses.size() > 1) {
	        		return partialPass(0.5, "Multiple classes tagged:" + aTag + " " + aClasses);
	        	}
	        	return fail("No class tagged: " + aTag);
	        
	        
	        
	 }

//	 protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
////	    	int aNumFailedInstances = aFailedLines.size();
////	        int aTotalClassCount = aProject.getClassesManager().getClassDescriptions().size();
////	        String aNotes = failMessageSpecifier() + " in " + aNumFailedInstances + " out of " + aTotalClassCount + " classes ";
////	        return partialPass((aTotalClassCount - aNumFailedInstances)/aTotalClassCount, aNotes, autoGrade);  
//	    	int aNumMatchedInstances = aMatchedLines.size();
//	    	if (aNumMatchedInstances == 0 && failOnMatch() || aNumMatchedInstances == 1 && !failOnMatch())
//	    		return pass();
//	    	return computeResult(aProject, aCheckStyleLines, aMatchedLines, autoGrade);
//	    	
//	    }

	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Class matching " + descriptor + " not defined";
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

