package grader.junit;

import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.GradableJUnitTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JUnitUtils extends BasicJUnitUtils{
	public static  Map<String,List<GraderTestCase>>  toGraderTestCaseMap (Map<String,List<GradableJUnitTest>> aGradableJUnitTestCaseMap) {
		Map<String,List<GraderTestCase>> retVal = new HashMap();
		for (String aGroup:aGradableJUnitTestCaseMap.keySet()) {
			retVal.put(aGroup, toGraderTestCaseList(aGradableJUnitTestCaseMap.get(aGroup)));
		}
		return retVal;
	}
}
