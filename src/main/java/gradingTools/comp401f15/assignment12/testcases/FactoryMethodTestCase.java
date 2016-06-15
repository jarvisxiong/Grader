package gradingTools.comp401f15.assignment12.testcases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
import grader.util.IntrospectionUtil;

public class FactoryMethodTestCase extends BasicTestCase {
	String factoryClassTag;
	String factoryMethodTag;
	String instantiatedTypeTag;
	public FactoryMethodTestCase (String aFactoryClass, String aFactoryMethod, String anInstantiatedType) {
		super ("Factory Method Test Case");
		factoryClassTag = aFactoryClass;
		factoryMethodTag = aFactoryMethod;
		 instantiatedTypeTag = anInstantiatedType;
		
	}
	@Override
	public TestCaseResult test(Project aProject, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		Class<?> factoryClass = IntrospectionUtil.getOrFindClass(aProject, this, factoryClassTag);
		if (factoryClass == null) {
			return fail("Factory class:" + factoryClassTag + " not found.");
		}
		Method factoryMethod =	IntrospectionUtil.getOrFindUniqueMethod(aProject, this, factoryClass, factoryMethodTag);
		if (factoryMethod == null) {
			return fail("Unique factory method:" + factoryMethodTag + " not found.");

		}		
		// at some point, expect an interface here
		Class instantiatedClass = IntrospectionUtil.getOrFindClass(aProject, this, instantiatedTypeTag);
		if (instantiatedClass == null) {
			return fail("Instantiated class:" + instantiatedTypeTag + " not found.");
		}
		try {
		Constructor aConstructor = instantiatedClass.getConstructor();
		if (aConstructor == null) {
			return fail("Instantiated class does not have parameterless constructor:");		
		}
		
		Object instantiatedObject = ExecutionUtil.timedInvoke(aConstructor, new Object[]{});
		if (instantiatedObject == null) {
			return fail ("Instantiation returned null");
		}
		Object anActualClass = instantiatedObject.getClass();
		if (instantiatedClass.isAssignableFrom(instantiatedClass)) {
			IntrospectionUtil.putInstance(aProject, this, instantiatedTypeTag, instantiatedObject);
			return pass();
		}
		return partialPass (0.7, "Actual class:" + anActualClass + " not assignable from:" + instantiatedClass );
		} catch (Exception e) {
			return fail ("Instanitaion caused exception:" + e.getMessage());
		}
		
		

	}

}
