package gradingTools.comp110.program0;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class HelloWorldClassTestCase extends BasicTestCase {

	public HelloWorldClassTestCase() {
		super("HelloWorld class test case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		// You can get the classes and information about them from the project
		// using the ClassesManager
		if (project.getClassesManager().isEmpty())
			throw new NotGradableException();
		ClassesManager manager = project.getClassesManager().get();

		// Check for a class with the name HelloWorld
		for (ClassDescription description : manager.getClassDescriptions()) {
			if (description.getJavaClass().getName().equals("HelloWorld")
					|| description.getJavaClass().getName().matches(".+[.]HelloWorld")) {
				return pass();
			} else if (description.getJavaClass().getName().equalsIgnoreCase("HelloWorld")) {
				return partialPass(0.5,
						"HelloWorld class name does not contain proper capitalization");
			}
		}

		return fail("No class with name HelloWorld found");
	}
}