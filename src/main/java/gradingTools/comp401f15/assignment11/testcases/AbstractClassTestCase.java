package gradingTools.comp401f15.assignment11.testcases;

import java.lang.reflect.Modifier;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;
import grader.util.ProjectIntrospection;

public class AbstractClassTestCase extends BasicTestCase {
	String tag;
	public AbstractClassTestCase (String aTag) {
		super (aTag + " is Abstract Test Case");
		tag = aTag;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		Class<?> clazz = ProjectIntrospection.getOrFindClass(project, this, tag);
		if (clazz == null) {
			return fail(tag + " not found.", autoGrade);
		}
		if (Modifier.isAbstract(clazz.getModifiers()))
            return pass(autoGrade);
        return fail(tag + " is not abstract.", autoGrade);
	}

}
