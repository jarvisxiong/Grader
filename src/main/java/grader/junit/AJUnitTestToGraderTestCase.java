package grader.junit;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCase;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import util.annotations.Explanation;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;

public class AJUnitTestToGraderTestCase extends BasicTestCase {
	public static int DEFAULT_SCORE = 10;	
	int defaultScore = DEFAULT_SCORE;
	Class jUnitClass;
	boolean isExtra;
	long maxScore;
	String explanation;
	String group;
	
	public AJUnitTestToGraderTestCase (Class aJUnitClass) {
		setJUnitClass(aJUnitClass);
	}
	
	public AJUnitTestToGraderTestCase () {
	}
	
	public Class getJUnitClass() {
		return jUnitClass;
	}
	
	public void setDefaultScore(int aDefaultScore) {
		defaultScore = aDefaultScore;
	}
	
	public int getDefaultScore() {
		return defaultScore;
	}
	
	public void setMaxScore (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(MaxValue.class)) {
			MaxValue aMaxValue =  (MaxValue) aJUnitClass.getAnnotation(MaxValue.class);
			maxScore = aMaxValue.value();
		} else {
			maxScore = defaultScore;
		}
	}
	public void setIsRestriction (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(IsRestriction.class)) {
			IsRestriction anIsRestriction =  (IsRestriction) aJUnitClass.getAnnotation(IsRestriction.class);
			isExtra = anIsRestriction.value();
		} else {
			isExtra = false;
		}
	}
	public void setIsExtra (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(IsExtra.class)) {
			IsExtra anIsExtra =  (IsExtra) aJUnitClass.getAnnotation(IsExtra.class);
			isExtra = anIsExtra.value();
		} else {
			isExtra = false;
		}
	}
	
	public void setExplanation (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(Explanation.class)) {
			Explanation anExplanation =  (Explanation) aJUnitClass.getAnnotation(Explanation.class);
			explanation = anExplanation.value();
		} else {
			explanation = aJUnitClass.getName();
		}
		setName(explanation);
	}	

	public void setGroup (Class aJUnitClass) {
		if (aJUnitClass.isAnnotationPresent(Group.class)) {
			Group aGroup =  (Group) aJUnitClass.getAnnotation(Group.class);
			group = aGroup.value();
		} else {
			group = explanation;
		}
	}
	
	public void setJUnitClass(Class aJUnitClass) {
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
	public boolean isRestriction() {
		return isExtra;
	}	
	public long isMaxScore() {
		return maxScore;
	}	
	public String getExplanation() {
		return explanation;
	}	
	
	

	

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main (String[] args) {
		AJUnitTestToGraderTestCase foo = new AJUnitTestToGraderTestCase();
		foo.setJUnitClass(ACartesianPointJUnitTester.class);
		System.out.println (foo);
	}
}
