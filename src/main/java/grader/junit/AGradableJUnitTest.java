package grader.junit;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import util.annotations.Explanation;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;
import util.annotations.Visible;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.junit.test.directreference.ACartesianPointJUnitTester;

public class AGradableJUnitTest implements GradableJUnitTest{
	static int DEFAULT_SCORE = 0;	
	int defaultScore = DEFAULT_SCORE;
	Class jUnitClass;
	boolean isExtra;
	boolean isRestriction;
	Double maxScore;
	String explanation;
	String group = "";
	RunNotifier runNotifier = new RunNotifier();
	AJUnitRunToTestCaseResult runListener = new AJUnitRunToTestCaseResult();
	
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
	@Visible(false)
	public TestCaseResult test()
			throws NotAutomatableException, NotGradableException {
		try {
			Class aJUnitClass = getJUnitClass();
			runListener.setJUnitName(aJUnitClass.getName());
			Runner aRunner = new BlockJUnit4ClassRunner(aJUnitClass);
			aRunner.run(runNotifier);
			return runListener.getTestCaseResult();

			
		} catch (InitializationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new TestCaseResult(false, e.getMessage(), getExplanation(), true);
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
	public static void main (String[] args) {
		AGradableJUnitTest foo = new AGradableJUnitTest(ACartesianPointJUnitTester.class);
//		foo.setJUnitClass(ACartesianPointJUnitTester.class);
		System.out.println (foo);
	}
}
