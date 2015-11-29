package gradingTools.comp401f15.assignment11.testcases;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.CheckStyleTestCase;

public class GenerictClassCheckStyleTestCase extends CheckStyleTestCase {
	public GenerictClassCheckStyleTestCase (String aTypeTag) {
		super (aTypeTag, aTypeTag + " is Generic Test Case");
	}

//	@Override
//	public TestCaseResult test(Project project, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		Class<?> clazz = IntrospectionUtil.getOrFindClass(project, this, tag);
//		if (clazz == null) {
//			return fail(aTypeTag + " not found.", autoGrade);
//		}
//		
////		ParameterizedType foo = (ParameterizedType) clazz.getGenericSuperclass();
//		Class bar = clazz.asSubclass(clazz);
//		Type super2 = bar.getGenericSuperclass();
//
//		Object anObject;
//		try {
//			anObject = clazz.newInstance();
//			
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			return fail ("table class does not have empty constructor");
//		}
//		Class aConcreteClass = anObject.getClass();
//		
//		ParameterizedType aGenericSuperClass = (ParameterizedType) aConcreteClass.getGenericSuperclass();
//	
//		
//		if (Modifier.isAbstract(clazz.getModifiers()))
//            return pass(autoGrade);
//        return fail(tag + " is not abstract.", autoGrade);
//	}

	@Override
	public String regexLineFilter() {
		// TODO Auto-generated method stub
		return "(.*)" + typeName + "(.*)" + "should be generic" + "(.*)";
	}

	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return typeTag + " should be generic";
	}
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
