package grader.junit;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.introspect.Attribute;
import util.annotations.Explanation;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;
import util.annotations.Position;
import util.annotations.Visible;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.junit.test.directreference.ACartesianPointJUnitTester;

public class AGradableJUnitTest implements GradableJUnitTest{
	public static final Color UNTESTED_COLOR = Color.BLACK;
	public static final Color ALL_FAIL_COLOR = Color.RED;
	public static final Color MOSTLY_FAIL_COLOR = Color.PINK;
	public static final Color MOSTLY_PASS_COLOR = Color.ORANGE;
	public static final Color ALL_PASS_COLOR = Color.GREEN;
	static int DEFAULT_SCORE = 0;	
	int defaultScore = DEFAULT_SCORE;
	Class jUnitClass;
	Color color = UNTESTED_COLOR;
	boolean isExtra;
	boolean isRestriction;
	Double maxScore;
	String explanation;
	String group = "";
	RunNotifier runNotifier = new RunNotifier();
	AJUnitRunToTestCaseResult runListener = new AJUnitRunToTestCaseResult();
	int numTests = 0;
	double fractionComplete = 0;
	String status = "Not Tested";
	String message = "";
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	
	public AGradableJUnitTest (Class aJUnitClass) {
		init();
		setJUnitClass(aJUnitClass);	
	}
	
//	public AJUnitTestToGraderTestCase () {
//		init();
//	}
	@Visible(false)
	public void init() {
		runNotifier.addListener(runListener);
	}
	@Visible(false)
	public Class getJUnitClass() {
		return jUnitClass;
	}
	@Visible(false)

	public void setDefaultScore(int aDefaultScore) {
		defaultScore = aDefaultScore;
	}
	@Visible(false)
	public int getDefaultScore() {
		return defaultScore;
	}
	@Visible(false)
	public void setMaxScore (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(MaxValue.class)) {
			MaxValue aMaxValue =  (MaxValue) aJUnitClass.getAnnotation(MaxValue.class);
			maxScore = (double) aMaxValue.value();
		} else {
			maxScore = null;
		}
	}
	@Visible(false)
	public void setIsRestriction (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(IsRestriction.class)) {
			IsRestriction anIsRestriction =  (IsRestriction) aJUnitClass.getAnnotation(IsRestriction.class);
			isRestriction = anIsRestriction.value();
		} else {
			isRestriction = false;
		}
	}
	@Visible(false)
	public void setIsExtra (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(IsExtra.class)) {
			IsExtra anIsExtra =  (IsExtra) aJUnitClass.getAnnotation(IsExtra.class);
			isExtra = anIsExtra.value();
		} else {
			isExtra = false;
		}
	}
	@Visible(false)
	public void setExplanation (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(Explanation.class)) {
			Explanation anExplanation =  (Explanation) aJUnitClass.getAnnotation(Explanation.class);
			explanation = anExplanation.value();
		} else {
			explanation = aJUnitClass.getSimpleName();
		}
//		setName(explanation);
	}	
	@Visible(false)
	public void setGroup (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(Group.class)) {
			Group aGroup =  (Group) aJUnitClass.getAnnotation(Group.class);
			group = aGroup.value();
		} else {
			group = explanation;
		}
	}
	@Override
	@Visible(false)
	public String getGroup() {
		return group;
	}
	@Visible(false)
	public void setJUnitClass(Class aJUnitClass) {
		jUnitClass = aJUnitClass;
		setExplanation(aJUnitClass);
		setMaxScore(aJUnitClass);
		setIsRestriction(aJUnitClass);
		setIsExtra(aJUnitClass);
		setGroup(aJUnitClass);
//		this.jUnitClass = aJUnitClass;
//		if (aJUnitClass.isAnnotationPresent(MaxValue.class)) {
//			MaxValue aMaxValue =  (MaxValue) aJUnitClass.getAnnotation(MaxValue.class);
//			maxScore = aMaxValue.value();
//		} else {
//			maxScore = DEFAULT_SCORE;
//		}
		
	}
	@Visible(false)
	public boolean isRestriction() {
		return isRestriction;
	}
	@Visible(false)
	public boolean isExtra() {
		return isExtra;
	}
	@Visible(false)
	public Double getMaxScore() {
		return maxScore;
	}
	@Visible(false)
	public String getExplanation() {
		return explanation;
	}
	protected void showColor() {
		Color oldColor = color;
		color = computeColor();
		propertyChangeSupport.firePropertyChange("this", oldColor,
				new Attribute(AttributeNames.COMPONENT_FOREGROUND, color));
	}
	protected void showResult (TestCaseResult aTestCaseResult) {
		String oldStatus = status;
		String oldMessage = message;
		status = aTestCaseResult.getPercentage()*100 + "% complete";
		message = aTestCaseResult.getNotes();
		
		propertyChangeSupport.firePropertyChange("Status", oldStatus, status);
		propertyChangeSupport.firePropertyChange("Message", oldMessage, message);
		showColor();
//		Color oldColor = color;
//		Color color = computeColor();
//		propertyChangeSupport.firePropertyChange("this", oldColor,
//				new Attribute(AttributeNames.COMPONENT_FOREGROUND, color));
		
	}
	@Visible(false)
	public TestCaseResult test()
			throws NotAutomatableException, NotGradableException {
		try {
			numTests++;
			Class aJUnitClass = getJUnitClass();
			runListener.setJUnitName(aJUnitClass.getName());
			Runner aRunner = new BlockJUnit4ClassRunner(aJUnitClass);
			aRunner.run(runNotifier);
			TestCaseResult aTestCaseResult = runListener.getTestCaseResult();
			fractionComplete = aTestCaseResult.getPercentage();
			showResult(aTestCaseResult);
//			status = aTestCaseResult.getPercentage()*100 + " % complete";
//			message = aTestCaseResult.getNotes();			
			return aTestCaseResult;

			
		} catch (InitializationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TestCaseResult aTestCaseResult = new TestCaseResult(false, e.getMessage(), getExplanation(), true);
			showResult(aTestCaseResult);
			return aTestCaseResult;
//			return fail(e.getMessage());
		}
		// InitializationError
//		Runner aRunner = new BlockJUnit4ClassRunner(ACartesianPointParametrizedJUnitTester.class);
//		Runner aRunner = new BlockJUnit4ClassRunner(ASinglePointBeforeClassJUnitMultiTester.class);
		// IniitializationError
//		Runner aRunner = new BlockJUnit4ClassRunner(ACartesianPointParametrizedJUnitMultiTester.class);
//		return null;
	}
	
	@Visible(false)
	@Override
	public void setMaxScore(double aMaxScore) {
		maxScore = aMaxScore;
	}
	@Visible(false)
	@Override
	public void setGroup(String newVal) {
		group = newVal;
		
	}
	@Visible(false)
	@Override
	public void setRestriction(boolean newVal) {
		isRestriction = newVal;		
	}
	@Visible(false)
	@Override
	public void setExtra(boolean newVal) {
		isExtra = newVal;		
	}
	@Visible(false)
	@Override
	public void setExplanation(String newVal) {
		explanation = newVal;		
	}
	protected String description = null;
	public String getName() {
		if (description == null) {
		String aScore = "[" + maxScore + " pts" + "]";
		String anExtra = isExtra?"(extra credit)":"";
		description = explanation + aScore + anExtra;
		}
		return description;
	}
	@Visible(true)
	@Position(0)
	@Override
	public String getStatus() {
		return status;
	}
	@Position(1)
	@Override
	public String getMessage() {
		return message;
	}
	@Visible(false)
	public void open(String aField) {
//		System.out.println ("opened: " + aTest);
		test();
	}
	@Override
	@Visible(false)
	public int numTests() {
		return numTests;
	}
	protected Color computeColor(int aNumTests,double aFractionComplete) {
		if (aNumTests == 0)
			return UNTESTED_COLOR;
		if (aFractionComplete == 1)
			return ALL_PASS_COLOR;
		if (aFractionComplete == 0)
			return ALL_FAIL_COLOR;		
		if (aFractionComplete >= 0.5)
			return MOSTLY_PASS_COLOR;
		return MOSTLY_FAIL_COLOR;
	}
	protected Color computeColor() {
		return computeColor(numTests, fractionComplete);
//		if (numTests == 0)
//			return UNTESTED_COLOR;
//		if (fractionComplete == 1)
//			return ALL_PASS_COLOR;
//		if (fractionComplete == 0)
//			return ALL_FAIL_COLOR;		
//		if (fractionComplete >= 0.5)
//			return MOSTLY_PASS_COLOR;
//		return MOSTLY_FAIL_COLOR;
	}
//	double aFractionCorrect = ((double) numTestsSuceeded())/children.size());
//	if (aFractionCorrect == 1)
//		return ALL_PASS_COLOR;
//	else if (aFractionCorrect == 0) {
//		
//	}
//}
	@Override
	@Visible(false)
	public double getFractionComplete() {
		return fractionComplete;
	}
	@Override
	@Visible(false)
	public void addPropertyChangeListenerRecursive(PropertyChangeListener arg0) {
		addPropertyChangeListener(arg0);
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyChangeSupport.addPropertyChangeListener(arg0);
		
	}
	public static void main (String[] args) {
		ObjectEditor.edit(new bus.uigen.test.ACompositeColorer());
		AGradableJUnitTest foo = new AGradableJUnitTest(ACartesianPointJUnitTester.class);
//		foo.setJUnitClass(ACartesianPointJUnitTester.class);
		System.out.println (foo);
	}
}
