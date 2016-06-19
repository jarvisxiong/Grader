package grader.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runners.Suite;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp999junit.assignment1.testcases.reflection.ReflectiveCartesianPointSuite;


public class AnOriginalJUnitProjectRequirements extends FrameworkProjectRequirements implements JUnitProjectRequirements{
	@Override
	public void addJUnitTestSuite (Class<?> aJUnitSuiteClass) {
		Class[] aJUnitClasses = getJUnitTestClasses(aJUnitSuiteClass);
		Map<String, List<JUnitTestToGraderTestCase>>  aGroupedTestCases = createAndCollectTestCases(aJUnitClasses);
		addGroupedTestCases(aGroupedTestCases);
		
	}
	static JUnitTestToGraderTestCase[] testCasesType = {};
	
	public void addGroupedTestCases(Map<String, List<JUnitTestToGraderTestCase>> aTestCases) {
		for (String aGroup:aTestCases.keySet()) {
			List<JUnitTestToGraderTestCase> aJUnitTestToGraderTestCases = aTestCases.get(aGroup);
			double aTotalScore = computeTotalScore(aJUnitTestToGraderTestCases);
			setPointWeights(aJUnitTestToGraderTestCases, aTotalScore);
			JUnitTestToGraderTestCase aFirstCase = aJUnitTestToGraderTestCases.get(0);
			
			boolean anIsRestriction = aFirstCase.isRestriction();
			boolean anIsExtraCredit = aFirstCase.isExtra();
			if (anIsRestriction) {
				addRestriction(aGroup, aTotalScore, aJUnitTestToGraderTestCases.toArray(testCasesType));
			} else {
				addFeature(aGroup, aTotalScore, anIsExtraCredit, aJUnitTestToGraderTestCases.toArray(testCasesType));
			}			
		}		
	}
	
	
	public double  computeTotalScore (List<JUnitTestToGraderTestCase> aJUnitTestToGraderTestCases) {
		double aRetVal = 0;
		for (JUnitTestToGraderTestCase aJUnitTestToGraderTestCase:aJUnitTestToGraderTestCases) {
			aRetVal += aJUnitTestToGraderTestCase.getMaxScore();
		}
		return aRetVal;
	}
	public void setPointWeights (List<JUnitTestToGraderTestCase> aJUnitTestToGraderTestCases) {
		double aTotalScore = computeTotalScore(aJUnitTestToGraderTestCases);
		setPointWeights(aJUnitTestToGraderTestCases, aTotalScore);
	}
	
	public void setPointWeights (List<JUnitTestToGraderTestCase> aJUnitTestToGraderTestCases, double aTotalScore) {
		for (JUnitTestToGraderTestCase aJUnitTestToGraderTestCase:aJUnitTestToGraderTestCases) {
			aJUnitTestToGraderTestCase.setPointWeight(aJUnitTestToGraderTestCase.getMaxScore()/aTotalScore);
		}
	}
	
	public  Map<String, List<JUnitTestToGraderTestCase>> createAndCollectTestCases(Class[] aJUnitClasses) {
		Map<String, List<JUnitTestToGraderTestCase>> aResult = new HashMap();
		for (Class aJUnitClass:aJUnitClasses) {
			JUnitTestToGraderTestCase aJUnitTestToGraderTestCase =
					 	new AJUnitTestToGraderTestCase(aJUnitClass, new AJUnitTestToGraderProperties(aJUnitClass));
			String aGroup = aJUnitTestToGraderTestCase.getGroup();
			List<JUnitTestToGraderTestCase> aClasses = aResult.get(aGroup);
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
	
	public static void main (String[] args) {
		JUnitProjectRequirements aJUnitProjectRequirements = new AnOriginalJUnitProjectRequirements();
		aJUnitProjectRequirements.addJUnitTestSuite(ReflectiveCartesianPointSuite.class);
		aJUnitProjectRequirements.checkFeatures(null);
//		addJUnitTestSuite (CartesianPointSuite.class);
		
	}

}
