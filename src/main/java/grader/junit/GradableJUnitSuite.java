package grader.junit;

import java.util.List;

import framework.grading.testing.TestCaseResult;


public interface GradableJUnitSuite extends GradableJUnitTest{
	List<GradableJUnitTest> getJUnitTests();
	

}
