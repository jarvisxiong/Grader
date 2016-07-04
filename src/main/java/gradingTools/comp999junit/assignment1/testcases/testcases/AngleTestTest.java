package gradingTools.comp999junit.assignment1.testcases.testcases;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.junit.GradableJUnitTest;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProject;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.Project;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
public class AngleTestTest {
	@Test()
	public void Test() {
		Project aGradedProject = CurrentProjectHolder.getOrCreateCurrentProject(); // there should be something assigned
		double aFractionComplete = 0.0;
		try {
		String aKey = "AngleTest";
		GradableJUnitTest aTest = BasicJUnitUtils.createGradable("AngleTest");
		Assert.assertTrue("Could not find class matching " + aKey, aTest != null);
		aFractionComplete += 0.1; // they had a test class at least
		Project aWrongAngleProject = new BasicProject(null, new File("."), null, "wrongangle");
		Project aCorrectAngleProject = new BasicProject(null, new File("."), null, "allcorrect");
		CurrentProjectHolder.setProject(aCorrectAngleProject);
		TestCaseResult aCorrectAngleResult = aTest.test();
		CurrentProjectHolder.setProject(aWrongAngleProject);
		TestCaseResult aWrongAngleResult = aTest.test();
		Assert.assertTrue("Correct angle failed and wrong angle passed:" + aFractionComplete, aCorrectAngleResult.isPass() || aWrongAngleResult.isFail());
		aFractionComplete = 0.4;
		Assert.assertTrue("Correct angle failed and wrong angle failed:" + aFractionComplete, aCorrectAngleResult.isPass());
		Assert.assertTrue("Wrong angle passed:" + aFractionComplete, !aWrongAngleResult.isPass());
		aFractionComplete = 1.0;

		} catch (Exception e) {
			BasicJUnitUtils.assertTrue(e, aFractionComplete);
		} finally {
			CurrentProjectHolder.setProject(aGradedProject);

		}
		
	}

}
