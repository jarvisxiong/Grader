package grader.junit;

import java.util.ArrayList;
import java.util.List;

public class AGradableJUnitTestSuite extends AGradableJUnitTest implements GradableJUnitSuite{
	List<GradableJUnitTest> children = new ArrayList();
	public AGradableJUnitTestSuite(Class aJUnitClass) {
		super(aJUnitClass);
	}
	public GradableJUnitTest get(int anIndex) {
		return children.get(anIndex);
	}
	public int size() {
		return children.size();
	}
	public void add(GradableJUnitTest anElement) {
		children.add(anElement);
	}
	public void addAll(List<GradableJUnitTest> anElement) {
		children.addAll(anElement);
	}
//	@Override
//	public List<GradableJUnitTest> getJUnitTests() {
//		return children;
//	}
	public String getName() {
		return getExplanation();
	}
	
}
