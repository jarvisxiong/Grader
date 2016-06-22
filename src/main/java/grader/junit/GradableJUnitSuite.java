package grader.junit;

import java.util.List;

import framework.grading.testing.TestCaseResult;


public interface GradableJUnitSuite extends GradableJUnitTest{
//	List<GradableJUnitTest> getJUnitTests();
	public int size() ;
	public void add(GradableJUnitTest anElement) ;
	public void addAll(List<GradableJUnitTest> anElement) ;
	void testAll();
	

}
