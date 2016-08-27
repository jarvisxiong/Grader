package grader.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runners.Suite;

import framework.grading.FrameworkProjectRequirements;
import grader.basics.junit.AGradableJUnitTest;
//import gradingTools.comp999junit.assignment1.testcases.reflection.ReflectiveCartesianPointSuite;


public class AnOriginalJUnitProjectRequirements extends FrameworkProjectRequirements implements JUnitProjectRequirements{
	@Override
	public void addJUnitTestSuite (Class<?> aJUnitSuiteClass) {
		Class[] aJUnitClasses = getJUnitTestClasses(aJUnitSuiteClass);
		Map<String, List<GraderTestCase>>  aGroupedTestCases = createAndCollectTestCases(aJUnitClasses);
		addGroupedTestCases(aGroupedTestCases);
		
	}
	static GraderTestCase[] testCasesType = {};
	
	public void addGroupedTestCases(Map<String, List<GraderTestCase>> aTestCases) {
		for (String aGroup:aTestCases.keySet()) {
			List<GraderTestCase> aJUnitTestToGraderTestCases = aTestCases.get(aGroup);
			double aTotalScore = computeTotalScore(aJUnitTestToGraderTestCases);
			setPointWeights(aJUnitTestToGraderTestCases, aTotalScore);
			GraderTestCase aFirstCase = aJUnitTestToGraderTestCases.get(0);
			
			boolean anIsRestriction = aFirstCase.isRestriction();
			boolean anIsExtraCredit = aFirstCase.isExtra();
			if (anIsRestriction) {
				addRestriction(aGroup, aTotalScore, aJUnitTestToGraderTestCases.toArray(testCasesType));
			} else {
				addFeature(aGroup, aTotalScore, anIsExtraCredit, aJUnitTestToGraderTestCases.toArray(testCasesType));
			}			
		}		
	}
	
	
	public double  computeTotalScore (List<GraderTestCase> aJUnitTestToGraderTestCases) {
		double aRetVal = 0;
		for (GraderTestCase aJUnitTestToGraderTestCase:aJUnitTestToGraderTestCases) {
			aRetVal += aJUnitTestToGraderTestCase.getMaxScore();
		}
		return aRetVal;
	}
	public void setPointWeights (List<GraderTestCase> aJUnitTestToGraderTestCases) {
		double aTotalScore = computeTotalScore(aJUnitTestToGraderTestCases);
		setPointWeights(aJUnitTestToGraderTestCases, aTotalScore);
	}
	
	public void setPointWeights (List<GraderTestCase> aJUnitTestToGraderTestCases, double aTotalScore) {
		for (GraderTestCase aJUnitTestToGraderTestCase:aJUnitTestToGraderTestCases) {
			aJUnitTestToGraderTestCase.setPointWeight(aJUnitTestToGraderTestCase.getMaxScore()/aTotalScore);
		}
	}
	
	public  Map<String, List<GraderTestCase>> createAndCollectTestCases(Class[] aJUnitClasses) {
		Map<String, List<GraderTestCase>> aResult = new HashMap();
		for (Class aJUnitClass:aJUnitClasses) {
			GraderTestCase aJUnitTestToGraderTestCase =
					 	new AGraderTestCase(new AGradableJUnitTest(aJUnitClass));
			String aGroup = aJUnitTestToGraderTestCase.getGroup();
			List<GraderTestCase> aClasses = aResult.get(aGroup);
			if (aClasses == null) {
				aClasses = new ArrayList();
				aResult.put(aGroup, aClasses);
			}
			aClasses.add(aJUnitTestToGraderTestCase);			
		}
		return aResult;
	}
	
	public static Class[] getJUnitTestClasses (Class<?> aJUnitSuiteClass) {
		Suite.SuiteClasses aSuiteClassAnnotation = aJUnitSuiteClass.getAnnotation(Suite.SuiteClasses.class);
		if (aSuiteClassAnnotation == null)
			return null;
		Class[] aTestClasses = aSuiteClassAnnotation.value();
		return aTestClasses;
	}
	
//	public static void main (String[] args) {
//		JUnitProjectRequirements aJUnitProjectRequirements = new AnOriginalJUnitProjectRequirements();
//		aJUnitProjectRequirements.addJUnitTestSuite(ReflectiveCartesianPointSuite.class);
//		aJUnitProjectRequirements.checkFeatures(null);
////		addJUnitTestSuite (CartesianPointSuite.class);
//		
//	}

}
