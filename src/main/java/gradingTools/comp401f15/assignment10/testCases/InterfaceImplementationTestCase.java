package gradingTools.comp401f15.assignment10.testCases;

import java.lang.reflect.Modifier;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

public class InterfaceImplementationTestCase extends BasicTestCase {
	String tag;
	Class implementedInterface;
	public InterfaceImplementationTestCase (String aTag, Class anInterface) {
		super (aTag + " Implements " + anInterface);
		tag = aTag;
		implementedInterface = anInterface;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		Class<?> clazz = IntrospectionUtil.getOrFindClass(project, this, tag);
		if (clazz == null) {
			return fail(tag + " not found.", autoGrade);
		}
		Class[] anInterfaces = clazz.getInterfaces();
		for (Class anInterface:anInterfaces){
			if (implementedInterface.isAssignableFrom(anInterface))
				return pass(autoGrade);
		}
	
        return fail(tag + " does not implement " + clazz);
	}

}
