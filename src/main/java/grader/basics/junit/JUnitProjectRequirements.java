package grader.basics.junit;

import framework.grading.ProjectRequirements;

public interface JUnitProjectRequirements extends ProjectRequirements {

	void addJUnitTestSuite(Class<?> aJUnitSuiteClass);

}
