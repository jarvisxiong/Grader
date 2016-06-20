package grader.junit;

import java.util.ArrayList;
import java.util.List;

public class AGradableJUnitTestSuite extends AGradableJUnitTest implements GradableJUnitSuite{
	List<GradableJUnitTest> children = new ArrayList();
	public AGradableJUnitTestSuite(Class aJUnitClass) {
		super(aJUnitClass);
	}
	@Override
	public List<GradableJUnitTest> getJUnitTests() {
		return children;
	}
	
}
