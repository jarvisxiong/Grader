package grader.junit;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.GradableJUnitSuite;
import grader.basics.junit.GradableJUnitTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JUnitUtils extends BasicJUnitUtils{
	static GraderTestCase[] testCasesType = {};
	public static double  computeTotalScore (List<GraderTestCase> aJUnitTestToGraderTestCases) {
		double aRetVal = 0;
		for (GraderTestCase aJUnitTestToGraderTestCase:aJUnitTestToGraderTestCases) {
			Double aMaxScore = aJUnitTestToGraderTestCase.getMaxScore();
			if (aMaxScore != null)
			aRetVal += aJUnitTestToGraderTestCase.getMaxScore();
		}
		return aRetVal;
	}
	public static void setPointWeights (List<GraderTestCase> aJUnitTestToGraderTestCases) {
		double aTotalScore = computeTotalScore(aJUnitTestToGraderTestCases);
		setPointWeights(aJUnitTestToGraderTestCases, aTotalScore);
	}
	
	public static void setPointWeights (List<GraderTestCase> aJUnitTestToGraderTestCases, double aTotalScore) {
		for (GraderTestCase aJUnitTestToGraderTestCase:aJUnitTestToGraderTestCases) {
			Double aMaxScore = aJUnitTestToGraderTestCase.getMaxScore();
			if (aMaxScore != null)
			aJUnitTestToGraderTestCase.setPointWeight(aMaxScore/aTotalScore);
		}
	}

	public static  Map<String,List<GraderTestCase>>  toGraderTestCaseMap (Map<String,List<GradableJUnitTest>> aGradableJUnitTestCaseMap) {
		Map<String,List<GraderTestCase>> retVal = new HashMap();
		for (String aGroup:aGradableJUnitTestCaseMap.keySet()) {
			retVal.put(aGroup, toGraderTestCaseList(aGradableJUnitTestCaseMap.get(aGroup)));
		}
		return retVal;
	}
	public static GraderTestCase toGraderTestCase(GradableJUnitTest aGradableJUnitCase){
		return new AGraderTestCase(aGradableJUnitCase);
	}
	public static  List<GraderTestCase> toGraderTestCaseList(List<GradableJUnitTest> aGradableJUnitCaseList){
		List<GraderTestCase> retVal = new ArrayList();
		for (GradableJUnitTest aGradableJUnitTestCase:aGradableJUnitCaseList) {
			if (aGradableJUnitTestCase instanceof GradableJUnitSuite)  
				continue; // that is just for display and hierarchy purposes
			retVal.add(new AGraderTestCase(aGradableJUnitTestCase));
		}
		return retVal;
	}
}
