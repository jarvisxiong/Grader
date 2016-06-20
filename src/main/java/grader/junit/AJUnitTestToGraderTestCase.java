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
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.junit.test.directreference.ACartesianPointJUnitTester;

public class AJUnitTestToGraderTestCase extends BasicTestCase implements JUnitTestToGraderTestCase{
//	public static int DEFAULT_SCORE = 10;	
//	int defaultScore = DEFAULT_SCORE;
	Class jUnitClass;
//	boolean isExtra;
//	boolean isRestriction;
//	long maxScore;
//	String explanation;
//	String group = "";
	RunNotifier runNotifier = new RunNotifier();
	JUnitTestGraderProperties graderProperties;
	AJUnitRunToTestCaseResult runListener = new AJUnitRunToTestCaseResult();
	
	public AJUnitTestToGraderTestCase (Class aJUnitClass, JUnitTestGraderProperties aGraderProperties) {
	
		init();
		graderProperties = aGraderProperties;
		jUnitClass = aJUnitClass;

//		setJUnitClass(aJUnitClass);	
	}
	
//	public AJUnitTestToGraderTestCase () {
//		init();
//	}
	public void init() {
		runNotifier.addListener(runListener);
	}
	
	public Class getJUnitClass() {
		return jUnitClass;
	}
	
	public void setDefaultScore(int aDefaultScore) {
		graderProperties.setDefaultScore(aDefaultScore);
	}
	
	public int getDefaultScore() {
		return graderProperties.getDefaultScore();
	}
	
	public void setMaxScore (Class aJUnitClass) {
//		if (aJUnitClass.isAnnotationPresent(MaxValue.class)) {
//			MaxValue aMaxValue =  (MaxValue) aJUnitClass.getAnnotation(MaxValue.class);
//			maxScore = aMaxValue.value();
//		} else {
//			maxScore = defaultScore;
//		}
		graderProperties.setMaxScore(aJUnitClass);
	}
	public void setIsRestriction (Class aJUnitClass) {
//		if (aJUnitClass.isAnnotationPresent(IsRestriction.class)) {
//			IsRestriction anIsRestriction =  (IsRestriction) aJUnitClass.getAnnotation(IsRestriction.class);
//			isRestriction = anIsRestriction.value();
//		} else {
//			isRestriction = false;
//		}
		graderProperties.setIsRestriction(aJUnitClass);
	}
	public void setIsExtra (Class aJUnitClass) {
//		if (aJUnitClass.isAnnotationPresent(IsExtra.class)) {
//			IsExtra anIsExtra =  (IsExtra) aJUnitClass.getAnnotation(IsExtra.class);
//			isExtra = anIsExtra.value();
//		} else {
//			isExtra = false;
//		}
		graderProperties.setIsExtra(aJUnitClass);
	}
	
	public void setExplanation (Class aJUnitClass) {
//		if (aJUnitClass.isAnnotationPresent(Explanation.class)) {
//			Explanation anExplanation =  (Explanation) aJUnitClass.getAnnotation(Explanation.class);
//			explanation = anExplanation.value();
//		} else {
//			explanation = aJUnitClass.getSimpleName();
//		}
//		setName(explanation);
		graderProperties.setExplanation(aJUnitClass);
	}	

	public void setGroup (Class aJUnitClass) {
//		if (aJUnitClass.isAnnotationPresent(Group.class)) {
//			Group aGroup =  (Group) aJUnitClass.getAnnotation(Group.class);
//			group = aGroup.value();
//		} else {
//			group = explanation;
//		}
		graderProperties.setGroup(aJUnitClass);
	}
	@Override
	public String getGroup() {
//		return group;
		return graderProperties.getGroup();
	}
	public void setJUnitClass(Class aJUnitClass) {
		graderProperties.setJUnitClass(aJUnitClass);		
	}
	public boolean isRestriction() {
		return graderProperties.isRestriction();
	}
	public boolean isExtra() {
		return graderProperties.isExtra();
	}
	public Double getMaxScore() {
		return graderProperties.getMaxScore();
	}	
	public String getExplanation() {
		return graderProperties.getExplanation();
	}	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
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
			return fail(e.getMessage());
		}
		// InitializationError
//		Runner aRunner = new BlockJUnit4ClassRunner(ACartesianPointParametrizedJUnitTester.class);
//		Runner aRunner = new BlockJUnit4ClassRunner(ASinglePointBeforeClassJUnitMultiTester.class);
		// IniitializationError
//		Runner aRunner = new BlockJUnit4ClassRunner(ACartesianPointParametrizedJUnitMultiTester.class);
//		return null;
	}
	public static void main (String[] args) {
		AJUnitTestToGraderTestCase foo = new AJUnitTestToGraderTestCase(ACartesianPointJUnitTester.class, new AJUnitTestToGraderProperties(ACartesianPointJUnitTester.class));
//		foo.setJUnitClass(ACartesianPointJUnitTester.class);
		System.out.println (foo);
	}

	@Override
	public void setMaxScore(double aMaxScore) {
		graderProperties.setMaxScore(aMaxScore);
		
	}

	@Override
	public void setGroup(String newVal) {
		graderProperties.setGroup(newVal);
		
	}

	@Override
	public void setRestriction(boolean newVal) {
		graderProperties.setRestriction(newVal);
		
	}

	@Override
	public void setExtra(boolean newVal) {
		graderProperties.setExtra(newVal);
		
	}
}
