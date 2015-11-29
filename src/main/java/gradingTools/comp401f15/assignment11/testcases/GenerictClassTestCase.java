package gradingTools.comp401f15.assignment11.testcases;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

public class GenerictClassTestCase extends BasicTestCase {
	String tag;
	public GenerictClassTestCase (String aTag) {
		super (aTag + " is Generic Test Case");
		tag = aTag;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		Class<?> clazz = IntrospectionUtil.getOrFindClass(project, this, tag);
		if (clazz == null) {
			return fail(tag + " not found.", autoGrade);
		}
		
//		ParameterizedType foo = (ParameterizedType) clazz.getGenericSuperclass();
		Class bar = clazz.asSubclass(clazz);
		Type super2 = bar.getGenericSuperclass();

		Object anObject;
		try {
			anObject = clazz.newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			return fail ("table class does not have empty constructor");
		}
		Class aConcreteClass = anObject.getClass();
		
		ParameterizedType aGenericSuperClass = (ParameterizedType) aConcreteClass.getGenericSuperclass();
	
		
		if (Modifier.isAbstract(clazz.getModifiers()))
            return pass(autoGrade);
        return fail(tag + " is not abstract.", autoGrade);
	}

}
