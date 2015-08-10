package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.sakai.project.SakaiProject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import wrappers.framework.project.ProjectWrapper;


public abstract class CheckStyleTestCase extends BasicTestCase {
    public CheckStyleTestCase(String aName) {
        super(aName);
    }
    
    public static  List<String> failedLines (String[] aLines, String aRegex) {
    	List<String> result = new ArrayList();    
//    	int aCount = 0;
    	for (String aLine:aLines) {
    		if (aLine.matches(aRegex))
    			result.add(aLine);
    	}
    	return result;
    }
    public static  int numMatches (String[] aLines, String aRegex) {
    	return failedLines(aLines, aRegex).size();
    }
    public abstract String regexLineFilter();
    public abstract String failMessageSpecifier();
    protected TestCaseResult test(SakaiProject aProject, String[] aCheckStyleLines, boolean autoGrade) {
    	List<String> aFailedLines = failedLines(aCheckStyleLines, regexLineFilter());
    	return test(aProject, aCheckStyleLines, aFailedLines, autoGrade);    	
    }
    
    protected TestCaseResult classFractionPenalty (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
    	int aNumFailedInstances = aFailedLines.size();    	
        int aTotalClassCount = aProject.getClassesManager().getClassDescriptions().size();
        String aNotes = failMessageSpecifier() + " in " + aNumFailedInstances + " out of " + aTotalClassCount + " classes ";
        return partialPass((aTotalClassCount - aNumFailedInstances)/aTotalClassCount, aNotes, autoGrade);    
    	
    }
    protected TestCaseResult numMistakesPenalty (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
    	int aNumFailedInstances = aFailedLines.size();    	
    	double aPenalty = penaltyForMistakes(aNumFailedInstances);
        String aNotes = failMessageSpecifier() + " " + aNumFailedInstances + " number of times";
        return partialPass((1 - aPenalty), aNotes, autoGrade);    
    	
    }
    
    protected TestCaseResult penalty (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
    	return numMistakesPenalty(aProject, aCheckStyleLines, aFailedLines, autoGrade);
    	
    }
    
    protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
//    	int aNumFailedInstances = aFailedLines.size();
//        int aTotalClassCount = aProject.getClassesManager().getClassDescriptions().size();
//        String aNotes = failMessageSpecifier() + " in " + aNumFailedInstances + " out of " + aTotalClassCount + " classes ";
//        return partialPass((aTotalClassCount - aNumFailedInstances)/aTotalClassCount, aNotes, autoGrade);  
    	int aNumFailedInstances = aFailedLines.size();
    	if (aNumFailedInstances == 0)
    		return pass();
    	return penalty(aProject, aCheckStyleLines, aFailedLines, autoGrade);
    	
    }
    protected double penaltyForMistakeNumber(int aMistakeNumber) {
    	return 1.0/(Math.pow(2, aMistakeNumber+1)); // starting at 0
    }
    protected double penaltyForMistakes(int aNumMistakes) {
    	double aPenalty = 0;
    	for (int aMistakeNumber = 0; aMistakeNumber < aNumMistakes; aMistakeNumber++) {
    		aPenalty += penaltyForMistakeNumber(aMistakeNumber);
    	}
    	return aPenalty;
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
        SakaiProject aProject = ((ProjectWrapper) project).getProject();
        String aCheckStyleText = aProject.getCheckstyleText();
        String aCheckStyleFileName = aProject.getCheckStyleFileName(); // can read lines from this, maybe more efficient
        String[] aCheckStyleLines = aCheckStyleText.split(System.getProperty("line.separator"));
        return test(aProject, aCheckStyleLines, autoGrade);
        
    }
}

