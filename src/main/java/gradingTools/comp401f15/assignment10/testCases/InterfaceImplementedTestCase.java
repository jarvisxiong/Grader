package gradingTools.comp401f15.assignment10.testCases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ProjectIntrospection;

public class InterfaceImplementedTestCase extends BasicTestCase {
	String tag;
	Class implementedInterface;
	// add something for interface being a tag, or auto check tag
	public InterfaceImplementedTestCase (String aTag, Class anInterface) {
		super (aTag + " Implements " + anInterface);
		tag = aTag;
		implementedInterface = anInterface;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		Class<?> clazz = ProjectIntrospection.getOrFindClass(project, this, tag);
		if (clazz == null) {
			return fail(tag + " not found.", autoGrade);
		}
		if (implementedInterface == null) {
			return pass();
		}
		if (clazz.isInterface()) {
			System.out.println("got interface instead of class");
			return pass();
		}
		Class[] anInterfaces = clazz.getInterfaces();
		for (Class anInterface:anInterfaces){
			if (implementedInterface.isAssignableFrom(anInterface))
				return pass(autoGrade);
		}
		// we did not find the right class
		ProjectIntrospection.putClass(project, this, tag, null);	
        return partialPass(0.0, tag + " does not implement " + implementedInterface);
	}

}
