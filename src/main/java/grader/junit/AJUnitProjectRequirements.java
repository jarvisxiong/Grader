package grader.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runners.Suite;

import edu.emory.mathcs.backport.java.util.Arrays;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp999junit.assignment1.testcases.reflection.ReflectiveCartesianPointSuite;


public class AJUnitProjectRequirements extends FrameworkProjectRequirements implements JUnitProjectRequirements{
	@Override
	public void addJUnitTestSuite (Class<?> aJUnitSuiteClass) {
		List<Class> aJUnitOrSuiteClasses = getTopLevelJUnitTestClassesAndSuites(aJUnitSuiteClass);
		List<Class> aSuiteClasses = selectJUnitSuites(aJUnitOrSuiteClasses);
		List<Class> aTestCases = new ArrayList(aJUnitOrSuiteClasses);
		aTestCases.removeAll(aSuiteClasses);
		Map<String, List<JUnitTestToGraderTestCase>>  aGroupedTopLevelTestCases = createAndCollectTopLevelTestCases(aTestCases);
		Map<String, List<JUnitTestToGraderTestCase>>  aGroupedSuiteTestCases = createAndCollectSuiteTestCases(aSuiteClasses);
		Map<String,List<JUnitTestToGraderTestCase>> aGroupedTestCases = new HashMap();
		aGroupedTestCases.putAll(aGroupedTopLevelTestCases);
		aGroupedTestCases.putAll(aGroupedSuiteTestCases);
		
		addGroupedTwoLevelTestCases(aGroupedTestCases);
		
	}
	public List<Class> selectJUnitSuites (List<Class> aJUnitSuiteAndTestCases) {
		List<Class> retVal = new ArrayList();
		for (Class aJUnitTestCase:aJUnitSuiteAndTestCases) {
			if (isJUnitSuite(aJUnitTestCase)) {
				retVal.add(aJUnitTestCase);
			}
		}
		return retVal;
	}
//	public void addJUnitTestSuiteFlat (Class<?> aJUnitSuiteClass) {
//		List<Class> aJUnitClasses = getJUnitTestClassesDeep(aJUnitSuiteClass);
//		Map<String, List<JUnitTestToGraderTestCase>>  aGroupedTestCases = createAndCollectTopLevelTestCases(aJUnitClasses);
//		Map<String, List<JUnitTestToGraderTestCase>>  aGroupedSuiteTestCases = createAndCollectSuiteTestCases(aSuiteClasses)(aJUnitClasses);
//
//		addGroupedTwoLevelTestCases(aGroupedTestCases);
//		
//	}
	static JUnitTestToGraderTestCase[] testCasesType = {};
	
	public void addGroupedTwoLevelTestCases(Map<String, List<JUnitTestToGraderTestCase>> aTestCases) {
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
//	public void separateAndAddtestCasesAndSuite(Map<String, List<JUnitTestToGraderTestCase>> aTestCases) {
//		for (String aGroup:aTestCases.keySet()) {
//			List<JUnitTestToGraderTestCase> aJUnitTestToGraderTestCases = aTestCases.get(aGroup);
//			double aTotalScore = computeTotalScore(aJUnitTestToGraderTestCases);
//			setPointWeights(aJUnitTestToGraderTestCases, aTotalScore);
//			JUnitTestToGraderTestCase aFirstCase = aJUnitTestToGraderTestCases.get(0);
//			
//			boolean anIsRestriction = aFirstCase.isRestriction();
//			boolean anIsExtraCredit = aFirstCase.isExtra();
//			if (anIsRestriction) {
//				addRestriction(aGroup, aTotalScore, aJUnitTestToGraderTestCases.toArray(testCasesType));
//			} else {
//				addFeature(aGroup, aTotalScore, anIsExtraCredit, aJUnitTestToGraderTestCases.toArray(testCasesType));
//			}			
//		}		
//	}
	public void addGroupedFlatTestCases(Map<String, List<JUnitTestToGraderTestCase>> aTestCases) {
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
			Double aMaxScore = aJUnitTestToGraderTestCase.getMaxScore();
			if (aMaxScore != null)
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
			Double aMaxScore = aJUnitTestToGraderTestCase.getMaxScore();
			if (aMaxScore != null)
			aJUnitTestToGraderTestCase.setPointWeight(aMaxScore/aTotalScore);
		}
	}
	public  Map<String, List<JUnitTestToGraderTestCase>> createAndCollectSuiteTestCases(List<Class> aSuiteClasses) {
		Map<String, List<JUnitTestToGraderTestCase>> aResult = new HashMap();
		for (Class aSuiteClass:aSuiteClasses) {
			JUnitTestGraderProperties aSuiteProperties = new AJUnitTestToGraderProperties(aSuiteClass);
//			String aFeatureName =  aProperties.getExplanation();
			Double aFeatureScore = aSuiteProperties.getMaxScore();
			List<Class> aLeafTestCases = getJUnitTestClassesDeep(aSuiteClass);
			Double aTestMaxScore = null;
			if (aSuiteProperties.getMaxScore() != null) {
				aTestMaxScore = aSuiteProperties.getMaxScore()/aLeafTestCases.size();
			}

			List<JUnitTestToGraderTestCase> aTestCases = new ArrayList();
			for (Class aLeafTestCase:aLeafTestCases) {
				JUnitTestGraderProperties aTestCaseProperties = new AJUnitTestToGraderProperties(aLeafTestCase); 
				if (aTestMaxScore != null) {
					aTestCaseProperties.setMaxScore(aTestMaxScore);	
					aTestCaseProperties.setGroup(aSuiteProperties.getExplanation());
					aTestCaseProperties.setRestriction(aSuiteProperties.isRestriction());
					aTestCaseProperties.setExtra(aSuiteProperties.isExtra());
				}
				JUnitTestToGraderTestCase aJUnitTestToGraderTestCase =
					 	new AJUnitTestToGraderTestCase(aLeafTestCase, aTestCaseProperties);
				aTestCases.add(aJUnitTestToGraderTestCase);
			}
			
			aResult.put(aSuiteProperties.getExplanation(), aTestCases);
			
		}
		return aResult;
	}

	
	public  Map<String, List<JUnitTestToGraderTestCase>> createAndCollectTopLevelTestCases(List<Class> aJUnitClasses) {
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
//	public  Map<String, List<JUnitTestToGraderTestCase>> createAndCollectTwoLevelTestCases(List<Class> aJUnitClasses) {
//		Map<String, List<JUnitTestToGraderTestCase>> aResult = new HashMap();
//		for (Class aJUnitClass:aJUnitClasses) {
//			JUnitTestToGraderTestCase aJUnitTestToGraderTestCase =
//					 	new AJUnitTestToGraderTestCase(aJUnitClass, new AJUnitTestToGraderProperties(aJUnitClass));
//			String aGroup = aJUnitTestToGraderTestCase.getGroup();
//			List<JUnitTestToGraderTestCase> aClasses = aResult.get(aGroup);
//			if (aClasses == null) {
//				aClasses = new ArrayList();
//				aResult.put(aGroup, aClasses);
//			}
//			aClasses.add(aJUnitTestToGraderTestCase);			
//		}
//		return aResult;
//	}
	
	public static List<Class> getJUnitTestClassesDeep (Class<?> aJUnitSuiteClass) {
		Suite.SuiteClasses aSuiteClassAnnotation = aJUnitSuiteClass.getAnnotation(Suite.SuiteClasses.class);
		if (aSuiteClassAnnotation == null)
			return null;
		Class[] aTestClasses = aSuiteClassAnnotation.value();
		List<Class> retVal = new ArrayList();
		for (Class aTestClass: aTestClasses) {
			List<Class> aSubList = getJUnitTestClassesDeep(aTestClass);
			if (aSubList == null) {
				retVal.add(aTestClass);
			} else {
				retVal.addAll(aSubList);
			}
		}
		return retVal;
	}
	public static boolean isJUnitSuite (Class<?> aClass) {
		return aClass.getAnnotation(Suite.SuiteClasses.class) != null;
	}
	public static List<Class> getTopLevelJUnitTestClassesAndSuites (Class<?> aJUnitSuiteClass) {
		Suite.SuiteClasses aSuiteClassAnnotation = aJUnitSuiteClass.getAnnotation(Suite.SuiteClasses.class);
		if (aSuiteClassAnnotation == null)
			return null;
		Class[] aTestClasses = aSuiteClassAnnotation.value();
		return Arrays.asList(aTestClasses);
	}
	
	public static void main (String[] args) {
		JUnitProjectRequirements aJUnitProjectRequirements = new AJUnitProjectRequirements();
		aJUnitProjectRequirements.addJUnitTestSuite(ReflectiveCartesianPointSuite.class);
		aJUnitProjectRequirements.checkFeatures(null);
//		addJUnitTestSuite (CartesianPointSuite.class);
		
	}

}
