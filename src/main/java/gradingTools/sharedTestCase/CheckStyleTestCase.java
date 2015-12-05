package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.IntrospectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import wrappers.framework.project.ProjectWrapper;


public abstract class CheckStyleTestCase extends BasicTestCase {
	
	protected boolean foundType;
	protected String typeTag;
	 protected String typeName;


    public CheckStyleTestCase(String aTypeTag, String aName) {
        super(aName);
        foundType = false;
        typeTag = aTypeTag;
    }
    
	protected String typeTag() {
		return typeTag;
	}
	protected boolean foundType() {
		return foundType;
	}
	// interface defined should also use similar syntax
	protected String typeRegex(String aTypeTag) {
		return "(.*)" + "matching" + "(.*)" + aTypeTag + "(.*)" + "defined" + "(.*)" ;
	}
    
    protected boolean failOnMatch() {
    	return true;
    }
    
    public static  List<String> matchedLines (String[] aLines, String aRegex) {
    	List<String> result = new ArrayList();    
//    	int aCount = 0;
    	for (String aLine:aLines) {
    		if (aLine.matches(aRegex))
    			result.add(aLine);
    	}
    	return result;
    }
    public static  int numMatches (String[] aLines, String aRegex) {
    	return matchedLines(aLines, aRegex).size();
    }
    public abstract String regexLineFilter();
    public abstract String failMessageSpecifier();
    protected TestCaseResult test(SakaiProject aProject, String[] aCheckStyleLines, boolean autoGrade) {
    	String aTypeTag = typeTag();
    	if (aTypeTag != null) {
    	List<String> aTypeDefinedLines = matchedLines(aCheckStyleLines, typeRegex(aTypeTag));
    	  this.foundType = aTypeDefinedLines.size() > 0;
    	  if (!foundType) {
    		  return fail (aTypeTag + " not found");
    	  }
    	}
    	List<String> aFailedLines = matchedLines(aCheckStyleLines, regexLineFilter());
    	
    	return test(aProject, aCheckStyleLines, aFailedLines, autoGrade);    	
    }
    
    protected TestCaseResult classFractionResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
    	int aNumMatchedInstances = aMatchedLines.size();    	
        int aTotalClassCount = aProject.getClassesManager().getClassDescriptions().size();
        String aNotes = failMessageSpecifier() + " in " + aNumMatchedInstances + " out of " + aTotalClassCount + " classes ";
        return partialPass((aTotalClassCount - aNumMatchedInstances)/aTotalClassCount, aNotes, autoGrade);    
    	
    }
    protected TestCaseResult numMatchesResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
    	int aNumFailedInstances = aFailedLines.size();   
    	int i = 0;
    	double aScore = scoreForMatches(aNumFailedInstances);
        String aNotes = failMessageSpecifier() + " " + aNumFailedInstances + " number of times";
        return partialPass((1 - aScore), aNotes, autoGrade);    
    	
    }
    
    protected TestCaseResult singleMatchScore (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
    	
        String aNotes = failMessageSpecifier(); 
        return fail(aNotes, autoGrade);    
    	
    }
    
    protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
    	return numMatchesResult(aProject, aCheckStyleLines, aFailedLines, autoGrade);
    	
    }
    protected boolean isPassed(int aNumMatchedInstances) {
    	return aNumMatchedInstances == 0 && failOnMatch() || aNumMatchedInstances == 1 && !failOnMatch();
    }
    protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
//    	int aNumFailedInstances = aFailedLines.size();
//        int aTotalClassCount = aProject.getClassesManager().getClassDescriptions().size();
//        String aNotes = failMessageSpecifier() + " in " + aNumFailedInstances + " out of " + aTotalClassCount + " classes ";
//        return partialPass((aTotalClassCount - aNumFailedInstances)/aTotalClassCount, aNotes, autoGrade);  
    	int aNumMatchedInstances = aMatchedLines.size();
//    	if (aNumMatchedInstances == 0 && failOnMatch() || aNumMatchedInstances == 1 && !failOnMatch())
        if (isPassed(aNumMatchedInstances))

    		return pass();
    	return computeResult(aProject, aCheckStyleLines, aMatchedLines, autoGrade);
    	
    }
    protected double scoreForMatchNumber(int aMistakeNumber) {
    	return 1.0/(Math.pow(2, aMistakeNumber+1)); // starting at 0
    }
    protected double scoreForMatches(int aNumMistakes) {
    	double aScore = 0;
    	for (int aMistakeNumber = 0; aMistakeNumber < aNumMistakes; aMistakeNumber++) {
    		aScore += scoreForMatchNumber(aMistakeNumber);
    	}
    	return aScore;
    }
//    protected TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
//    	int aNumFailedInstances = aFailedLines.size();
//    	double penaltyPerMistake = 0.2;
//        int aTotalClassCount = aProject.getClassesManager().getClassDescriptions().size();
//        String aNotes = failMessageSpecifier() + " in " + aNumFailedInstances + " out of " + aTotalClassCount + " classes ";
//        return partialPass((aTotalClassCount - aNumFailedInstances)/aTotalClassCount, aNotes, autoGrade);    
//    	
//    }
    
    

//    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
        String aTypeTag = typeTag();
        if (aTypeTag != null) {
        Class aClass = IntrospectionUtil.getOrFindClass(project, this, typeTag); 
	     if (aClass == null) {
	    	 return fail("Type " + aTypeTag + "not defined, cannot check");
	     }
	     typeName = aClass.getSimpleName();
        }
        SakaiProject aProject = ((ProjectWrapper) project).getProject();
        String aCheckStyleText = aProject.getCheckstyleText();
        String aCheckStyleFileName = aProject.getCheckStyleFileName(); // can read lines from this, maybe more efficient
        String[] aCheckStyleLines = aCheckStyleText.split(System.getProperty("line.separator"));
        return test(aProject, aCheckStyleLines, autoGrade);
        
    }
    public   String toClassName(String aCheckstyleMessage) {
		 int anIndex1 = aCheckstyleMessage.indexOf("(");
		 int anIndex2 = aCheckstyleMessage.indexOf (")");
		 if (anIndex1 < 0 || anIndex2 < 0 || anIndex2 <= anIndex1)
			 return "";
		 return aCheckstyleMessage.substring(anIndex1 + 1, anIndex2);
				
	 }
}

