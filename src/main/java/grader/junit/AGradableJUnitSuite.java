package grader.junit;

import java.util.ArrayList;
import java.util.List;

import util.annotations.Position;
import util.annotations.Visible;

public class AGradableJUnitSuite extends AGradableJUnitTest implements GradableJUnitSuite{
	List<GradableJUnitTest> children = new ArrayList();
	public AGradableJUnitSuite(Class aJUnitClass) {
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
	public void open(GradableJUnitTest aTest) {
		System.out.println ("opened: " + aTest);
		aTest.test();
	}
	@Visible(false)
	public String getStatus() {
		return "";
	}
	@Position(1)
	@Visible(false)
	public String getMessage() {
		return "";
	}
	
}
