package grader.junit;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import util.annotations.Position;
import util.annotations.Visible;

public class AGradableJUnitSuite extends AGradableJUnitTest implements GradableJUnitSuite, PropertyChangeListener{
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
//		System.out.println ("opened: " + aTest);
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
	protected int numTestsSuceeded() {
		int retVal = 0;
		for (GradableJUnitTest aTest:children) {
			if (aTest.getMessage().isEmpty())
				retVal++;
		}
		return retVal;
	}
	@Visible(false)
	public TestCaseResult test()
			throws NotAutomatableException, NotGradableException {
		for (GradableJUnitTest aTest:children) {
			aTest.test();
		}
		int aNumSuccesses = numTestsSuceeded();
		if (aNumSuccesses == children.size()) {
			return new TestCaseResult(true, getExplanation());
		} else {
			return new TestCaseResult(((double) aNumSuccesses)/children.size(), getExplanation());
		}
	}
//	protected Color computeColor() {
//		double aFractionCorrect = ((double) numTestsSuceeded())/children.size());
//		if (aFractionCorrect == 1)
//			return ALL_PASS_COLOR;
//		else if (aFractionCorrect == 0) {
//			
//		}
//	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
}
