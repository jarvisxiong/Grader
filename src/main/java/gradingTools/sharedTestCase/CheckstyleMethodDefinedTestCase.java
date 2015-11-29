package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.MainClassFinder;
import grader.sakai.project.SakaiProject;
import grader.util.IntrospectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.rmi.CORBA.ClassDesc;

import wrappers.framework.project.ProjectWrapper;


public class CheckstyleMethodDefinedTestCase extends CheckStyleTestCase {
	 protected String method;
//	 protected String typeTag;
//	 protected String typeName;
	 public CheckstyleMethodDefinedTestCase(String aType, String aMethod) {
	        super(aType, aType + "!" + aMethod);
	        typeTag = aType;
	        method = aMethod;
	        
	  }
	 @Override
	 protected String typeTag() {
			return typeTag;
		}
    
	@Override
	public String regexLineFilter() {
		
//		return "(.*)Signature(.*)" + method + "(.*)" + type + "(.*)";
		return "(.*)Signature(.*)" + method + "(.*)" + typeName + "(.*)";

	}
	 public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	     Class aClass = IntrospectionUtil.getOrFindClass(project, this, typeTag); 
	     if (aClass == null) {
	    	 return fail("Type " + typeTag + "not defined, cannot check method");
	     }
	     typeName = aClass.getSimpleName();
		 TestCaseResult aResult = super.test(project, autoGrade);
//		 TestCaseResult aResult = fail("foo");
	        return aResult;

	        
	        
	        
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
		return "Method call matching " + method + " not made by " + typeName;
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

