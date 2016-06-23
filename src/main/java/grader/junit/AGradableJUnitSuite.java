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
	@Visible(false)
	public void add(GradableJUnitTest anElement) {
		children.add(anElement);
		anElement.addPropertyChangeListener(this);
	}
	@Visible(false)
	public void addAll(List<GradableJUnitTest> anElement) {
		children.addAll(anElement);
		for (GradableJUnitTest aTest:anElement) {
			aTest.addPropertyChangeListenerRecursive(this);
		}
	}
//	@Override
//	public List<GradableJUnitTest> getJUnitTests() {
//		return children;
//	}
	public String getName() {
		return getExplanation();
	}
	@Visible(false)
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
			if (aTest.getFractionComplete() == 1.0)
				retVal++;
		}
		return retVal;
	}
	@Visible(false)
	public int numTests() {
		int retVal = 0;
		for (GradableJUnitTest aTest:children) {
				
				retVal += aTest.numTests() > 0?1:0;;
		}
		return retVal;
	}
	// does not retiurn a result
	@Override
	public void testAll() {
		test();
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
	@Override
	@Visible(false)
	public List<Double>  getPercentages(){
		List retVal = new ArrayList();
		for (GradableJUnitTest aTest:children) {
			retVal.addAll(aTest.getPercentages());
		}
		return retVal;
	}
	@Visible(false)
	public List<String>  getMessages(){
		List<String> retVal = new ArrayList();
		for (GradableJUnitTest aTest:children) {
			retVal.addAll(aTest.getMessages());
		}
		return retVal;
	}
	@Visible(false)
	public List<TestCaseResult>  getTestCaseResults(){
		List<TestCaseResult> retVal = new ArrayList();
		for (GradableJUnitTest aTest:children) {
			retVal.addAll(aTest.getTestCaseResults());
		}
		return retVal;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!"Status".equals(evt.getPropertyName())) 
				return; // do not want this computed multiple times for different properties of test
		numTests = numTests();
		fractionComplete = ((double) numTestsSuceeded())/children.size();
		showColor();
		
	}
	@Override
	@Visible(false)
	public void addPropertyChangeListenerRecursive(PropertyChangeListener arg0) {
		for (GradableJUnitTest aTest:children) {
			aTest.addPropertyChangeListener(arg0);
	}		
	}
	@Override
	@Visible(false)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		super.addPropertyChangeListener(arg0);	
		
	}
	
}
